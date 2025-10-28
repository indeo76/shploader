
export interface ImportResult {
  fileName: string;
  totalCount: number,
  savedCount: number,
  errors: ShpError[]
}

export interface ShpError {
  key: string;
  value: string;
  error: string;
}
