
export interface IRepository {
  findById: (id: string) => Promise<void>;
  findOne: (where: object) => Promise<void>;
  findAll: (where: object) => Promise<void>;
  save: (data: object) => Promise<void>;
  exists: (where: object) => Promise<boolean>;
  destroy: (id: string) => Promise<void>;
  update: (id: string, data: object) => Promise<void>;
}