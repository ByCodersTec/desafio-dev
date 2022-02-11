import PostgresRepostory from "../../../infra/repository/PostgresRepository";
import { IUseCase } from "../../types";
import { IDTO } from "./dto";


export default class UseCase implements IUseCase {
  constructor(private repository: PostgresRepostory) {}

  async execute(data: IDTO) {
    const { code } = data;
    const exists = await this.repository.exists({ code });

    if (exists) {
      throw new Error(`Code ${code} already exists.`);
    }

    await this.repository.save(data);
  }
}