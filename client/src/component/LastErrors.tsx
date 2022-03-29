import { Alert, Table } from "antd";
import { ColumnsType } from "antd/lib/table";
import { InvalidLine } from "../model/UploadResponse";

interface LastErrorsProps {
  lastErrors: InvalidLine[];
}

const columns: ColumnsType<InvalidLine> = [
  {
    title: "Linha",
    dataIndex: "line",
  },
  {
    title: "Campo",
    dataIndex: "field",
  },
  {
    title: "Valor",
    dataIndex: "value",
  },
];

export const LastErrors = ({ lastErrors }: LastErrorsProps) => {
  return (
    <div id="errors-wrapper">
      <Alert message="Houve erros no último envio" type="error" />
      <Table columns={columns} dataSource={lastErrors} rowKey="line" />
    </div>
  );
};
