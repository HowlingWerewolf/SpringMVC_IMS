
# postgres/Dockerfile
# Builds a container based on the official Postgres image. The base image/version
# can be overridden by passing --build-arg POSTGRES_VERSION=... when building.

ARG POSTGRES_VERSION=17.8-alpine3.23
FROM postgres:${POSTGRES_VERSION}

# Expose the Postgres port (compose still maps the host port)
EXPOSE 5432

# Do not set the database password or other secrets here. Keep them in compose
# environment or external secrets. This Dockerfile is intentionally minimal so
# the service can be configured by docker-compose or environment management.

# Optional: you can add initialization scripts under /docker-entrypoint-initdb.d
# in this directory if you need preloaded DB schema or data.

CMD ["postgres"]

