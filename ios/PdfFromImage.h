
#ifdef RCT_NEW_ARCH_ENABLED
#import "generated/RNPdfFromImageSpec/RNPdfFromImageSpec.h"

@interface PdfFromImage : NSObject <NativePdfFromImageSpec>


#else
#import <React/RCTBridgeModule.h>

@interface PdfFromImage: NSObject <RCTBridgeModule>

#endif


@end





