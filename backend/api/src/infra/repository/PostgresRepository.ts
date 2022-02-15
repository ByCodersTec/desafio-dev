import { uuid } from 'uuidv4';
import { IRepository } from './IRepository';


export default class PostgresRepostory implements IRepository {
  constructor(private model: any)
  {}

  findById(id: string) {
    return this.model.findByPk(id);
  }

  findOne(where: object, options: object = {}) {
    return this.model.findOne({ where, ...options });
  }

  findAll(where: object, options: object = {}) {
    if (!where) {
      return this.model.findAll({ ...options });
    }
    return this.model.findAll({ where, ...options });
  }
  
  save(data: any, options: object = {}, customid: string|null = null) {
    if (!data.id) {
      Object.assign(data, { id: uuid() });
    }
    return this.model.create(data, options);
  }

  async exists(where: object) {
    const find = await this.model.findOne({ where });
    return find !== null;
  }

  destroy(id: string) {
    return this.model.destroy({ where: { id } });
  }

  update(id: string, data: object) {
    return this.model.update(data, { where: { id } });
  }

}