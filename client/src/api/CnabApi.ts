import Axios from "axios";
import { Cnab } from "../model/Cnab";

class CnabApi {
  BASE_URL: string;

  constructor() {
    this.BASE_URL = process.env.REACT_APP_BASE_URL! + "/cnab";
  }

  list = () => {
    return Axios.get<Cnab[]>(this.BASE_URL);
  };
}

const cnabApi = new CnabApi();
export default cnabApi;
