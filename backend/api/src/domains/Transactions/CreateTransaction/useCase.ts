import PostgresRepostory from "../../../infra/repository/PostgresRepository";
import { IUseCase } from "../../types";
import { IDTO } from "./dto";


export default class UseCase implements IUseCase {
  constructor(private repository: PostgresRepostory) {}

  async execute(data: IDTO) {
    const exists = await this.repository.exists({ ...data });

    if (exists) {
      throw new Error('Already exists.');
    }

    await this.repository.save(data);
  }
}