import color from 'cli-color';
import * as SwaggerUi from 'swagger-ui-express';
import express, { Express, Router } from 'express';

import {
  IServer,
  ISwaggerProps,
  ISpecification,
  IInformation,
  IServerConfig,
  IAnyObject,
  IBaseRoute
} from "./interfaces";

class AnyObject implements IAnyObject {
  [key: string]: object;
}

class ServerConfig implements IServerConfig {
  url: string;

  constructor() {
    this.url = "";
  }
}

class Information implements IInformation {
  name: string;
  title: string;
  version: string;
  description: string;

  constructor() {
    this.name = "";
    this.title = "";
    this.version = "";
    this.description = "";
  }
}

class Layout implements SwaggerUi.SwaggerUiOptions {
}

class Specification implements ISpecification {
  openapi: string;
  info: IInformation;
  servers: IServerConfig[];
  components: IAnyObject;
  paths: IAnyObject;

  constructor() {
    this.openapi = "3.0.0";
    this.info = new Information();
    this.servers = [];
    this.components = new AnyObject();
    this.paths = new AnyObject();
  }
}

class SwaggerProps implements ISwaggerProps {
  layout: SwaggerUi.SwaggerUiOptions;
  specification: ISpecification;

  constructor() {
    this.layout = new Layout();
    this.specification = new Specification();
  }
}

class Server implements IServer {
  NODE_ENV: string;
  BASE_HOST: string;
  BASE_PATH: string;
  PORT: number;

  serverApp: Express;
  serverRouter: Router;
  swaggerProps: ISwaggerProps;

  serverMiddleware: (req: any, res: any, callback: Function) => Function;

  constructor() {
    this.NODE_ENV = "";
    this.BASE_HOST = "";
    this.BASE_PATH = "";
    this.PORT = 0;

    this.serverApp = express();
    this.serverRouter = express.Router();
    this.serverMiddleware = () => function() {};

    this.swaggerProps = new SwaggerProps();

    this.serverRouter.route("/").get((req, res) => res.status(200).json({
      StatusCode: 200, 
      Message: `${this.swaggerProps.specification.info.name.toUpperCase()}: OK! - process.env.NODE_ENV: ${this.NODE_ENV}`,
    }));
  }

  addRoute(route: IBaseRoute): void {
    const serverMiddleware = this.serverMiddleware;
    
    if (route.method === 'GET') {
      this.serverRouter.route(route.path).get(function (req, res) {
        serverMiddleware(req, res, route.handler);
      });
    }
    else if (route.method === 'POST') {
      this.serverRouter.route(route.path).post(function (req, res) {
        serverMiddleware(req, res, route.handler);
      });
    }
    else if (route.method === 'PUT') {
      this.serverRouter.route(route.path).put(function (req, res) {
        serverMiddleware(req, res, route.handler);
      });
    }
    else if (route.method === 'DELETE') {
      this.serverRouter.route(route.path).delete(function (req, res) {
        serverMiddleware(req, res, route.handler);
      });
    }

    if (this.swaggerProps.specification.paths[route.path.toString()]) {
      Object.assign(this.swaggerProps.specification.paths[route.path.toString()], {
        [route.method.toLowerCase()]: {
          tags: route.tags,
          summary: route.summary,
          parameters: route.parameters,
          responses: route.responses
        }
      });
    }
    else {
      this.swaggerProps.specification.paths[route.path.toString()] = {
        [route.method.toLowerCase()]: {
          tags: route.tags,
          summary: route.summary,
          parameters: route.parameters,
          responses: route.responses
        }
      };
    }
  }

  setSwaggerProps(props: ISwaggerProps): void {
    Object.assign(this.swaggerProps.layout, props.layout);
    Object.assign(this.swaggerProps.specification, props.specification);
  }

  listen(): boolean {
    if (!this.NODE_ENV) return this.showMessage('A propriedade "NODE_ENV" não foi inicializada');
    if (!this.BASE_HOST) return this.showMessage('A propriedade "BASE_HOST" não foi inicializada');
    if (!this.BASE_PATH) return this.showMessage('A propriedade "BASE_PATH" não foi inicializada');
    if (!this.PORT) return this.showMessage('A propriedade "PORT" não foi inicializada');

    const routeDocs = this.BASE_PATH + '/docs';
    const swaggerSetup = SwaggerUi.setup(this.swaggerProps.specification, this.swaggerProps.layout);

    this.serverApp
      .use(this.BASE_PATH, this.serverRouter)
      .use(routeDocs, SwaggerUi.serve, swaggerSetup)
      .listen(this.PORT, () => {
        var _name = `\n ${this.swaggerProps.specification.info.name.toUpperCase()} `;
        var _description = `\n ${this.swaggerProps.specification.info.description} `;

        var _environment = `\n process.env.NODE_ENV: ${this.NODE_ENV} `;
        var _baseRoute = `\n Base Route: http://${this.BASE_HOST}:${this.PORT}${this.BASE_PATH} `;
        var _docsRoute = `\n Docs Route: http://${this.BASE_HOST}:${this.PORT}${this.BASE_PATH}/docs `;

        var message = `\n${_name} ${_description} ${_environment} ${_baseRoute} ${_docsRoute}`;

        console.log(color.bgWhite(color.black(message.replace(/([^\s][^\n]{1,75})/g, '$1 \n'))));
        console.log('');
      });

      return true;
  }

  showMessage(msg: string): boolean {
    console.log('');
    console.log('ExpressSwagger.Server diz: ');
    console.log('');
    console.log(msg);
    console.log('');

    return false;
  }
}

export default {
  AnyObject: AnyObject,
  ServerConfig: ServerConfig,
  Information: Information,
  Layout: Layout,
  Specification: Specification,
  SwaggerProps: SwaggerProps,
  Server: new Server(),
}
