import { Table, Tag } from "antd";
import { ColumnsType } from "antd/lib/table";
import { Cnab } from "../model/Cnab";
import { DefaultPagination } from "../model/GenericPage";

const columns: ColumnsType<Cnab> = [
  {
    title: "Tipo da Transação",
    render: (cnab: Cnab) => {
      return (
        <Tag color={cnab.operationType.type === "I" ? "green" : "red"}>
          {cnab.operationType.description.toUpperCase()}
        </Tag>
      );
    },
  },
  {
    title: "Data",
    dataIndex: "date",
    render: (dateString: string) => {
      let date = new Date(dateString);
      return `${date.toLocaleDateString("pt-BR")} ${date.toLocaleTimeString(
        "pt-BR"
      )}`;
    },
  },
  {
    title: "Valor",
    dataIndex: "value",
  },
  {
    title: "CPF",
    dataIndex: "documentId",
  },
  {
    title: "Cartão",
    dataIndex: "cardNumber",
  },
  {
    title: "Dono da Loja",
    dataIndex: "storeOwner",
  },
  {
    title: "Nome da Loja",
    dataIndex: "storeName",
  },
];

interface CnabListProps {
  cnabs: Cnab[];
  pagination: DefaultPagination;
}

export const CnabList = ({ cnabs, pagination }: CnabListProps) => {
  return (
    <div id="table-wrapper">
      <Table
        columns={columns}
        dataSource={cnabs}
        pagination={false}
        rowKey="id"
      />
    </div>
  );
};
