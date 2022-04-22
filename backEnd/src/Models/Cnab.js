import Sequelize, { Model } from 'sequelize';

import { GET_DATE_FORMAT, SET_DATE_FORMAT } from '../config/constants'; 

export default class CNAB extends Model 
{
    static init(sequelize) {

        super.init({
            tipoTransacao: Sequelize.INTEGER,
            data: Sequelize.STRING(8),
            valor: Sequelize.DECIMAL(6, 2),
            cpf: Sequelize.STRING(14),
            cartao: Sequelize.STRING(12),
            hora: Sequelize.STRING(6),
            donoLoja: Sequelize.STRING(30),
            nomeLoja: Sequelize.STRING(30),
            dataUpload: {
				type: Sequelize.DATE,
				defaultValue: SET_DATE_FORMAT,
				get: function ()
				{
					return GET_DATE_FORMAT(this, 'dataUpload');
				}
			},
        },
            {
                sequelize,
                freezeTableName: true,
            });

        return this;
    }

    static associate(models) {
        this.hasOne(models.TipoTransacao, { foreignKey: 'id' });
    }
}
