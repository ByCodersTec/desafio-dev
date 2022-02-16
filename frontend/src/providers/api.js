import axios from 'axios';

const provider = axios.create({
  baseURL: "/"
})

provider.interceptors.response.use(
  (response) => {
    return response;
  }, 
  (error) => {
    const { message } = error.response.data;

    if (message) {
      const customError = new Error(message);
      return Promise.reject(customError);
    }

    return Promise.reject(error);
  }
);

class API {

  post(endpoint, data, headers={}) {
    return provider.post(endpoint, data, { headers })
  }

  remove(endpoint) {
    return provider.delete(endpoint)
  }

  get(endpoint, params={}, headers={}) {
    return provider.get(endpoint, { params, headers })
  }

}

export default new API();