export interface ApiResponse<T> {
    data: T
    status: boolean
    code: number
    error_message: string
    brokenRules: BrokenRule[]
  }

  export interface PagedResponse<T> {
    currentPage: number
    totalPages: number
    pageSize: number
    totalCount: number
    items: T[]
    hasPrivious: boolean
    hasNext: boolean
  }

  export interface BrokenRule {
    field: string
    message: string
  }