import { Table, Tag } from "antd";
import { ColumnsType } from "antd/lib/table";
import { Cnab } from "../model/Cnab";
import { DefaultPagination } from "../model/GenericPage";

const columns: ColumnsType<Cnab> = [
  {
    title: "Tipo da Transação",
    align: "right",
    render: (cnab: Cnab) => {
      return (
        <Tag color={cnab.operationType.type === "I" ? "blue" : "red"}>
          {cnab.operationType.description.toUpperCase()}
        </Tag>
      );
    },
  },
  {
    title: "Data",
    dataIndex: "date",
    align: "center",
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
    align: "center",
    render: (value: number) => (
      <span className={value < 0 ? "output-operation" : "input-operation"}>
        R$ {value.toFixed(2)}
      </span>
    ),
  },
  {
    title: "CPF",
    dataIndex: "documentId",
    align: "center",
  },
  {
    title: "Cartão",
    dataIndex: "cardNumber",
    align: "center",
  },
  {
    title: "Dono da Loja",
    dataIndex: "storeOwner",
    align: "center",
  },
  {
    title: "Nome da Loja",
    dataIndex: "storeName",
    align: "center",
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
