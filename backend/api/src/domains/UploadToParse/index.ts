import { Request, Response } from "express";
import { uuid } from "uuidv4";
import KafkaService from "../../infra/services/kafka";


const handler = async (request: Request, response: Response) => {
  try {
    if (!request.file) {
      throw new Error("Invalid file");
    }
  
    const { mimetype, buffer } = request.file;
  
    if (mimetype !== "text/plain") {
      throw new Error(`${mimetype} is invalid format. Available formats ["text/plain"]`)
    }
    
    const service = new KafkaService();
    await service.send({
      key: uuid(),
      value: buffer.toString('base64')
    })

    return response.status(200).send();

  } catch (err) {
    return response.status(400).json({
      message: err.message
    })
  }
}

export default handler;