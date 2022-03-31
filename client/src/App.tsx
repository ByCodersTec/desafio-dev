import { FilterFilled } from "@ant-design/icons";
import { Badge, Button, Pagination, Spin, Tag, Tooltip } from "antd";
import { compact } from "lodash";
import { useCallback, useEffect, useState } from "react";
import cnabApi from "./api/CnabApi";
import { CnabFilter, CnabFilters } from "./component/CnabFilters";
import { CnabList } from "./component/CnabList";
import { UploadPage } from "./component/UploadPage";
import { Cnab } from "./model/Cnab";
import { DefaultPagination, DEFAULT_PAGINATION } from "./model/GenericPage";
import { Summary } from "./model/Summary";

export const App = () => {
  const [cnabs, setCnabs] = useState<Cnab[]>([]);
  const [loading, setLoading] = useState(false);
  const [modalOpen, setModalOpen] = useState(false);
  const [filter, setFilter] = useState<CnabFilter>({});
  const [summary, setSummary] = useState<Summary>();
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

  const fetchSummary = useCallback((filter: CnabFilter) => {
    setLoading(true);

    cnabApi
      .getSummary(filter)
      .then((response) => setSummary(response.data))
      .finally(() => setLoading(false));
  }, []);

  useEffect(() => fetchData({}, 0), [fetchData]);

  useEffect(() => fetchSummary(filter), [filter]);

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
          className="filter-icon"
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
      {!!summary && (
        <div className="summary">
          <Tag color={summary.totalValue < 0 ? "red" : "blue"}>
            Total: R$ {summary.totalValue?.toFixed(2)}
          </Tag>
          <Tag>Operações: {summary.totalOperations}</Tag>
        </div>
      )}
      <div>{loading ? <Spin /> : cnalList}</div>
    </div>
  );
};
