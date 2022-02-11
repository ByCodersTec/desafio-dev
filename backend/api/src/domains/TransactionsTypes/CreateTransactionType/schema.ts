import * as Joi from 'joi';
import { SchemaValidator } from '../../../validators/schema';

export const Schema = (req, res, next) => {
  const schema = Joi.object({
    code: Joi.number().required(),
    label: Joi.string().required(),
    type: Joi.string().valid(...["in", "out"]).required(),
  }).required().min(1);
  SchemaValidator(req, res, next, schema);
}