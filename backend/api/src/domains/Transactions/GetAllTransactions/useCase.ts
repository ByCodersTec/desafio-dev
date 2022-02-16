import PostgresRepostory from "../../../infra/repository/PostgresRepository";
import { IUseCase } from "../../types";


export default class UseCase implements IUseCase {
  constructor(private repository: PostgresRepostory) {}

  async execute() {
    return await this.repository.findAll(null, 
      {
        include: ['store', 'parser', 'ttypes']
      }
    );
  }
}