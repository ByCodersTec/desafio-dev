import color from 'cli-color';
import express from 'express';
import * as SwaggerUi from 'swagger-ui-express';
import {
  IAnyObject,
  IBaseRoute,
  IFormatRoute,
  IHandler,
  IInformation,
  IServer,
  IServerConfig,
  ISpecification,
  ISwaggerProps,
} from './interfaces';

export class FormatRoute implements IFormatRoute {
  express: string;
  swagger: string;

  constructor(route: string) {
    this.express = route;
    this.swagger = route;
  }
}

export class AnyObject implements IAnyObject {
  [key: string]: object;
}

export class ServerConfig implements IServerConfig {
  url: string;

  constructor() {
    this.url = '';
  }
}

export class Information implements IInformation {
  name: string;
  title: string;
  version: string;
  description: string;

  constructor() {
    this.name = '';
    this.title = '';
    this.version = '';
    this.description = '';
  }
}

export class Layout implements SwaggerUi.SwaggerUiOptions {}

export class Specification implements ISpecification {
  openapi?: string;
  info: IInformation;
  servers: IServerConfig[];
  components: IAnyObject;
  paths?: IAnyObject;

  constructor() {
    this.openapi = '3.0.0';
    this.info = new Information();
    this.servers = [];
    this.components = new AnyObject();
    this.paths = new AnyObject();
  }
}

export class SwaggerProps implements ISwaggerProps {
  layout: SwaggerUi.SwaggerUiOptions;
  specification: ISpecification;

  constructor() {
    this.layout = new Layout();
    this.specification = new Specification();
  }
}

export class Server implements IServer {
  NODE_ENV: string;
  BASE_HOST: string;
  BASE_PATH: string;
  PORT: number;

  app: express.Express;
  routers: express.Router;
  swaggerProps: ISwaggerProps;

  middleware?: (req: express.Request, res: express.Response, callback?: IHandler) => any;
  authMiddleware?: (req: express.Request, res: express.Response, next?: express.NextFunction) => any;

  constructor() {
    this.NODE_ENV = '';
    this.BASE_HOST = '';
    this.BASE_PATH = '';
    this.PORT = 0;

    this.app = express();
    this.routers = express.Router();

    this.swaggerProps = new SwaggerProps();

    this.routers.route('/').get((req: any, res: any) => {
      const name = this.swaggerProps.specification.info.name.toUpperCase();

      res.status(200).json({
        StatusCode: 200,
        Date: new Date(),
        Message: `${name}: OK! - process.env.NODE_ENV: ${this.NODE_ENV}`,
      });
    });
  }

  addRoute(route: IBaseRoute): void {
    // Express
    const { handler, path, method } = route;
    const formatedRoute = this._formatRoute(path.toString());
    const handles = [this._handler(handler)];

    if (this.authMiddleware && route.auth !== false) {
      handles.unshift(this.authMiddleware);
    }

    switch (method.toLowerCase()) {
      case 'get':
        this.routers.route(formatedRoute.express).get(...handles);
        break;
      case 'post':
        this.routers.route(formatedRoute.express).post(...handles);
        break;
      case 'put':
        this.routers.route(formatedRoute.express).put(...handles);
        break;
      case 'delete':
        this.routers.route(formatedRoute.express).delete(...handles);
        break;
    }

    // Swagger
    let routeConfig = {
      tags: route.tags,
      summary: route.summary,
    };

    if (route.responses)
      Object.assign(routeConfig, {
        responses: route.responses,
      });

    if (route.security)
      Object.assign(routeConfig, {
        security: route.security,
      });

    if (route.parameters)
      Object.assign(routeConfig, {
        parameters: route.parameters,
      });

    if (route.requestBody)
      Object.assign(routeConfig, {
        requestBody: route.requestBody,
      });

    if (!this.swaggerProps.specification.paths) {
      this.swaggerProps.specification.paths = {};
    } else if (this.swaggerProps.specification.paths[formatedRoute.swagger]) {
      Object.assign(this.swaggerProps.specification.paths[formatedRoute.swagger], {
        [route.method.toLowerCase()]: routeConfig,
      });
    } else {
      this.swaggerProps.specification.paths[formatedRoute.swagger] = {
        [route.method.toLowerCase()]: routeConfig,
      };
    }
  }

