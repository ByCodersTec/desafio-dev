# move build to new container
FROM node:16.14.2-alpine
WORKDIR /app
COPY . /app/

COPY package.json /app/package.json
COPY wait-for-it.sh /app/wait-for-it.sh
COPY prisma /app/prisma

RUN npm install --silent
RUN npx prisma generate

RUN apk add --no-cache bash


# expose port
EXPOSE 5500
