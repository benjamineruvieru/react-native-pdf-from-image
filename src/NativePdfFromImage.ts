import type { TurboModule } from 'react-native';
import { TurboModuleRegistry } from 'react-native';

export type ImageObjectNative = {
  imagePaths: Array<string>;
  name: string;
  paperSize?: {
    height: number;
    width: number;
  };
};

export type File = {
  filePath: string;
};

export interface Spec extends TurboModule {
  createPdf(imageObject: ImageObjectNative): File;
}

export default TurboModuleRegistry.getEnforcing<Spec>('PdfFromImage');
