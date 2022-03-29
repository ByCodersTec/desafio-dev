import { FilterFilled } from "@ant-design/icons";
import { Badge, Button, Spin, Tooltip } from "antd";
import { compact } from "lodash";
import { useCallback, useEffect, useState } from "react";
import cnabApi from "./api/CnabApi";
import { CnabFilter, CnabFilters } from "./component/CnabFilters";
import { CnabList } from "./component/CnabList";
import { UploadPage } from "./component/UploadPage";
import { Cnab } from "./model/Cnab";

export const App = () => {
  const [cnabs, setCnabs] = useState<Cnab[]>([]);
  const [loading, setLoading] = useState(false);
  const [modalOpen, setModalOpen] = useState(false);
  const [filter, setFilter] = useState<CnabFilter>({});

  const fetchData = useCallback((cnabFilter: CnabFilter) => {
    setLoading(true);

    cnabApi
      .list(cnabFilter)
      .then((response) => {
        setCnabs(response.data);
        setFilter(cnabFilter);
      })
      .finally(() => setLoading(false));
  }, []);

  useEffect(() => fetchData({}), [fetchData]);

  const count = !!filter ? compact(Object.values(filter)).length : 0;

  return (
    <div id="main-app">
      <h1>Arquivo de Controle Bancário</h1>
      <UploadPage refresh={() => fetchData(filter)} />
      <Tooltip title="Filtro avançado" placement="bottom">
        <Button
          type="link"
          style={{ fontSize: "2em" }}
          icon={<FilterFilled />}
          onClick={() => setModalOpen((f) => !f)}
        >
          <Badge count={count} style={{ position: "absolute", zIndex: 10 }} />
        </Button>
      </Tooltip>
      <CnabFilters
        visible={modalOpen}
        onClose={() => setModalOpen(false)}
        onConfirm={(f) => {
          setModalOpen(false);
          fetchData(f);
        }}
      />
      {loading ? <Spin /> : <CnabList cnabs={cnabs} />}
    </div>
  );
};
