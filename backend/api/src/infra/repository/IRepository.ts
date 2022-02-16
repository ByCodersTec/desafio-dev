
export interface IRepository {
  findById: (id: string, options: object) => Promise<any>;
  findOne: (where: object, options: object) => Promise<any>;
  findAll: (where: object, options: object) => Promise<any>;
  save: (data: object) => Promise<any>;
  exists: (where: object) => Promise<boolean>;
  destroy: (id: string) => Promise<any>;
  update: (id: string, data: object) => Promise<any>;
}