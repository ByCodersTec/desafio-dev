FROM node:10-alpine
RUN mkdir -p /home/node/app/node_modules && chown -R node:node /home/node/app
WORKDIR /home/node/app
COPY package*.json ./
USER node
RUN npm install
COPY --chown=node:node . .
EXPOSE 8000
CMD [ "node", "index.js" ]

# docker build -t bycoder_node .
# docker run -p 8000:8000 bycoder_node