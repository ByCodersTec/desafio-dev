import Sequelize, { Model } from 'sequelize';


export default class TipoTransacao extends Model
{
	static init(sequelize)
	{
		super.init({
			tipo: Sequelize.INTEGER,
			descricao: Sequelize.STRING(30),
			natureza: Sequelize.STRING(20),
			sinal: Sequelize.STRING(2),
		},
			{
				sequelize,
				freezeTableName: true,
			});

		return this;
	}

}
