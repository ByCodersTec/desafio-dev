export interface CnaeDocValidation {
    lines: Line[]
  }
  
  export interface Line {
    lineContent: string
    columns: Column[]
    parsedLineItem: ParsedLineItem
    isValid: boolean
  }
  
  export interface Column {
    isValid: boolean
    validationPattern: string
    classPropName: string
    length: number
    start: number
    end: number
  }
  
  export interface ParsedLineItem {
    type: number
    date: string
    val: string
    document: string
    card: string
    time: string
    dealer: string
    storeName: string
    lineHash: string
  }