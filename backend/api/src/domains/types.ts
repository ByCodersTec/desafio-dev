import { Request, Response } from "express"

export interface IUseCase {
  execute: (...args: any) => Promise<any>
}

export interface IController {
  handle: (request: Request, response: Response) => Promise<Response>
}