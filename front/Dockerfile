# 1. build vue.js application
FROM node:lts-alpine AS development

#Set work directory
WORKDIR /app

#copy package for build purpose
COPY package.json yarn.lock /app/

#install all node packages
RUN npm install -g yarn && yarn install

# copy application
COPY . /app

#create a new entry point
FROM development as build

#build application
RUN yarn run build

# 2. Setup nginx
FROM nginx:1.19.10-alpine

COPY --from=build /app/.nginx/nginx.conf /etc/nginx/conf.d/default.conf

WORKDIR /usr/share/nginx/html

COPY --from=build /app/dist .

ENTRYPOINT ["nginx", "-g", "daemon off;"]
