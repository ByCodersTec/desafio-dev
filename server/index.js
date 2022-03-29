const express = require('express');
const bodyParser = require('body-parser');
const cookieParser = require('cookie-parser');
const logger = require('morgan');
const cors = require('cors');

const swaggerJsdoc = require('swagger-jsdoc');
const swaggerUi = require('swagger-ui-express');

// Todo: Prefix for route
let prefix = '/api/v1';
// Todo: Add routes
const indexRouter = require('./src/routes/index');
const uploadRouter = require('./src/routes/upload');

const app = express();
app.use(cors());
app.use(logger('dev'));
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));
app.use(cookieParser());
// CORS

// Todo: options for swagger
const options = {
  definition: {
    openapi: '3.0.0',
    info: {
      title: 'Bycoder with Swagger',
      version: '0.0.1',
      description: 'Doc for Swagger API byCode',
      license: {
        name: 'MIT',
        url: 'https://spdx.org/licenses/MIT.html'
      },
      contact: {
        name: 'Antônio Carlos',
        url: 'https://www.linkedin.com/in/acrochajr/',
        email: 'antoniocarlosdarocha@gmail.com'
      }
    },
    servers: [
      {
        url: 'http://localhost:8000'
      }
    ]
  },
  apis: ['./src/routes/upload.js']
};

const specs = swaggerJsdoc(options);
app.use('/api-docs', swaggerUi.serve, swaggerUi.setup(specs));

// Routes -> "src/routes"
app.use('/', indexRouter);
app.use(prefix + '/upload', uploadRouter);
app.use(prefix + '/list', uploadRouter);

app.use((err, req, res, next) => res.json({ error: err.message }));

app.listen(8000, () => {
  console.log('Servidor porta 8000');
});
