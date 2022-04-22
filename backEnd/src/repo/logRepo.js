import Return from '../Models/Return';
import Log from '../Models/Log';
import SequelizeConnection from '../Connections/SequelizeConnection';

class LogRepository extends SequelizeConnection {
  constructor() {
    super();
  }

  async addLog(obj) {
    let retorno = JSON.stringify(obj.retorno);
    
    retorno = retorno.length >= 8000 ? retorno.substring(0, 7999) : retorno;

    let result = await Log.create({
      metodo: obj.metodo,
      url: obj.url,
      parametro: JSON.stringify(obj.parametro),
      retorno: retorno,
    });

    return new Return(200, 'OK', result);
  }
}

export default new LogRepository();
