import { Request, Response } from "express";
import { Op } from "sequelize";
import { v5 as uuidv5, v4 as uuidv4 } from "uuid";

import ParserStatus from "../../entities/ParserStatus";
import PostgresRepostory from "../../infra/repository/PostgresRepository";
import KafkaService from "../../infra/services/kafka";

const repository = new PostgresRepostory(ParserStatus);

const handler = async (request: Request, response: Response) => {
  try {
    if (!request.file) {
      throw new Error("Invalid file");
    }
  
    const { mimetype, buffer } = request.file;
  
    if (mimetype !== "text/plain") {
      throw new Error(`${mimetype} is invalid format. Available formats ["text/plain"]`)
    }

    const value = buffer.toString('base64');
    const { key, hasher } = {
      key: uuidv4(),
      hasher: uuidv5(value, "1b671a64-40d5-491e-99b0-da01ff1f3341")
    }

    const parserStatus = await repository.findOne({ hasher, status: { [Op.not]: "error" } });

    if (parserStatus) {
      throw new Error("Transactions block already processed. Try another!");
    }

    const service = new KafkaService();
    await service.send({
      key,
      value
    });

    repository.save({
      id: key,
      hasher,
      count: 0,
      message: 'Processing started'
    })

    return response.status(200).send();

  } catch (err) {
    return response.status(400).json({
      message: err.message
    })
  }
}

export default handler;