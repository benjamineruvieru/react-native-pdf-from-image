export type ImageObjectJs = {
  imagePaths: Array<string>;
  name: string;
  customPaperSize?: {
    height: number;
    width: number;
  };
  paperSize?:
    | 'A0'
    | 'A1'
    | 'A2'
    | 'A3'
    | 'A4'
    | 'A5'
    | 'A6'
    | 'A7'
    | 'A8'
    | 'A9'
    | 'A10'
    | 'B0'
    | 'B1'
    | 'B2'
    | 'B3'
    | 'B4'
    | 'B5'
    | 'C0'
    | 'C1'
    | 'C2'
    | 'C3'
    | 'C4'
    | 'C5'
    | 'C6'
    | 'C7'
    | 'C8'
    | 'C9'
    | 'C10'
    | 'Letter'
    | 'Legal'
    | 'Tabloid'
    | 'Ledger'
    | 'Executive'
    | 'Folio';
};
