const env = process.env;

const config = {
  db: {
    host: env.DB_HOST || 'localhost',
    port: env.DB_PORT || '5438',
    user: env.DB_USER || 'postgres',
    password: env.DB_PASSWORD || 'postgres',
    database: env.DB_NAME || 'bycoder'
  }
};

module.exports = config;
