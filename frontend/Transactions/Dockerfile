FROM node:16.14.2 AS build
WORKDIR /usr/src/app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm install -g @angular/cli
RUN ng build

FROM nginx:alpine
COPY --from=build /usr/src/app/dist/transactions /usr/share/nginx/html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]