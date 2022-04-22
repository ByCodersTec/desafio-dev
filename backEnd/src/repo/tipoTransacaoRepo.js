import Return from '../Models/Return';
import cnab from '../Models/CNAB'
import TipoTransacao from '../Models/TipoTransacao';
import SequelizeConnection from '../Connections/SequelizeConnection';

class TipoTransacaoRepository extends SequelizeConnection {
	constructor() {
		super();
	}

	async getAll() {
		let results = await TipoTransacao.findAll({
			attributes: [
				'id',
				'tipo',
				'descricao',
				'natureza',
				'sinal',
			]
		});

		return new Return(200, 'OK', results);
	}

	async addNew(obj = {}) {
		let retorno = await cnab.create({
			tipoTransacao: parseInt(obj.tipoTransacao),
			data: obj.data,
			valor: parseFloat(obj.valor),
			cpf: obj.cpf,
			cartao: obj.cartao,
			hora: obj.hora,
			donoLoja: obj.donoLoja,
			nomeLoja: obj.nomeLoja
		});

		if (!retorno.dataValues)
			return new Return(201, 'Falha na alteração dos dados');

		return new Return(200, 'OK', retorno.dataValues);
	}
}

export default new TipoTransacaoRepository();