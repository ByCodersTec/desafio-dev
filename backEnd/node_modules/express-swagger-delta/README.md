<link href="main.css" rel="stylesheet"></link>

# ✅ express-swagger-delta

Fast, unopinionated, minimalist web framework for [node](http://nodejs.org).

[![NPM Version][npm-image]][npm-url]
[![NPM Downloads][downloads-image]][downloads-url]
![badge-statements]
![badge-branches]
![badge-functions]
![badge-lines]

## 🔹 Installation

This is a [Node.js](https://nodejs.org/en/) module available through the
[npm registry](https://www.npmjs.com/).

Before installing, [download and install Node.js](https://nodejs.org/en/download/).

Installation is done using the
[`npm install` command](https://docs.npmjs.com/getting-started/installing-npm-packages-locally):

```bash
$ npm install express-swagger-delta
```

OU

```bash
$ yarn add express-swagger-delta
```

## 🔹 Usage

To start using the library, it is necessary to create a base configuration file, following the structure:

**Note:** Follows the same structure pattern as the [swagger.js documentation](https://swagger.io/docs/specification/basic-structure/) (openapi: 3.0.0).

```js
import { description, name, version } from '../../package.json';

export const layout = {
  explorer: false,
  customSiteTitle: 'Example Documentation',
  customCss: '.swagger-ui .topbar { display: none }',
  swaggerOptions: {
    docExpansion: 'none',
  },
};

export const specification = {
  info: {
    name: name,
    version: version,
    description: description,
    title: 'Example Documentation',
  },
  servers: [
    {
      url: `http://localhost:8080/api`,
    },
  ],
  components: {
    securitySchemes: {
      ExampleKey: {
        in: 'header',
        type: 'apiKey',
        name: 'ExampleKey',
      },
    },
    schemas: {
      Return: {
        type: 'object',
        properties: {
          statusCode: {
            type: 'integer',
            format: 'int64',
            description: 'Request Status Code',
          },
          message: {
            type: 'string',
            description: 'Request Status Message',
          },
          content: {
            type: 'object',
            description: 'Request Content',
          },
        },
      },
    },
    responses: {
      default: {
        description: 'Api Default Return',
        content: {
          'application/json': {
            schema: {
              $ref: '#/components/schemas/Return',
            },
          },
        },
      },
    },
  },
};
```

That done, you need to configure the server, which can be done as follows:

```js
import { json, urlencoded } from 'body-parser';
import cors from 'cors';
import helmet from 'helmet';
import ExpressSwagger from '../../dist/index';
import ExampleController from '../controllers/ExampleController';
import AuthService from '../services/AuthService';
import { layout, specification } from './swagger';

class Server {
  constructor() {
    this.server = ExpressSwagger.Server;

    this.server.NODE_ENV = 'test';
    this.server.BASE_HOST = 'localhost';
    this.server.BASE_PATH = '/api';
    this.server.PORT = 8080;

    this.server.setSwaggerProps({
      layout: layout,
      specification: specification,
    });

    this.server.app.use(cors());
    this.server.app.use(helmet());
    this.server.app.use(urlencoded({ extended: true }));
    this.server.app.use(json({ limit: '2gb' }));

    this.server.middleware = this.middleware;
    this.server.authMiddleware = this.authMiddleware;

    ExampleController.setRoutes();

    this.server.initialize();
  }

  authMiddleware(req, res, next) {
    AuthService.checkAuth(req)
      .then((auth) => {
        switch (auth.statusCode) {
          case 200:
            return next();
          case 400:
            return res.status(400).json(auth);
          case 401:
            return res.status(401).json(auth);
        }
      })
      .catch((err) => res.status(500).json(err));
  }

  middleware(req, res, callback) {
    callback(req, res)
      .then((data) => res.status(data.statusCode).json(data))
      .catch((err) => res.status(500).json(err));
  }

  listen = (port) => this.server.listen(port);
}

export default new Server();
```

To add a route to the server it is necessary to create a file for building routes by calling an option from the ExpressSwagger property and thus passing its parameters, that way the API documentation and route will already be created, see:

**Note:** The parameter object follows the same structure pattern as [swagger.js documentation](https://swagger.io/docs/specification/describing-parameters/) (openapi: 3.0.0).

```js
import ExpressSwagger from '../../dist/index';
import { specification } from '../configs/swagger';
import Return from '../models/Return';

export default class ExampleController {
  static setRoutes() {
    ExpressSwagger.Server.addRoute({
      auth: true,
      method: 'GET',
      path: '/example',
      tags: ['Example'],
      summary: 'Example Controller',
      security: [specification.components.securitySchemes],
      responses: specification.components.responses,
      handler: async (req) => {
        return new Return(200, 'OK', {
          date: new Date(),
        });
      },
    });

    ExpressSwagger.Server.addRoute({
      auth: true,
      method: 'GET',
      path: '/example/:text',
      tags: ['Example'],
      summary: 'Example Controller',
      security: [specification.components.securitySchemes],
      responses: specification.components.responses,
      parameters: [
        {
          in: 'path',
          required: true,
          name: 'text',
          schema: {
            type: 'string',
          },
          description: 'Text to print in the response',
        },
      ],
      handler: async (req) => {
        return new Return(200, 'OK', {
          date: new Date(),
          text: req.params.text,
        });
      },
    });
  }
}
```

## 🔹 Contributors

<table>
  <tr>
    <td align="center">
      <a href="https://github.com/donatocardoso">
        <img src="https://avatars.githubusercontent.com/u/28939485?v=3" width="100px;" alt=""/><br />
        <sub>🥇 <b>Donato C. Ávila</b></sub>
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/ThiagoOliveira001">
        <img src="https://avatars.githubusercontent.com/u/18088052?v=3" width="100px;" alt=""/><br />
        <sub><b>Thiago S. Oliveira</b></sub>
      </a>
    </td>
  </tr>
<table>

## 🔹 License

[MIT](LICENSE)

[badge-branches]: ./__tests__/badges/badge-branches.svg
[badge-functions]: ./__tests__/badges/badge-functions.svg
[badge-lines]: ./__tests__/badges/badge-lines.svg
[badge-statements]: ./__tests__/badges/badge-statements.svg
[npm-image]: https://img.shields.io/npm/v/express-swagger-delta.svg
[npm-url]: https://npmjs.org/package/express-swagger-delta
[downloads-image]: https://img.shields.io/npm/dm/express-swagger-delta.svg
[downloads-url]: https://npmjs.org/package/express-swagger-delta
