package com.pdffromimage;

import androidx.annotation.NonNull;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReadableMap;
import com.pdffromimage.NativePdfFromImageSpec;
import com.pdffromimage.PdfFromImageModuleImpl;

public class PdfFromImageModule extends NativePdfFromImageSpec {
    // declare an instance of the implementation
     final PdfFromImageModuleImpl implementation;


    PdfFromImageModule(ReactApplicationContext context) {
        super(context);
        // initialize the implementation of the module
        implementation = new PdfFromImageModuleImpl(context);
    }

    @Override
    @NonNull
    public String getName() {
        // NAME is a static variable, so we can access it using the class name.
        return PdfFromImageModuleImpl.NAME;
    }

    @Override
    public void foo(double a, double b, Promise promise) {
        // Use the implementation instance to execute the function.
        implementation.foo(a, b, promise);
    }

}
