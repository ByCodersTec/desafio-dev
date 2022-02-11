import Repository from "../../../infra/repository/PostgresRepository";
import Controller from "./controller";
import UseCase from "./useCase";
import Entity from "../../../entities/TransactionsTypes";

const repository = new Repository(Entity);
const useCase = new UseCase(repository);
const deleteController = new Controller(useCase);

export { deleteController };