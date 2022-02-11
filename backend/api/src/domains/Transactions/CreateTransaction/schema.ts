import * as Joi from 'joi';
import { SchemaValidator } from '../../../validators/schema';

export const Schema = (req, res, next) => {
  const schema = Joi.object({
    transactionTypeId: Joi.string().required(),
    storeId: Joi.string().required(),
    value: Joi.number().required(),
    occurence: Joi.date().required(),
    buyerIdentification: Joi.string().required(),
    creditCard: Joi.string().required()
  }).required().min(1);
  SchemaValidator(req, res, next, schema);
}