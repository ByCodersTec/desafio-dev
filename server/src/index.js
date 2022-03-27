const express = require('express');
// const cors = require('cors');
const bodyParser = require('body-parser');
const cookieParser = require('cookie-parser');
const logger = require('morgan');

const indexRouter = require('./routes/index');
const uploadRouter = require('./routes/upload_file');

const app = express();
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));

app.use(logger('dev'));
app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use(cookieParser());

app.use('/', indexRouter);
app.use('/upload', uploadRouter);

// const corsOptions = {
//   origin: '*',
//   optionsSuccessStatus: 200
// };
// app.use(cors(corsOptions));

// app.use((err, req, res, next) => res.json({ error: err.message }));

app.listen(8000, () => {
  console.log('Servidor porta 8000');
});
// app.on('error', onError);
// app.on('listening', onListening);
