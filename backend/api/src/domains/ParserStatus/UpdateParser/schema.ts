import * as Joi from 'joi';
import { SchemaValidator } from '../../../validators/schema';

export const Schema = (req, res, next) => {
  const schema = Joi.object({
    count: Joi.number(),
    message: Joi.string(),
    status: Joi.string().valid(...["pending", "processing", "error", "complete"]),
  }).required().min(1);
  SchemaValidator(req, res, next, schema);
}