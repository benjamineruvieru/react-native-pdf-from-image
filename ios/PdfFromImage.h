#import <React/RCTBridgeModule.h>
#ifdef RCT_NEW_ARCH_ENABLED
#import "generated/RNPdfFromImageSpec/RNPdfFromImageSpec.h"
#endif

@interface PdfFromImage: NSObject <RCTBridgeModule>

@end

 #ifdef RCT_NEW_ARCH_ENABLED
@interface PdfFromImage () <NativePdfFromImageSpec>

@end
 #endif



