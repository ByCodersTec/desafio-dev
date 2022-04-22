import request from 'supertest';
import api from '../../example/configs/server';

describe('Ping Tests', () => {
  afterAll(async () => {
    await new Promise((resolve) => setTimeout(() => resolve(), 500));
  });

  it('should return the API status according to the swagger route - /', async () => {
    const response = await request(api.server.app).get('/api/');

    expect(response.status).toBe(200);
    expect(response.body).toEqual(expect.any(Object));
    expect(response.body).toEqual(
      expect.objectContaining({
        StatusCode: 200,
        Message: expect.any(String),
        Date: expect.any(String),
      })
    );
  });
});
