import PostgresRepostory from "../../../infra/repository/PostgresRepository";
import { IUseCase } from "../../types";


export default class UseCase implements IUseCase {
  constructor(private repository: PostgresRepostory) {}

  async execute(id: string) {
    const exists = await this.repository.exists({ id });
    
    if (!exists) {
      throw new Error(`TransactionType ${id} not exists.`);
    }

    await this.repository.destroy(id);
  }
}