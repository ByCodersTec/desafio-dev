import Return from '../models/Return';

class AuthService {
  async checkAuth(req) {
    if (!req.headers.examplekey) return new Return(400, 'Not Found req.headers.ExampleKey');

    if (req.headers.examplekey !== 'gv41f52d') return new Return(401, 'Unauthorized');

    return new Return(200, 'OK');
  }
}

export default new AuthService();
