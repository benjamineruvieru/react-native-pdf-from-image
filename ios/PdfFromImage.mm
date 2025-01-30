#import "PdfFromImage.h"
#import <React/RCTConvert.h>

@implementation PdfFromImage
RCT_EXPORT_MODULE()

- (NSDictionary *)createPdf:
    (JS::NativePdfFromImage::ImageObjectNative &)imageObject {

  NSLog(@"Starting PDF creation");

  ;

  NSString *documentName = imageObject.name() ?: @"document";

  facebook::react::LazyVector<NSString *> imagePathsLazyVector =
      imageObject.imagePaths();

  NSArray *imagePaths = RCTConvertVecToArray(imagePathsLazyVector);

  // Determine paper size
  CGSize paperSize = CGSizeZero;
  if (imageObject.paperSize()) {
    paperSize = CGSizeMake(imageObject.paperSize()->width(),
                           imageObject.paperSize()->height());
  }

  NSLog(@"Document name: %@", documentName);
  NSLog(@"Image paths: %@", imagePaths);
  NSLog(@"Paper size: Width = %.2f, Height = %.2f", paperSize.width,
        paperSize.height);

  // Create PDF context
  NSString *tempPath = [NSTemporaryDirectory()
      stringByAppendingPathComponent:[NSString stringWithFormat:@"%@.pdf",
                                                                documentName]];
  UIGraphicsBeginPDFContextToFile(tempPath, CGRectZero, nil);

  for (NSString *imagePath in imagePaths) {
    UIImage *image = [UIImage imageWithContentsOfFile:imagePath];
    if (image) {

      NSLog(@"Successfully loaded image from path: %@", imagePath);
      // Begin new PDF page with the size of the image
      CGRect pageRect;
      if (!CGSizeEqualToSize(paperSize, CGSizeZero)) {
        pageRect = CGRectMake(0, 0, paperSize.width, paperSize.height);
        // Calculate scaling factors
        CGFloat widthRatio = paperSize.width / image.size.width;
        CGFloat heightRatio = paperSize.height / image.size.height;
        CGFloat scale = MIN(widthRatio, heightRatio);

        CGFloat scaledWidth = image.size.width * scale;
        CGFloat scaledHeight = image.size.height * scale;
        CGFloat xOffset = (paperSize.width - scaledWidth) / 2;
        CGFloat yOffset = (paperSize.height - scaledHeight) / 2;

        UIGraphicsBeginPDFPageWithInfo(pageRect, nil);
        CGRect imageRect =
            CGRectMake(xOffset, yOffset, scaledWidth, scaledHeight);
        [image drawInRect:imageRect];
      } else {
        pageRect = CGRectMake(0, 0, image.size.width, image.size.height);
        UIGraphicsBeginPDFPageWithInfo(pageRect, nil);
        [image drawInRect:pageRect];
      }

    } else {
      NSLog(@"Failed to load image from path: %@", imagePath);
    }
  }

  UIGraphicsEndPDFContext();
  NSLog(@"PDF created at path: %@", tempPath);
  return @{@"filePath" : tempPath};
}

#ifdef RCT_NEW_ARCH_ENABLED
- (std::shared_ptr<facebook::react::TurboModule>)getTurboModule:
    (const facebook::react::ObjCTurboModule::InitParams &)params {
  return std::make_shared<facebook::react::NativePdfFromImageSpecJSI>(params);
}
#endif

@end
