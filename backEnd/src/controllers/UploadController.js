import UploadService from '../Services/UploadService'
import path from "path";
import ExpressSwagger from 'express-swagger-delta';
import Return from '../Models/Return';

class UploadController {
    setRoutes() {
        ExpressSwagger.Server.addRoute({
            method: 'POST',
            path: '/upload',
            tags: ['Upload'],
            summary: 'Upload do arquivo.',
            responses: {
                200: {
                    description: 'Chamada executada com sucesso.',
                    $ref: '#/components/responses/default'
                },
                400: {
                    description: 'Chamada executada com erros.',
                    $ref: '#/components/responses/default'
                },
                401: {
                    description: 'Inautorizado! Verifique os headers.',
                    $ref: '#/components/responses/default'
                }
            },
            handler: async (req) => {
                const file = req.files.file;
                const filename = file.name;
                if (file === null || typeof file === "undefined") {
                    return new Return(415, 'File Upload Failed!');
                }
                const newpath = path.join(__dirname, '../files/')
                file.mv(`${newpath}${filename}`, async (err) => {
                    if (err) {
                        return new Return(415, 'File Upload Failed!', err);
                    }
                    var filePath1 = path.join(__dirname, '../files/')
                    var fs = require('fs')
                    var files = fs.readdirSync(filePath1, { withFileTypes: true })
                        .filter(item => !item.isDirectory())
                        .map(item => item.name)
                    if (files.includes("CNAB.txt")) {
                        return await UploadService.upload()
                    } else {
                        return new Return(400, 'Verifique o nome do arquivo enviado!')
                    }
                });
                return new Return(200, 'OK')
            }
        });

        ExpressSwagger.Server.addRoute({
            method: 'GET',
            path: '/upload',
            tags: ['uploads'],
            summary: 'Recupera todos os uploads.',
            responses: {
                200: {
                    description: 'Chamada executada com sucesso.',
                    $ref: '#/components/responses/default'
                },
                400: {
                    description: 'Chamada executada com erros.',
                    $ref: '#/components/responses/default'
                },
                401: {
                    description: 'Inautorizado! Verifique os headers.',
                    $ref: '#/components/responses/default'
                }
            },
            handler: async (req) => {
                return await UploadService.getAll();
            }
        });
    }
}

export default new UploadController()