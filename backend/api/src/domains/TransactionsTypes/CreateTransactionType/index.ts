import Repository from "../../../infra/repository/PostgresRepository";
import Controller from "./controller";
import UseCase from "./useCase";
import Entity from "../../../entities/TransactionsTypes";
import { Schema as createSchema } from './schema';

const repository = new Repository(Entity);
const useCase = new UseCase(repository);
const createController = new Controller(useCase);

export { createController, createSchema };