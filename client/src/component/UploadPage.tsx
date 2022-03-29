import { InboxOutlined } from "@ant-design/icons";
import { message } from "antd";
import { UploadChangeParam } from "antd/lib/upload";
import Dragger from "antd/lib/upload/Dragger";
import { UploadFile } from "antd/lib/upload/interface";
import cnabApi from "../api/CnabApi";

export const UploadPage = () => {
  const uploadFile = (uploadFile: UploadChangeParam<UploadFile>) => {
    cnabApi
      .upload(uploadFile)
      .then(() => message.success("Arquivo enviado com sucesso"))
      .catch((error) => {
        message.error("Não foi possível fazer o upload do arquivo", error);
      });
  };
  return (
    <div id="dragger-wrapper">
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
    </div>
  );
};
