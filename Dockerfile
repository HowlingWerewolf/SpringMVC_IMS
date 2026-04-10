# Multi-stage build image for CI pipeline
#
# This Dockerfile performs the full project build (backend and frontend) and
# produces an image containing build artifacts under /artifacts. Use this in a
# pipeline to produce a reproducible build image that later stages can extract
# artifacts from (docker create + docker cp) or push to a registry.
#
# Usage (example):
# docker build -t springmvc-ims-build:latest .
# docker create --name tmp springmvc-ims-build:latest
# docker cp tmp:/artifacts ./artifacts
# docker rm tmp

######### Backend build (Maven) #########
FROM maven:3.9.14-eclipse-temurin-25-alpine AS backend-builder
WORKDIR /workspace

# Copy only what we need to leverage build cache where possible
COPY pom.xml ./
COPY core/pom.xml ./core/pom.xml

# Copy the rest of the source code
COPY . ./

# Build the backend (skip tests by default; pipeline can remove -DskipTests if needed)
RUN mvn -B -DskipTests package

######### Frontend build (Node / Angular) #########
FROM node:18-alpine AS frontend-builder
WORKDIR /workspace/web

# Copy frontend sources
COPY web/package*.json ./
COPY web/ ./

# Install deps and build. We use npm ci for reproducible installs.
RUN npm ci
RUN npm run build

######### Artifacts image #########
FROM alpine:3.18 AS artifacts

WORKDIR /artifacts

# Copy the entire backend build workspace and then collect any WAR files into /artifacts
# This approach avoids failing the Dockerfile if a specific glob doesn't match.
COPY --from=backend-builder /workspace /backend_workspace
RUN mkdir -p /artifacts && \
	find /backend_workspace -maxdepth 4 -type f -name "*.war" -exec cp --parents {} /artifacts/ \; || true

# Copy frontend build output
RUN mkdir -p web
COPY --from=frontend-builder /workspace/web/dist/browser/ ./web/

# Metadata
LABEL org.opencontainers.image.source="."
LABEL maintainer="devops@example.com"

# Nothing to run in this image; it only holds build artifacts for the pipeline.
CMD ["/bin/sh", "-c", "echo 'This image contains build artifacts in /artifacts' && ls -la /artifacts"]


