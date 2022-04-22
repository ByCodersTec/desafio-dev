//@ts-check
import express from 'express';
import { PathParams } from 'express-serve-static-core';
import { BaseParameter, BodyParameter, Reference, Response, Security } from 'swagger-schema-official';
import { SwaggerUiOptions } from 'swagger-ui-express';

export interface IBaseRoute {
  auth?: boolean;
  method: string | 'GET' | 'POST' | 'PUT' | 'DELETE';
  path: PathParams;
  tags: Array<string>;
  summary: string;
  parameters?: BaseParameter[];
  requestBody?: BodyParameter;
  security?: { [securityName: string]: Security }[];
  responses?: { [responseName: string]: Response | Reference };
  handler: (req: express.Request, res: express.Response) => void;
}

export type IHandler = (req: express.Request, res: express.Response) => any;

export interface IFormatRoute {
  express: string;
  swagger: string;
}

export interface IInformation {
  name: string;
  title: string;
  version: string;
  description: string;
}

export interface IServerConfig {
  url: string;
}

export interface IAnyObject {
  [key: string]: object;
}

export interface ISpecification {
  openapi?: string;
  info: IInformation;
  servers: Array<IServerConfig>;
  components: IAnyObject;
  paths?: IAnyObject;
}

export interface ISwaggerProps {
  layout: SwaggerUiOptions;
  specification: ISpecification;
}

export interface IServer {
  NODE_ENV: string;
  BASE_HOST: string;
  BASE_PATH: string;
  PORT: number;

  app: express.Express;
  routers: express.Router;
  swaggerProps: ISwaggerProps;

  middleware?: (req: express.Request, res: express.Response, callback?: IHandler) => any;
  authMiddleware?: (req: express.Request, res: express.Response, next?: express.NextFunction) => any;

  addRoute(route: IBaseRoute): void;
  setSwaggerProps(props: ISwaggerProps): void;
  initialize(): express.Express;
  listen(port?: number): void;
}
