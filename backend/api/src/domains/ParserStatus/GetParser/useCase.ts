import Transactions from "../../../entities/Transactions";
import PostgresRepostory from "../../../infra/repository/PostgresRepository";
import { IUseCase } from "../../types";


export default class UseCase implements IUseCase {
  constructor(private repository: PostgresRepostory) {}

  async execute(id: string) {
    const data = await this.repository.findOne({ id }, {
      include: [{
        model: Transactions,
        as: 'transactions',
        include: ['store', 'ttypes']
      }]
    });

    if (!data) {
      throw new Error(`Error: Store ${id} not exists.`);
    }

    return data;
  }
}