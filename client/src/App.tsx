import { useCallback, useEffect, useState } from "react";
import cnabApi from "./api/CnabApi";
import { CnabList } from "./component/CnabList";
import { UploadPage } from "./component/UploadPage";
import { Cnab } from "./model/Cnab";

export const App = () => {
  const [cnabs, setCnabs] = useState<Cnab[]>([]);
  const [loading, setLoading] = useState(false);

  const refresh = useCallback(() => {
    setLoading(true);
    cnabApi
      .list()
      .then((response) => setCnabs(response.data))
      .finally(() => setLoading(false));
  }, []);

  useEffect(refresh, [refresh]);

  return (
    <div id="main-app">
      <h1>Arquivo de Controle Bancário</h1>
      <UploadPage refresh={refresh} />
      <CnabList cnabs={cnabs} />
    </div>
  );
};
