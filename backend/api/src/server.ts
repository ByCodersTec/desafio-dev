import express from 'express';
import router from './routes';

const app = express();

app.use(express.json());
app.use(router);

app.listen(process.env.SERVER_PORT || 3000);