  setSwaggerProps(props: ISwaggerProps): void {
    Object.assign(this.swaggerProps.layout, props.layout);
    Object.assign(this.swaggerProps.specification, props.specification);
  }

  initialize(): express.Express {
    if (!this.NODE_ENV) this._showMessage('A propriedade "NODE_ENV" deve ser inicializada');
    if (!this.BASE_HOST) this._showMessage('A propriedade "BASE_HOST" deve ser inicializada');
    if (!this.PORT) this._showMessage('A propriedade "PORT" deve ser inicializada');

    const routeDocs = this.BASE_PATH + '/documentation';

    const swaggerSetup = SwaggerUi.setup(this.swaggerProps.specification, this.swaggerProps.layout);

    this.app.use(this.BASE_PATH, this.routers).use(routeDocs, SwaggerUi.serve, swaggerSetup);

    return this.app;
  }

  listen(port?: number): boolean {
    if (!this.NODE_ENV) return this._showMessage('A propriedade "NODE_ENV" não foi inicializada');
    if (!this.BASE_HOST) return this._showMessage('A propriedade "BASE_HOST" não foi inicializada');
    if (!port && !this.PORT) return this._showMessage('A propriedade "PORT" não foi inicializada');

    const routeDocs = this.BASE_PATH + '/documentation';

    this.app.listen(port || this.PORT, () => {
      var _name = `\n ${this.swaggerProps.specification.info.name.toUpperCase()} `;
      var _description = `\n ${this.swaggerProps.specification.info.description} `;

      var _environment = `\n process.env.NODE_ENV: ${this.NODE_ENV} `;
      var _baseRoute = `\n Base Route: http://${this.BASE_HOST}:${this.PORT}${this.BASE_PATH} `;
      var _docsRoute = `\n Docs Route: http://${this.BASE_HOST}:${this.PORT}${routeDocs} `;

      var message = `\n${_name} ${_description} ${_environment} ${_baseRoute} ${_docsRoute}`;

      console.log(color.bgWhite(color.black(message.replace(/([^\s][^\n]{1,75})/g, '$1 \n'))));

      console.log('');
    });

    return true;
  }

  _formatRoute(route: string): IFormatRoute {
    let pathKeys = route.split('/');

    let formatRoute = new FormatRoute(route);

    if (route.includes(':')) {
      formatRoute.express = route;

      for (let i = 0; i < pathKeys.length; i++) {
        if (pathKeys[i].includes(':')) pathKeys[i] = pathKeys[i].replace(':', '{') + '}';
      }

      formatRoute.swagger = pathKeys.join('/');
    } else if (route.includes('{') && route.includes('}')) {
      formatRoute.swagger = route;

      for (let i = 0; i < pathKeys.length; i++) {
        pathKeys[i] = pathKeys[i].replace('{', ':').replace('}', '');
      }

      formatRoute.express = pathKeys.join('/');
    }

    return formatRoute;
  }

  _showMessage(msg: string): boolean {
    console.log('');
    console.log('ExpressSwagger.Server diz: ');
    console.log('');
    console.log(msg);
    console.log('');

    return false;
  }

  _handler(handler: IHandler) {
    return (req: any, res: any) => {
      if (this.middleware) this.middleware(req, res, handler);
    };
  }
}

export const server = new Server();

export default {
  AnyObject: AnyObject,
  ServerConfig: ServerConfig,
  Information: Information,
  Layout: Layout,
  Specification: Specification,
  SwaggerProps: SwaggerProps,
  Server: new Server(),
};
