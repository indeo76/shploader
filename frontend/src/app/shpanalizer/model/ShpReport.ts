export interface ShpReport {
  globalProperties: string[];
  rows: ShpReportRow[];
}

export interface ShpReportRow {
  fileName: string;
  kodNowy: string;
  kodStary: string;
  rodzajGeometrii: string;
  count: number;
  properties: string[];
  kodGeoinfo?: KodGeoinfo;

  dkp_N: string;
  dkp_D: string;
  gvalue: string;
  xcode_C: number;
  xcode_N: string;
  xcode_D: string;
  gname: string;
}

export interface KodGeoinfo {
  kod_mnemoniczny: string;
  opis_kodu: string;
  rodzaj_geometrii: number,
  tabela: string;
}
