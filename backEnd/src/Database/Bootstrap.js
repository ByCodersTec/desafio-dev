const dotenv = require('dotenv');

if (process.env.NODE_ENV) {
  dotenv.config({
    path: process.env.NODE_ENV.trim() === 'PRODUCTION' ? './Env/Production' : './Env/Homolog',
  });
} else {
  dotenv.config({
    path: './Env/Homolog',
  });
}
