package com.pixelduke.window;

import com.sun.jna.*;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinNT;
import impl.com.pixelduke.window.win32.windows10.AccentPolicy;
import impl.com.pixelduke.window.win32.windows10.WindowCompositionAttributeData;
import javafx.stage.Window;

import static impl.com.pixelduke.window.win32.windows10.AccentState.*;
import static impl.com.pixelduke.window.win32.windows10.WindowCompositionAttribute.WCA_ACCENT_POLICY;
import static impl.com.pixelduke.window.win32.windows10.WindowCompositionAttribute.WCA_USEDARKMODECOLORS;

public class Win10ThemeWindowManager implements ThemeWindowManager {
    private Function setWindowCompositionAttribute;

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

    public Win10ThemeWindowManager() {
        NativeLibrary user32 = NativeLibrary.getInstance("user32");
        setWindowCompositionAttribute = user32.getFunction("SetWindowCompositionAttribute");
    }

    public void setDarkModeForWindowFrame(Window window, boolean darkMode) {
//        _AllowDarkModeForWindow(hDlg, g_darkModeEnabled);
//        RefreshTitleBarThemeColor(hDlg);

        // AllowDarkModeForWindow
        WinDef.HMODULE hModule = com.sun.jna.platform.win32.Kernel32.INSTANCE.GetModuleHandle("uxtheme.dll");
        Kernel32 kernel32 = Native.loadLibrary(Kernel32.class);
        Function function = Function.getFunction(kernel32.GetProcAddress(hModule, 133));
        function.invoke(new Object[]{WindowUtils.getNativeHandleOfStage(window),
                new WinDef.BOOL(darkMode)});


        // SetWindowCompositionAttribute
        //        WINDOWCOMPOSITIONATTRIBDATA data = { WCA_USEDARKMODECOLORS, &dark, sizeof(dark) };
        //        _SetWindowCompositionAttribute(hWnd, &data);

        WindowCompositionAttributeData data = new WindowCompositionAttributeData();
        data.Attribute = WCA_USEDARKMODECOLORS.getValue();
        data.Data = new WinDef.BOOLByReference(new WinDef.BOOL(darkMode)).getPointer();
        data.SizeOfData = WinDef.BOOL.SIZE;

        setWindowCompositionAttribute(window, data);

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

    public void enableAcrylic(Window window, int opacity, int background) {
        AccentPolicy policy = new AccentPolicy();
        policy.AccentState = ACCENT_ENABLE_BLURBEHIND.getValue();
        policy.GradientColor = (opacity << 24) | (background & 0xFFFFFF);
        policy.write();

        WindowCompositionAttributeData data = new WindowCompositionAttributeData();
        data.Attribute = WCA_ACCENT_POLICY.getValue();
        data.Data = policy.getPointer();
        data.SizeOfData = policy.size();
        data.write();

//        boolean success = SAUser32.INSTANCE.SetWindowCompositionAttribute(hwnd, data.getPointer());
//        if(!success) {
//            System.out.print("Failed to set acrylic: native error " +  Native.getLastError());
//        }
        WinNT.HRESULT result = setWindowCompositionAttribute(window, data);
    }

    private WinNT.HRESULT setWindowCompositionAttribute(Window window, WindowCompositionAttributeData data) {
        return (WinNT.HRESULT) setWindowCompositionAttribute.invoke(WinNT.HRESULT.class, new Object[] { WindowUtils.getNativeHandleOfStage(window), data });
    }
}
