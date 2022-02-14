import { Sequelize } from 'sequelize';

const {
  DBNAME, DBUSER, DBPASSWORD, DBHOST,
} = process.env;
const sequelize = new Sequelize(DBNAME, DBUSER, DBPASSWORD, {
  host: DBHOST,
  dialect: 'postgres',
});

export default sequelize;
