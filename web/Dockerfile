FROM node:25-alpine3.22

WORKDIR /usr/src/app
COPY web /usr/src/app

RUN npm install -g @angular/cli
RUN npm install
#RUN npm ci
#RUN npm run build

# Expose port
EXPOSE 4200

CMD ["ng", "serve"]