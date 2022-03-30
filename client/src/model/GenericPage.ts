export interface DefaultPagination {
  page: number;
  size: number;
  last: boolean;
  totalElements: number;
  totalPages: number;
}

export interface GenericPage<T> extends DefaultPagination {
  content: T[];
}

export const DEFAULT_PAGINATION: DefaultPagination = Object.freeze({
  page: 0,
  size: 20,
  last: true,
  totalElements: 0,
  totalPages: 0,
});
