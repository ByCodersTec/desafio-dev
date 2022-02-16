import { Request, Response } from 'express';
import { IController } from '../../types';
import UseCase from './useCase';


export default class Controller implements IController {
  constructor(private useCase: UseCase)
  {}

  async handle(request: Request, response: Response) {
    try {
      const { id } = request.params;
      await this.useCase.execute(id, request.body);
      
      return response.status(200).send();
    } catch (err) {
      console.log(err);
      return response.status(400).json({
        message: err.message || 'Error',
      });
    }
  }
}