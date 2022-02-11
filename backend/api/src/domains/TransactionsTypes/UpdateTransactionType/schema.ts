import * as Joi from 'joi';
import { SchemaValidator } from '../../../validators/schema';

export const Schema = (req, res, next) => {
  const schema = Joi.object({
    label: Joi.string(),
    type: Joi.string().valid(...["in", "out"]),
  }).required().min(1);
  SchemaValidator(req, res, next, schema);
}