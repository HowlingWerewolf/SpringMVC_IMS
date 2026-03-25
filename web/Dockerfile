# Use lightweight nginx to serve pre-built Angular files copied from the host
FROM nginx:alpine

# Remove default nginx static files
RUN rm -rf /usr/share/nginx/html/*

# Copy built Angular app (expecting `dist/` present in build context)
# The developer will run `npm run build` locally which typically produces `dist/browser`.
# Allow overriding which dist subfolder to copy via build-arg `DIST_DIR`.
# Default changed to dist/browser as a sensible default for this project.
ARG DIST_DIR=dist/browser
COPY ${DIST_DIR}/ /usr/share/nginx/html/

# Copy custom nginx config to enable SPA fallback (client-side routing)
COPY nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]
