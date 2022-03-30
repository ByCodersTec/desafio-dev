import { FilterFilled } from "@ant-design/icons";
import { Badge, Button, Pagination, Spin, Tooltip } from "antd";
import { compact } from "lodash";
import { useCallback, useEffect, useState } from "react";
import cnabApi from "./api/CnabApi";
import { CnabFilter, CnabFilters } from "./component/CnabFilters";
import { CnabList } from "./component/CnabList";
import { UploadPage } from "./component/UploadPage";
import { Cnab } from "./model/Cnab";
import { DefaultPagination, DEFAULT_PAGINATION } from "./model/GenericPage";

export const App = () => {
  const [cnabs, setCnabs] = useState<Cnab[]>([]);
  const [loading, setLoading] = useState(false);
  const [modalOpen, setModalOpen] = useState(false);
  const [filter, setFilter] = useState<CnabFilter>({});
  const [pagination, setPagination] = useState<DefaultPagination>({
    ...DEFAULT_PAGINATION,
  });

  const fetchData = useCallback(
    (cnabFilter: CnabFilter, page: number, pageSize?: number) => {
      setLoading(true);

      cnabApi
        .list(cnabFilter, page, pageSize)
        .then((response) => {
          setCnabs(response.data.content);
          setFilter(cnabFilter);
          setPagination({
            page: response.data.page,
            size: response.data.size,
            last: response.data.last,
            totalElements: response.data.totalElements,
            totalPages: response.data.totalPages,
          });
        })
        .finally(() => setLoading(false));
    },
    []
  );

  useEffect(() => fetchData({}, 0), [fetchData]);

  const count = !!filter ? compact(Object.values(filter)).length : 0;

  const handlePaginationChange = useCallback(
    (filter: CnabFilter, newPage: number, newPageSize?: number) => {
      fetchData(filter, newPage - 1, newPageSize);
    },
    []
  );

  const cnalList = (
    <>
      <Pagination
        hideOnSinglePage
        showSizeChanger
        showQuickJumper
        total={pagination.totalElements}
        current={pagination.page + 1}
        pageSize={pagination.size}
        onChange={(newPage, newSize) =>
          handlePaginationChange(filter, newPage, newSize)
        }
      />
      <CnabList cnabs={cnabs} pagination={pagination} />
    </>
  );

  return (
    <div id="main-app">
      <h1>Arquivo de Controle Bancário</h1>
      <UploadPage refresh={() => fetchData(filter, 0)} />
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
          fetchData(f, 0);
        }}
      />
      {loading ? <Spin /> : cnalList}
    </div>
  );
};
