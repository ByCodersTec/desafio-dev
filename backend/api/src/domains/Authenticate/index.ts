import { Request, Response } from "express";
import * as jwt from 'jsonwebtoken';
import { uuid } from "uuidv4";

const handler = async (request: Request, response: Response) => {
  try {
    const { email, password } = request.body;

    if (email !== "desafiodev@email.com" || password !== "desafiodev") {
      throw new Error("Invalid user credentials");
    }

    const token = jwt.sign({ userId: uuid() }, process.env.SECRET, {
      expiresIn: 3600
    });

    return response.status(200).json({
      auth: true, 
      token
    });

  } catch (err) {
    return response.status(400).json({
      message: err.message
    })
  }
}

export default handler;