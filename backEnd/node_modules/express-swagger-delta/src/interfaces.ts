import { Express, Router } from 'express';
import { SwaggerUiOptions } from 'swagger-ui-express';
import { PathParams } from 'express-serve-static-core';

export interface IBaseRoute
{
  method: string | 'GET' | 'POST' | 'PUT' | 'DELETE';
  path: PathParams;
  tags: Array<string>;
  summary: string;
  parameters: object;
  responses: object;
  handler: Function;
}

export interface IInformation
{
  name: string,
  title: string,
  version: string,
  description: string
}

export interface IServerConfig
{
  url: string
}

export interface IAnyObject
{
  [key: string]: object;
}

export interface ISpecification
{
  openapi: string,
  info: IInformation,
  servers: Array<IServerConfig>
  components: IAnyObject,
  paths: IAnyObject
}

export interface ISwaggerProps
{
  layout: SwaggerUiOptions,
  specification: ISpecification
}

export interface IServer
{
  NODE_ENV: string;
  BASE_HOST: string;
  BASE_PATH: string;
  PORT: number;
  
  serverApp: Express;
  serverRouter: Router;
  swaggerProps: ISwaggerProps;

  serverMiddleware: (req: any, res: any, callback: Function) => Function;
  
  addRoute(route: IBaseRoute): void;
  setSwaggerProps(props: ISwaggerProps): void;
  listen(): void;
}
