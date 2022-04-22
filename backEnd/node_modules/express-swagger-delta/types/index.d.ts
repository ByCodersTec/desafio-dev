import express from 'express';
import * as SwaggerUi from 'swagger-ui-express';
import { IAnyObject, IBaseRoute, IFormatRoute, IHandler, IInformation, IServer, IServerConfig, ISpecification, ISwaggerProps } from './interfaces';
export declare class FormatRoute implements IFormatRoute {
    express: string;
    swagger: string;
    constructor(route: string);
}
export declare class AnyObject implements IAnyObject {
    [key: string]: object;
}
export declare class ServerConfig implements IServerConfig {
    url: string;
    constructor();
}
export declare class Information implements IInformation {
    name: string;
    title: string;
    version: string;
    description: string;
    constructor();
}
export declare class Layout implements SwaggerUi.SwaggerUiOptions {
}
export declare class Specification implements ISpecification {
    openapi?: string;
    info: IInformation;
    servers: IServerConfig[];
    components: IAnyObject;
    paths?: IAnyObject;
    constructor();
}
export declare class SwaggerProps implements ISwaggerProps {
    layout: SwaggerUi.SwaggerUiOptions;
    specification: ISpecification;
    constructor();
}
export declare class Server implements IServer {
    NODE_ENV: string;
    BASE_HOST: string;
    BASE_PATH: string;
    PORT: number;
    app: express.Express;
    routers: express.Router;
    swaggerProps: ISwaggerProps;
    middleware?: (req: express.Request, res: express.Response, callback?: IHandler) => any;
    authMiddleware?: (req: express.Request, res: express.Response, next?: express.NextFunction) => any;
    constructor();
    addRoute(route: IBaseRoute): void;
    setSwaggerProps(props: ISwaggerProps): void;
    initialize(): express.Express;
    listen(port?: number): boolean;
    _formatRoute(route: string): IFormatRoute;
    _showMessage(msg: string): boolean;
    _handler(handler: IHandler): (req: any, res: any) => void;
}
export declare const server: Server;
declare const _default: {
    AnyObject: typeof AnyObject;
    ServerConfig: typeof ServerConfig;
    Information: typeof Information;
    Layout: typeof Layout;
    Specification: typeof Specification;
    SwaggerProps: typeof SwaggerProps;
    Server: Server;
};
export default _default;
//# sourceMappingURL=index.d.ts.map