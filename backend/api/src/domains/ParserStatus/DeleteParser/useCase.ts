import PostgresRepostory from "../../../infra/repository/PostgresRepository";
import { IUseCase } from "../../types";

import Transactions from "../../../entities/Transactions";


export default class UseCase implements IUseCase {
  constructor(private repository: PostgresRepostory) {}

  async execute(id: string) {
    const exists = await this.repository.exists({ id });
    
    if (!exists) {
      throw new Error(`Parser Status ${id} not exists.`);
    }
    
    await Transactions.destroy({
      where: {
        parserStatusId: id
      }
    });
    await this.repository.destroy(id);
  }
}