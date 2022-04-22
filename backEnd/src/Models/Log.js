import Sequelize, { Model } from 'sequelize';

import { GET_DATE_FORMAT, SET_DATE_FORMAT } from '../config/constants';

export default class Log extends Model
{
	static init(sequelize)
	{
		super.init({
			metodo: Sequelize.STRING(10),
			url: Sequelize.STRING(100),
			parametro: Sequelize.STRING(1000),
			retorno: Sequelize.STRING(8000),
			data: {
				type: Sequelize.DATE,
				defaultValue: SET_DATE_FORMAT,
				get: function ()
				{
					return GET_DATE_FORMAT(this, 'data');
				}
			},
		},
			{
				sequelize,
				freezeTableName: true,
			});

		return this;
	}
}
