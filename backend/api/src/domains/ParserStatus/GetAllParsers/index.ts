import Repository from "../../../infra/repository/PostgresRepository";
import Controller from "./controller";
import UseCase from "./useCase";
import Entity from "../../../entities/ParserStatus";

const repository = new Repository(Entity);
const useCase = new UseCase(repository);
const getAllController = new Controller(useCase);

export { getAllController };