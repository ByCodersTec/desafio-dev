import { Cnab } from "./Cnab";

export interface InvalidLine {
  line: number;
  field: string;
  value: string;
}

export interface UploadResponse {
  invalidLines: InvalidLine[];
  savedCnabs: Cnab[];
}
