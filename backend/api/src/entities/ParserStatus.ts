import { ParserStatus as Schema } from '../infra/database/schemas/ParserStatus';

export default class ParserStatus extends Schema {
  private readonly id;
  private hasher;
  private count;
  private message;
  private status;
}