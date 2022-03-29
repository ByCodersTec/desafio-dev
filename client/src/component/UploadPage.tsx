import { InboxOutlined } from "@ant-design/icons";
import Dragger from "antd/lib/upload/Dragger";

export const UploadPage = () => {
  return (
    <div>
      <Dragger>
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
