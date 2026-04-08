FROM nginx:1.26-alpine

# Build arg pointing to the local build output directory (relative to build context)
ARG DIST_DIR=dist/browser

# Remove default nginx static files
RUN rm -rf /usr/share/nginx/html/*

# Copy nginx config
COPY nginx.conf /etc/nginx/conf.d/default.conf

# Copy the built Angular app to nginx root. We'll also normalize the base href
# in index.html to '/' so the app works when served from the container root.
RUN mkdir -p /usr/share/nginx/html
COPY ${DIST_DIR}/ /usr/share/nginx/html/

# If the built index.html contains references with the /SpringMVC_IMS/ prefix,
# normalize them to root '/' so assets and routes work when serving from '/'.
RUN if [ -f /usr/share/nginx/html/index.html ]; then \
	  sed -i 's|<base href="/SpringMVC_IMS/"|<base href="/"|g' /usr/share/nginx/html/index.html || true; \
	  sed -i 's|/SpringMVC_IMS/|/|g' /usr/share/nginx/html/index.html || true; \
	fi

EXPOSE 80

# Use the default nginx foreground command
CMD ["nginx", "-g", "daemon off;"]



