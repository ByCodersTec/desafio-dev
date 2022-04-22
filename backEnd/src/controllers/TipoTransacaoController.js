import TipoTransacaoService from "../Services/TipoTransacaoService";
import ExpressSwagger from 'express-swagger-delta';
import Return from "../Models/Return";

class TipoTransacaoController
{
	setRoutes()
	{
		ExpressSwagger.Server.addRoute({
			method: 'GET',
			path: '/tipoTransacao',
			tags: ['Tipo Transação'], 
			summary: 'Recupera todos os tipo de transações.',
			responses: {
				200: {
					description: 'Chamada executada com sucesso.',
					$ref: '#/components/responses/default'
				},
				400: {
					description: 'Chamada executada com erros.',
					$ref: '#/components/responses/default'
				},
				401: {
					description: 'Inautorizado! Verifique os headers.',
					$ref: '#/components/responses/default'
				}
			},
			handler: async (req) =>
			{
				return await TipoTransacaoService.getAll();
			}
		});

		ExpressSwagger.Server.addRoute({
			method: 'POST',
			path: '/cnab',
			tags: ['cnab'],
			summary: 'Cadastra um cnab.',
			responses: {
				200: {
					description: 'Chamada executada com sucesso.',
					$ref: '#/components/responses/default'
				},
				400: {
					description: 'Chamada executada com erros.',
					$ref: '#/components/responses/default'
				},
				401: {
					description: 'Inautorizado! Verifique os headers.',
					$ref: '#/components/responses/default'
				}
			},
			handler: async (req) =>
			{
				let { cnab } = req.body;

				if (!cnab)
					return new Return(201, 'Parâmetro `cnab` não encontrado!');
				return await TipoTransacaoService.addNew(cnab);
			}
		});


    }
}
export default new TipoTransacaoController();
