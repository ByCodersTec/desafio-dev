import Return from '../Models/Return';
import LogRepository from '../repo/logRepo';

class LogService
{
  async authorization(headers)
  {
    if (!headers.authorization)
      return new Return(401, 'No `authorization` sent!');

    return new Return();
  }

  async addLogSuccess(req, res, response)
  {
    LogRepository.addLog({
      metodo: req.method,
      url: req.url,
      parametro: req.params || req.body || {},
      retorno: response,
    });

    return res
      .status(response.statusCode)
      .json(response)
  }

  async addLogError(req, res, error)
  {
    let msg = `Ocorreu um erro ao processar os dados. TypeError: ${error.message}`;

    let stack = error.stack.split('\n');

    let response = new Return(400, msg, stack);
    
    console.log(response)
    //LogRepository.addLog({
    //  metodo: req.method,
    //  url: req.url,
    //  parametro: req.params || req.body,
    //  retorno: response,
    //});

    return res
      .status(response.statusCode)
      .json(response);
  }
}

export default new LogService();
