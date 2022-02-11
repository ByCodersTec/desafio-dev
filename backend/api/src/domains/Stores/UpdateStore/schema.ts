import * as Joi from 'joi';
import { SchemaValidator } from '../../../validators/schema';

export const Schema = (req, res, next) => {
  const schema = Joi.object({
    storeOwner: Joi.string(),
    storeName: Joi.string(),
  }).required().min(1);
  SchemaValidator(req, res, next, schema);
}