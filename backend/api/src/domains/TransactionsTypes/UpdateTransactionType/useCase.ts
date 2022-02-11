import PostgresRepostory from "../../../infra/repository/PostgresRepository";
import { IUseCase } from "../../types";
import { IDTO } from "./dto";


export default class UseCase implements IUseCase {
  constructor(private repository: PostgresRepostory) {}

  async execute(id: string, data: IDTO) {
    const exists = await this.repository.exists({ id });

    if (!exists) {
      throw new Error(`Transaction Type ${id} not exists.`);
    }

    await this.repository.update(id, data);
  }
}