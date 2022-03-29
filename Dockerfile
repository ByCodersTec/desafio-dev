FROM node:10-alpine as angular
WORKDIR /app
COPY package.json /app
RUN npm install --silent
COPY . .
RUN npm run build

FROM nginx:alpine
VOLUME /var/cache/nginx
COPY --from=angular app/dist/front-angular /usr/share/nginx/html
COPY ./config/nginx.conf /etc/nginx/conf.d/default.conf


# docker build -t bycoder_angular .
# docker run -p 8081:80 bycoder_angular