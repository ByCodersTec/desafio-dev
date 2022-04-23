import * as SwaggerUi from 'swagger-ui-express';
import { Express, Router } from 'express';
import { IServer, ISwaggerProps, ISpecification, IInformation, IServerConfig, IAnyObject, IBaseRoute } from "./interfaces";
declare class AnyObject implements IAnyObject {
    [key: string]: object;
}
declare class ServerConfig implements IServerConfig {
    url: string;
    constructor();
}
declare class Information implements IInformation {
    name: string;
    title: string;
    version: string;
    description: string;
    constructor();
}
declare class Layout implements SwaggerUi.SwaggerUiOptions {
}
declare class Specification implements ISpecification {
    openapi: string;
    info: IInformation;
    servers: IServerConfig[];
    components: IAnyObject;
    paths: IAnyObject;
    constructor();
}
declare class SwaggerProps implements ISwaggerProps {
    layout: SwaggerUi.SwaggerUiOptions;
    specification: ISpecification;
    constructor();
}
declare class Server implements IServer {
    NODE_ENV: string;
    BASE_HOST: string;
    BASE_PATH: string;
    PORT: number;
    serverApp: Express;
    serverRouter: Router;
    swaggerProps: ISwaggerProps;
    serverMiddleware: (req: any, res: any, callback: Function) => Function;
    constructor();
    addRoute(route: IBaseRoute): void;
    setSwaggerProps(props: ISwaggerProps): void;
    listen(): boolean;
    showMessage(msg: string): boolean;
}
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