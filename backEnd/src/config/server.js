import cors from 'cors';
import dotenv from 'dotenv';
import bodyParser from 'body-parser';
import fileupload from 'express-fileupload';
import ExpressSwagger from 'express-swagger-delta';
import { specification, layout } from './Swagger';
import express from 'express'

import LogService from '../Services/LogService';
import * as Controller from '../Controllers';

class Server {
	setConfigs() {
		if ((process.env.NODE_ENV + '').trim() !== 'PRODUCTION')
			dotenv.config({
				path: './Env/Homolog',
			});

		ExpressSwagger.Server.NODE_ENV = process.env.NODE_ENV || process.env.ENVIRONMENT;
		ExpressSwagger.Server.BASE_HOST = process.env.BASE_HOST;
		ExpressSwagger.Server.BASE_PATH = process.env.BASE_PATH;
		ExpressSwagger.Server.PORT = process.env.PORT;

		ExpressSwagger.Server.setSwaggerProps({ layout: layout, specification: specification });

		ExpressSwagger.Server.serverApp.use(cors());
		ExpressSwagger.Server.serverApp.use(fileupload())
		ExpressSwagger.Server.serverApp.use(express.static("files"))
		ExpressSwagger.Server.serverApp.use(bodyParser.urlencoded({ extended: true }));
		ExpressSwagger.Server.serverApp.use(bodyParser.json());
		
		ExpressSwagger.Server.serverMiddleware = this.serverMiddleware;

		Controller.Upload.setRoutes();
		Controller.TipoTransacao.setRoutes();
	}
	serverMiddleware(req, res, callback) {
		if ((process.env.NODE_ENV + '').trim() !== 'PRODUCTION') {
			callback(req, res)
				.then((response) => LogService.addLogSuccess(req, res, response))
				.catch((error) => LogService.addLogError(req, res, error));

		} else {
			LogService.authorization(req.headers)
				.then((auth) => {
					if (auth.statusCode == 200) {
						callback(req, res)
							.then((response) => LogService.addLogSuccess(req, res, response))
							.catch((error) => LogService.addLogError(req, res, error));
					}
					else {
						return res
							.status(auth.statusCode)
							.json(auth);
					}
				})
				.catch((error) => LogService.addLogError(req, res, error));
		}
	}
	listen() {
		ExpressSwagger.Server.listen();
	}
}

export default new Server();
