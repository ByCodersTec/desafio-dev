import request from 'supertest';
import api from '../../example/configs/server';

describe('Documentation Tests', () => {
  afterAll(async () => {
    await new Promise((resolve) => setTimeout(() => resolve(), 500));
  });

  it('should return the html from the swagger documentation', async () => {
    const response = await request(api.server.app).get('/api/documentation/');

    expect(response.status).toBe(200);
    expect(response.header['content-type']).toBe('text/html; charset=utf-8');
  });
});
