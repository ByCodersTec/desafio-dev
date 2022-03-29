import { CnabList } from "./component/CnabList";
import { UploadPage } from "./component/UploadPage";

export const App = () => {
  return (
    <div>
      <h1>Arquivo de Controle Bancário</h1>
      <UploadPage />
      <CnabList />
    </div>
  );
};
