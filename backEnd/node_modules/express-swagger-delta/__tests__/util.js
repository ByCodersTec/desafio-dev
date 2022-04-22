export const headers = {
  ExampleKey: 'gv41f52d',
};

export const jsonReturn = expect.objectContaining({
  statusCode: expect.any(Number),
  message: expect.any(String),
  content: expect.any(Object),
});
