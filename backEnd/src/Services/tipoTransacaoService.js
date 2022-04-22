import TipoTransacaoRepository from '../repo/tipoTransacaoRepo';

class TipoTransacaoService
{
	async getAll()
	{
		return await TipoTransacaoRepository.getAll();
	}

	async addNew(cnab)
	{
		return await TipoTransacaoRepository.addNew(cnab)
	}
}
export default new TipoTransacaoService();