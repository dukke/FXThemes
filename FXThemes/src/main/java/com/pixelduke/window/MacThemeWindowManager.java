package com.pixelduke.window;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
import com.sun.jna.platform.mac.CoreFoundation;
import javafx.stage.Window;

import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class MacThemeWindowManager implements ThemeWindowManager {
    public enum Backdrop {
        NSAppearanceNameAqua,
        NSAppearanceNameDarkAqua,
        NSAppearanceNameVibrantLight,
        NSAppearanceNameVibrantDark
    }
    @Override
    public void setDarkModeForWindowFrame(Window window, boolean darkMode) {
        NativeLong nsWindow = WindowUtils.getNativeHandleOfStageAsNativeLong(window);
        String nativeAppearanceName = darkMode ? "NSAppearanceNameDarkAqua" : "NSAppearanceNameAqua";
        FXThemesLibrary.INSTANCE.setAppearanceByName(nsWindow, FXThemesLibrary.fromJavaString(nativeAppearanceName));
    }

    public void setWindowFrameAppearance(Window window, Backdrop backdrop) {
        NativeLong nsWindow = WindowUtils.getNativeHandleOfStageAsNativeLong(window);
        FXThemesLibrary.INSTANCE.setAppearanceByName(nsWindow, FXThemesLibrary.fromJavaString(backdrop.toString()));
    }
}

// The interface of the runtime library of Objective-C.
interface FoundationLibrary extends Library {
    NativeLong NULL = new NativeLong(0l);
    FoundationLibrary INSTANCE = Native.load(
            "Foundation",
            FoundationLibrary.class,
            Map.of(Library.OPTION_STRING_ENCODING, StandardCharsets.UTF_8.name()));

    // https://developer.apple.com/documentation/objectivec/1418952-objc_getclass?language=objc
    NativeLong objc_getClass(String className);

    // https://developer.apple.com/documentation/objectivec/1418760-objc_lookupclass?language=objc
    NativeLong objc_lookUpClass(String className);

    // https://developer.apple.com/documentation/objectivec/1418571-sel_getname?language=objc


    // https://developer.apple.com/documentation/objectivec/1418557-sel_registername?language=objc
    Pointer sel_registerName(String selectorName);

    // https://developer.apple.com/documentation/objectivec/1456712-objc_msgsend?language=objc
    // The return type is actually "generic". You might need to declare this function
    // multiple times with different return types if you need them.
    //void objc_msgSend(Pointer receiver, Pointer selector, Object... args);

    NativeLong objc_msgSend(NativeLong receiver, Pointer selector);
    NativeLong objc_msgSend(NativeLong receiver, Pointer selector, Pointer ...obj);
    NativeLong objc_msgSend(NativeLong receiver, Pointer selector, NativeLong ...objAddress);
    // Used by NSString.fromJavaString
    NativeLong objc_msgSend(NativeLong receiver, Pointer selector, byte[] bytes, int len, long encoding);
    NativeLong objc_msgSend(NativeLong receiver, Pointer selector, boolean boolArg);
    NativeLong objc_msgSend(NativeLong receiver, Pointer selector, double floatArg);
    NativeLong objc_msgSend(NativeLong receiver, Pointer selector, Double red, Double green, Double blue, Double alpha);

    NativeLong stringCls = FoundationLibrary.INSTANCE.objc_getClass("NSString");
    Pointer stringSel = FoundationLibrary.INSTANCE.sel_registerName("string");
    Pointer allocSel = FoundationLibrary.INSTANCE.sel_registerName("alloc");
    Pointer initWithBytesLengthEncodingSel = FoundationLibrary.INSTANCE.sel_registerName("initWithBytes:length:encoding:");
    long NSUTF16LittleEndianStringEncoding = 0x94000100;

    static String toNativeString(NativeLong nativeLong) {
        if (NULL.equals(nativeLong)) {
            return null;
        }
        CoreFoundation.CFStringRef cfString = new CoreFoundation.CFStringRef(new Pointer(nativeLong.longValue()));
        try {
            return CoreFoundation.INSTANCE.CFStringGetLength(cfString).intValue() > 0 ? cfString.stringValue() : "";
        } finally {
            cfString.release();
        }
    }
    static NativeLong fromJavaString(String s) {
        if (s.isEmpty()) {
            return FoundationLibrary.INSTANCE.objc_msgSend(stringCls, stringSel);
        }

        byte[] utf16Bytes = s.getBytes(Charset.forName("UTF-16LE"));
        return FoundationLibrary.INSTANCE.objc_msgSend(FoundationLibrary.INSTANCE.objc_msgSend(stringCls, allocSel),
                initWithBytesLengthEncodingSel, utf16Bytes, utf16Bytes.length, NSUTF16LittleEndianStringEncoding);
    }
}

interface FXThemesLibrary extends Library {
    NativeLong NULL = new NativeLong(0l);
    FXThemesLibrary INSTANCE = Native.load(
            "FXThemes",
            FXThemesLibrary.class,
            Map.of(Library.OPTION_STRING_ENCODING, StandardCharsets.UTF_8.name()));

    void setAppearanceByName(NativeLong address, NativeLong address2);
    NativeLong stringCls = FoundationLibrary.INSTANCE.objc_getClass("NSString");
    Pointer stringSel = FoundationLibrary.INSTANCE.sel_registerName("string");
    Pointer allocSel = FoundationLibrary.INSTANCE.sel_registerName("alloc");
    Pointer initWithBytesLengthEncodingSel = FoundationLibrary.INSTANCE.sel_registerName("initWithBytes:length:encoding:");
    long NSUTF16LittleEndianStringEncoding = 0x94000100;

    static String toNativeString(NativeLong nativeLong) {
        if (NULL.equals(nativeLong)) {
            return null;
        }
        CoreFoundation.CFStringRef cfString = new CoreFoundation.CFStringRef(new Pointer(nativeLong.longValue()));
        try {
            return CoreFoundation.INSTANCE.CFStringGetLength(cfString).intValue() > 0 ? cfString.stringValue() : "";
        } finally {
            cfString.release();
        }
    }
    static NativeLong fromJavaString(String s) {
        if (s.isEmpty()) {
            return FoundationLibrary.INSTANCE.objc_msgSend(stringCls, stringSel);
        }

        byte[] utf16Bytes = s.getBytes(Charset.forName("UTF-16LE"));
        return FoundationLibrary.INSTANCE.objc_msgSend(FoundationLibrary.INSTANCE.objc_msgSend(stringCls, allocSel),
                initWithBytesLengthEncodingSel, utf16Bytes, utf16Bytes.length, NSUTF16LittleEndianStringEncoding);
    }
}