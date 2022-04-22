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
      /* istanbul ignore next */
      .catch((err) => {
        /* istanbul ignore next */
        res.status(500).json(err);
      });
  }

  middleware(req, res, callback) {
    callback(req, res)
      .then((data) => res.status(data.statusCode).json(data))
      /* istanbul ignore next */
      .catch((err) => {
        /* istanbul ignore next */
        res.status(500).json(err);
      });
  }

  listen = (port) => this.server.listen(port);
}

export default new Server();
