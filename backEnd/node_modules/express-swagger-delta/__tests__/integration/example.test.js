import request from 'supertest';
import api from '../../example/configs/server';
import { headers, jsonReturn } from '../util';

describe('Example Tests', () => {
  afterAll(async () => {
    await new Promise((resolve) => setTimeout(() => resolve(), 500));
  });

  it('should return authentication failure', async () => {
    const response = await request(api.server.app).get('/api/example');

    expect(response.status).toBe(400);
    expect(response.body).toEqual(jsonReturn);
  });

  it('should return unauthorized authentication', async () => {
    const response = await request(api.server.app).get('/api/example').set({
      ExampleKey: 'xxxxxxxx',
    });

    expect(response.status).toBe(401);
    expect(response.body).toEqual(jsonReturn);
  });

  it('should return successful authentication', async () => {
    const response = await request(api.server.app).get('/api/example').set(headers);

    expect(response.status).toBe(200);
    expect(response.body).toEqual(jsonReturn);
    expect(response.body.content).toEqual(
      expect.objectContaining({
        date: expect.any(String),
      })
    );
  });

  it('should return a personalized message and today`s date', async () => {
    const response = await request(api.server.app).get('/api/example/HelloWorld').set(headers);

    expect(response.status).toBe(200);
    expect(response.body).toEqual(jsonReturn);
    expect(response.body.content).toEqual(
      expect.objectContaining({
        text: 'HelloWorld',
        date: expect.any(String),
      })
    );
  });
});
