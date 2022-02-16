import Transactions from "../../../entities/Transactions";
import PostgresRepostory from "../../../infra/repository/PostgresRepository";
import { IUseCase } from "../../types";


export default class UseCase implements IUseCase {
  constructor(private repository: PostgresRepostory) {}

  async execute() {
    const data = await this.repository.findAll({}, {
      include: [{
        model: Transactions,
        as: 'transactions',
        include: ['store', 'ttypes']
      }]
    });

    return data;
  }
}