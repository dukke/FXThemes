package com.pixelduke.window;

import com.sun.jna.*;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinNT;
import javafx.stage.Window;

import java.util.Arrays;
import java.util.List;

public class Win10ThemeWindowManager implements ThemeWindowManager {

//    interface UXThemeSupport extends Library {
//        UXThemeSupport INSTANCE = Native.load("uxtheme", UXThemeSupport.class);
//
//        WinNT.HRESULT SetWindowTheme(
//                WinDef.HWND hwnd,
//                WString pszSubAppName,
//                WString pszSubIdList
//                );
//    }

//    interface WinUserSupport extends Library {
//        WinUserSupport INSTANCE = Native.load("User32", WinUserSupport.class, W32APIOptions.DEFAULT_OPTIONS);
//
//        WinDef.LRESULT SendMessage(
//                WinDef.HWND hwnd,
//                int   msg,
//                WinDef.WPARAM wParam,
//                WinDef.LPARAM lParam
//        );
//
//        WinDef.BOOL UpdateWindow(
//            WinDef.HWND hWnd
//        );
//    }

    public static class WindowCompositionAttributeData extends Structure implements Structure.ByReference {
        public int Attribute;
        public Pointer Data;
        public int SizeOfData;

        @Override
        protected List<String> getFieldOrder() {
            return Arrays.asList("Attribute", "Data", "SizeOfData");
        }
    }

    public void setDarkModeForWindowFrame(Window window, boolean darkMode) {
//        _AllowDarkModeForWindow(hDlg, g_darkModeEnabled);
//        RefreshTitleBarThemeColor(hDlg);

//        struct WINDOWCOMPOSITIONATTRIBDATA
//        {
//            WINDOWCOMPOSITIONATTRIB Attrib;
//            PVOID pvData;
//            SIZE_T cbData;
//        };

//        enum WINDOWCOMPOSITIONATTRIB
//        {
//            WCA_UNDEFINED = 0,
//            WCA_NCRENDERING_ENABLED = 1,
//            WCA_NCRENDERING_POLICY = 2,
//            WCA_TRANSITIONS_FORCEDISABLED = 3,
//            WCA_ALLOW_NCPAINT = 4,
//            WCA_CAPTION_BUTTON_BOUNDS = 5,
//            WCA_NONCLIENT_RTL_LAYOUT = 6,
//            WCA_FORCE_ICONIC_REPRESENTATION = 7,
//            WCA_EXTENDED_FRAME_BOUNDS = 8,
//            WCA_HAS_ICONIC_BITMAP = 9,
//            WCA_THEME_ATTRIBUTES = 10,
//            WCA_NCRENDERING_EXILED = 11,
//            WCA_NCADORNMENTINFO = 12,
//            WCA_EXCLUDED_FROM_LIVEPREVIEW = 13,
//            WCA_VIDEO_OVERLAY_ACTIVE = 14,
//            WCA_FORCE_ACTIVEWINDOW_APPEARANCE = 15,
//            WCA_DISALLOW_PEEK = 16,
//            WCA_CLOAK = 17,
//            WCA_CLOAKED = 18,
//            WCA_ACCENT_POLICY = 19,
//            WCA_FREEZE_REPRESENTATION = 20,
//            WCA_EVER_UNCLOAKED = 21,
//            WCA_VISUAL_OWNER = 22,
//            WCA_HOLOGRAPHIC = 23,
//            WCA_EXCLUDED_FROM_DDA = 24,
//            WCA_PASSIVEUPDATEMODE = 25,
//            WCA_USEDARKMODECOLORS = 26,
//            WCA_LAST = 27
//        };

        // AllowDarkModeForWindow
        WinDef.HMODULE hModule = com.sun.jna.platform.win32.Kernel32.INSTANCE.GetModuleHandle("uxtheme.dll");
        Kernel32 kernel32 = Native.loadLibrary(Kernel32.class);
        Function function = Function.getFunction(kernel32.GetProcAddress(hModule, 133));
        function.invoke(new Object[]{WindowUtils.getNativeHandleOfStage(window),
                new WinDef.BOOL(darkMode)});


        // SetWindowCompositionAttribute
        //        WINDOWCOMPOSITIONATTRIBDATA data = { WCA_USEDARKMODECOLORS, &dark, sizeof(dark) };
        //        _SetWindowCompositionAttribute(hWnd, &data);
        int WCA_USEDARKMODECOLORS = 26;

        WindowCompositionAttributeData data = new WindowCompositionAttributeData();
        data.Attribute = WCA_USEDARKMODECOLORS;
        data.Data = new WinDef.BOOLByReference(new WinDef.BOOL(darkMode)).getPointer();
        data.SizeOfData = WinDef.BOOL.SIZE;

        NativeLibrary user32 = NativeLibrary.getInstance("user32");
        Function setWindowCompositionAttribute = user32.getFunction("SetWindowCompositionAttribute");
        setWindowCompositionAttribute.invoke(WinNT.HRESULT.class, new Object[] { WindowUtils.getNativeHandleOfStage(window), data });

//        // Update Window (DOESN'T SEEM TO BE WORKING)
//        WinUserSupport.INSTANCE.UpdateWindow(WindowUtils.getNativeHandleOfStage(window));
//
//        // Send Message (THEME_CHANGED) (DOESN'T SEEM TO BE WORKING)
//        int WM_THEME_CHANGED = 0x031A;
//        WinUserSupport.INSTANCE.SendMessage(WindowUtils.getNativeHandleOfStage(window),
//                                            WM_THEME_CHANGED,
//                                            new WinDef.WPARAM(0), new WinDef.LPARAM(0));

        // Force refresh javafx window
        forceWindowRefresh(window);
    }

    private boolean previouslyIncrementedWidth = false;
    private void forceWindowRefresh(Window window) {
        // Hack - we increment / decrement window width to force a refresh so that the Frame decorations are repainted

        // needs to be one pixel at least so that incrementing and then decrementing still refresh the window
        double WINDOW_WIDTH_INCREMENT = 1;

        if (!previouslyIncrementedWidth) {
            window.setWidth(window.getWidth() + WINDOW_WIDTH_INCREMENT);
            previouslyIncrementedWidth = true;
        } else {
            window.setWidth(window.getWidth() - WINDOW_WIDTH_INCREMENT);
            previouslyIncrementedWidth = false;
        }
    }

}
