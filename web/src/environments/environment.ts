export const environment = {
  production: false,
  // During local development you can either use the dev-server proxy (keep '/SpringMVC_IMS/api')
  // or point directly to the backend server. Pointing directly avoids webpack-dev-server "No static resource" 404
  // when the proxy is not in use.
  apiBase: 'http://localhost:8080/SpringMVC_IMS/api'
};

