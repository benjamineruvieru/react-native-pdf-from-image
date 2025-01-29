import PdfFromImage, { type ImageObjectNative } from './NativePdfFromImage';
import { paperSizes } from './contants';
import type { ImageObjectJs } from './types';

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

  return PdfFromImage.createPdf(sanitizedParams);
}
