
const SchemaValidator = (req, res, next, schema) => {
  const options = {
      abortEarly: false,
      allowUnknown: true,
      stripUnknown: true
  };
  const { error, value } = schema.validate(req.body, options);
  if (error) {
      return res.status(400).json({
          "error": true,
          "message": `Validation error: ${error.details.map(x => x.message).join(', ')}`
      });
  } else {
      req.body = value;
      next();
  }
}

export { SchemaValidator };