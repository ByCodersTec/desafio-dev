import { Table } from "antd";
import { ColumnsType } from "antd/lib/table";
import { useEffect, useState } from "react";
import cnabApi from "../api/CnabApi";
import { Cnab } from "../model/Cnab";

const columns: ColumnsType<Cnab> = [
  {
    title: "Tipo da Transação",
    dataIndex: "name",
    key: "name",
  },
  {
    title: "Data",
    dataIndex: "date",
    key: "date",
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
    key: "value",
  },
  {
    title: "CPF",
    dataIndex: "documentId",
    key: "documentId",
  },
  {
    title: "Cartão",
    dataIndex: "cardNumber",
    key: "cardNumber",
  },
  {
    title: "Dono da Loja",
    dataIndex: "storeOwner",
    key: "storeOwner",
  },
  {
    title: "Nome da Loja",
    dataIndex: "storeName",
    key: "storeName",
  },
];

export const CnabList = () => {
  const [cnabs, setCnabs] = useState<Cnab[]>([]);
  useEffect(() => {
    cnabApi.list().then((response) => setCnabs(response.data));
  }, []);
  return (
    <div>
      <Table columns={columns} dataSource={cnabs} />
    </div>
  );
};
