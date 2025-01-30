import { NativeModules } from 'react-native';
import { type ImageObjectNative } from './NativePdfFromImage';
import { paperSizes } from './contants';
import type { ImageObjectJs } from './types';

// @ts-ignore We want to check whether __turboModuleProxy exitst, it may not
const isTurboModuleEnabled = global.__turboModuleProxy != null;

const pdffromimage = isTurboModuleEnabled
  ? require('./NativePdfFromImage').default
  : NativeModules.PdfFromImage;

export function createPdf(params: ImageObjectJs) {
  // Sanitize document name by removing file extension
  const sanitizedName = params.name.replace(/\.[^/.]+$/, '');

  // Sanitize image paths for iOS
  const sanitizedImagePaths = params.imagePaths.map((path) => {
    if (path.startsWith('file://')) {
      return path.replace('file://', '');
    }
    return path;
  });

  // Determine the paper size
  let paperSize = params.customPaperSize;
  if (!paperSize && params.paperSize) {
    paperSize = paperSizes[params.paperSize];
  }

  // Create a new ImageObject with sanitized paths
  const sanitizedParams: ImageObjectNative = {
    ...params,
    imagePaths: sanitizedImagePaths,
    paperSize,
    name: sanitizedName,
  };

  return pdffromimage.createPdf(sanitizedParams);
}
