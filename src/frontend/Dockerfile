# Node 16.16 LTS, Alpine Linux is a small distribution image
FROM node:16.16-alpine3.15
# setup workspace
RUN mkdir -p /home/app
WORKDIR /home/app

# install angular
RUN npm install -g @angular/cli

# copy dependency files
COPY package.json /home/app/package.json

# install dependencies
RUN npm install

# Copy all files to the work dir
COPY . . 

# RUN server
EXPOSE 4200
CMD ["ng", "serve", "--host", "0.0.0.0", "--poll=2000", "--port", "4200", "--disable-host-check"]