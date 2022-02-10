import { uuid } from 'uuidv4';
import { IRepository } from './IRepository';


export default class PostgresRepostory implements IRepository {
  constructor(private model: any)
  {}

  findById(id: string) {
    return this.model.findByPk(id);
  }

  findOne(where: object) {
    return this.model.findOne({ where });
  }

  findAll(where: object) {
    if (!where) {
      return this.model.findAll();
    }
    return this.model.findAll({ where });
  }
  
  save(data: object) {
    Object.assign(data, { id: uuid() });
    return this.model.create(data);
  }

  async exists(where: object) {
    const find = await this.model.findOne(where);
    return find !== null;
  }

  destroy(id: string) {
    return this.model.destroy({ where: { id } });
  }

  update(id: string, data: object) {
    return this.model.update(data, { where: { id } });
  }

}