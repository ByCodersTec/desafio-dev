import { name, version, description } from '../../package.json';

export const layout = {
  explorer: false,
  customCss: '.swagger-ui .topbar { display: none }',
  customSiteTitle: 'Documentação da API'
};

export const specification = {
  info: {
    name: name,
    version: version,
    description: description,
    title: 'Documentação da API'
  },
  servers: [
    {
      url: `http://localhost:3001/api/desafio`
    }
  ],
  components: {
    securitySchemes: {      
    },
    schemas: {
      Return: {
        type: 'object',
        properties: {
          StatusCode: {
            type: 'integer',
            format: 'int64',
            description: 'Código de Estado da requisição'
          },
          Message: {
            type: 'string',
            description: 'Mensagem de Estado da requisição'
          },
          Content: {
            type: 'object',
            description: 'Conteúdo de retorno da requisição, caso haja'
          }
        }
      }
    },
    responses: {
      default: {
        description: 'Retorno padrão da Api',
        content: {
          'application/json': {
            schema: {
              $ref: '#/components/schemas/Return'
            }
          }
        }
      }
    }
  }
};
