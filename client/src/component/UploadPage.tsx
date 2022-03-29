import { InboxOutlined } from "@ant-design/icons";
import { message, Spin } from "antd";
import { UploadChangeParam } from "antd/lib/upload";
import Dragger from "antd/lib/upload/Dragger";
import { UploadFile } from "antd/lib/upload/interface";
import { useState } from "react";
import cnabApi from "../api/CnabApi";

interface UploadPageProps {
  refresh: () => void;
}

export const UploadPage = ({ refresh }: UploadPageProps) => {
  const [loading, setLoading] = useState(false);

  const uploadFile = (uploadFile: UploadChangeParam<UploadFile>) => {
    setLoading(true);
    cnabApi
      .upload(uploadFile)
      .then(() => {
        refresh();
        message.success("Arquivo enviado com sucesso");
      })
      .catch((error) => {
        message.error("Não foi possível fazer o upload do arquivo", error);
      })
      .finally(() => setLoading(false));
  };

  return (
    <div id="dragger-wrapper">
      {loading ? (
        <Spin />
      ) : (
        <Dragger
          onChange={(info) => uploadFile(info)}
          beforeUpload={() => {
            // Prevent default antd behavior
            return false;
          }}
          fileList={[]}
          style={{ backgroundColor: "#ddd" }}
        >
          <p className="ant-upload-drag-icon">
            <InboxOutlined />
          </p>
          <p className="ant-upload-text">
            Clique aqui ou arraste um arquivo para fazer upload
          </p>
        </Dragger>
      )}
    </div>
  );
};
