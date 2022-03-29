import { Form, Input, InputNumber, Modal } from "antd";
import { useState } from "react";

interface CnabFiltersProps {
  visible: boolean;
  onClose: () => void;
  onConfirm: (newFilter: CnabFilter) => void;
}

export interface CnabFilter {
  documentId?: string;
  cardNumber?: string;
}

export const CnabFilters = ({
  visible,
  onClose,
  onConfirm,
}: CnabFiltersProps) => {
  const [filter, setFilter] = useState<CnabFilter>({});
  const handleOk = () => {
    onConfirm(filter);
  };
  return (
    <Modal title="Filtros" visible={visible} onOk={handleOk} onCancel={onClose}>
      <Form
        onKeyPress={(e) => {
          if (e.key === "Enter") {
            handleOk();
          }
        }}
      >
        <Form.Item label={<label htmlFor="cpf">CPF</label>}>
          <Input
            id="cpf"
            value={filter.documentId}
            onChange={(e) =>
              setFilter((f) => ({ ...f, documentId: e.target.value }))
            }
          />
        </Form.Item>
        <Form.Item label={<label htmlFor="card-number">Final do Cartão</label>}>
          <Input
            id="card-number"
            value={filter.cardNumber}
            onChange={(e) =>
              setFilter((f) => ({ ...f, cardNumber: e.target.value }))
            }
          />
        </Form.Item>
      </Form>
    </Modal>
  );
};
