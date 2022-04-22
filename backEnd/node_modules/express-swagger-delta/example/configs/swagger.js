import { description, name, version } from '../../package.json';

export const layout = {
  explorer: false,
  customSiteTitle: 'Example Documentation',
  customCss: '.swagger-ui .topbar { display: none }',
  swaggerOptions: {
    docExpansion: 'none',
  },
};

export const specification = {
  info: {
    name: name,
    version: version,
    description: description,
    title: 'Example Documentation',
  },
  servers: [
    {
      url: `http://localhost:8080/api`,
    },
  ],
  components: {
    securitySchemes: {
      ExampleKey: {
        in: 'header',
        type: 'apiKey',
        name: 'ExampleKey',
      },
    },
    schemas: {
      Return: {
        type: 'object',
        properties: {
          statusCode: {
            type: 'integer',
            format: 'int64',
            description: 'Request Status Code',
          },
          message: {
            type: 'string',
            description: 'Request Status Message',
          },
          content: {
            type: 'object',
            description: 'Request Content',
          },
        },
      },
    },
    responses: {
      default: {
        description: 'Api Default Return',
        content: {
          'application/json': {
            schema: {
              $ref: '#/components/schemas/Return',
            },
          },
        },
      },
    },
  },
};
