import { UploadChangeParam } from "antd/lib/upload";
import { UploadFile } from "antd/lib/upload/interface";
import Axios from "axios";
import { CnabFilter } from "../component/CnabFilters";
import { Cnab } from "../model/Cnab";
import { GenericPage } from "../model/GenericPage";
import { Summary } from "../model/Summary";
import { UploadResponse } from "../model/UploadResponse";

class CnabApi {
  BASE_URL: string;

  constructor() {
    this.BASE_URL = process.env.REACT_APP_BASE_URL! + "/cnab/";
  }

  list = (params: CnabFilter, page: number, size?: number) =>
    Axios.get<GenericPage<Cnab>>(this.BASE_URL, {
      params: { ...params, page, size },
    });

  getSummary = (params: CnabFilter) =>
    Axios.get<Summary>(this.BASE_URL + "summary", {
      params,
    });

  upload = (file: UploadChangeParam<UploadFile>) => {
    const formData = new FormData();
    formData.append("file", file.file as any);
    return Axios.post<UploadResponse>(this.BASE_URL, formData, {
      headers: {
        "content-type": "multipart/form-data",
      },
    });
  };
}

const cnabApi = new CnabApi();
export default cnabApi;
