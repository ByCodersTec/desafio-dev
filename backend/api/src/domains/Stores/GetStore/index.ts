import Repository from "../../../infra/repository/PostgresRepository";
import Controller from "./controller";
import UseCase from "./useCase";
import Entity from "../../../entities/Stores";

const repository = new Repository(Entity);
const useCase = new UseCase(repository);
const getController = new Controller(useCase);

export { getController };