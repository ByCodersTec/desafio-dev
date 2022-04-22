import ExpressSwagger from '../../dist/index';
import { specification } from '../configs/swagger';
import Return from '../models/Return';

export default class ExampleController {
  static setRoutes() {
    ExpressSwagger.Server.addRoute({
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
