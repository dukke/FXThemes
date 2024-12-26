package com.pixelduke.window;

import com.sun.jna.platform.win32.WinDef;
import impl.com.pixelduke.window.win32.windows11.*;
import javafx.scene.paint.Color;
import javafx.stage.Window;

public class Win11ThemeWindowManager implements ThemeWindowManager {

    public enum Backdrop {
        NONE,
        MICA,
        MICA_ALT,
        ACRYLIC
    }

    public enum CornerPreference {
        RECTANGULAR,
        ROUND,
        ROUND_SMALL
    }

    public void setDarkModeForWindowFrame(Window window, boolean darkMode) {
        if (window.isShowing()) {
            DWM.setWindowAttribute(
                    WindowUtils.getNativeHandleOfStage(window),
                    DWMA_WINDOW_ATTRIBUTE.DWMWA_USE_IMMERSIVE_DARK_MODE.getValue(),
                    darkMode
            );
            return;
        }
        window.setOnShown(event -> DWM.setWindowAttribute(
                WindowUtils.getNativeHandleOfStage(window),
                DWMA_WINDOW_ATTRIBUTE.DWMWA_USE_IMMERSIVE_DARK_MODE.getValue(),
                darkMode
        ));
    }

    /**     BELOW STILL NOT WORKING
    public void setWindowIconVisible(Stage window, boolean isVisible) {

        NativeLong windowStyle = new NativeLong(0x00000001L);
//        User32Support.INSTANCE.SetWindowLongPtrA(
//            WindowUtils.getNativeHandleOfStage(window),
//            GWL_EXSTYLE,
//            windowStyle
//        );

        BaseTSD.LONG_PTR style = User32.INSTANCE.GetWindowLongPtr(WindowUtils.getNativeHandleOfStage(window), GWL_EXSTYLE);

        Pointer pointer = new WinDef.LONGByReference(new WinDef.LONG(0x00000001L)).getPointer();

        User32.INSTANCE.SetWindowLongPtr(
                WindowUtils.getNativeHandleOfStage(window),
                GWL_EXSTYLE,
                pointer);

        User32.INSTANCE.SetWindowPos(WindowUtils.getNativeHandleOfStage(window),
                null,
                0,
                0,
                0,
                0,
                SWP_FRAMECHANGED | SWP_NOMOVE | SWP_NOSIZE | SWP_NOZORDER);

        window.setResizable(true);

    } **/

    public void setWindowBorderColor(Window window, Color color) {
        DWM.setWindowAttribute(
                WindowUtils.getNativeHandleOfStage(window),
                DWMA_WINDOW_ATTRIBUTE.DWMWA_BORDER_COLOR.getValue(),
                toRGBInt(color)
        );
    }

    public void setWindowFrameColor(Window window, Color color) {
        DWM.setWindowAttribute(
                WindowUtils.getNativeHandleOfStage(window),
                DWMA_WINDOW_ATTRIBUTE.DWMWA_CAPTION_COLOR.getValue(),
                toRGBInt(color)
        );
    }

    public void setWindowTextColor(Window window, Color color) {
        DWM.setWindowAttribute(
                WindowUtils.getNativeHandleOfStage(window),
                DWMA_WINDOW_ATTRIBUTE.DWMWA_TEXT_COLOR.getValue(),
                toRGBInt(color));
    }

    public void setWindowBackdrop(Window window, Backdrop backdrop) {
        switch (backdrop) {
            case NONE -> DwmSupport.INSTANCE.DwmSetWindowAttribute(
                            WindowUtils.getNativeHandleOfStage(window),
                            DWMA_WINDOW_ATTRIBUTE.DWMWA_SYSTEMBACKDROP_TYPE.getValue(),
                            new WinDef.DWORDByReference(new WinDef.DWORD(DWM_SYSTEMBACKDROP_TYPE.NONE.getValue())),
                            WinDef.DWORD.SIZE
                        );
            case MICA -> DwmSupport.INSTANCE.DwmSetWindowAttribute(
                            WindowUtils.getNativeHandleOfStage(window),
                            DWMA_WINDOW_ATTRIBUTE.DWMWA_SYSTEMBACKDROP_TYPE.getValue(),
                            new WinDef.DWORDByReference(new WinDef.DWORD(DWM_SYSTEMBACKDROP_TYPE.MICA.getValue())),
                            WinDef.DWORD.SIZE
                        );
            case MICA_ALT -> DwmSupport.INSTANCE.DwmSetWindowAttribute(
                                WindowUtils.getNativeHandleOfStage(window),
                                DWMA_WINDOW_ATTRIBUTE.DWMWA_SYSTEMBACKDROP_TYPE.getValue(),
                                new WinDef.DWORDByReference(new WinDef.DWORD(DWM_SYSTEMBACKDROP_TYPE.MICA_ALT.getValue())),
                                WinDef.DWORD.SIZE
                            );
            case ACRYLIC -> DwmSupport.INSTANCE.DwmSetWindowAttribute(
                                WindowUtils.getNativeHandleOfStage(window),
                                DWMA_WINDOW_ATTRIBUTE.DWMWA_SYSTEMBACKDROP_TYPE.getValue(),
                                new WinDef.DWORDByReference(new WinDef.DWORD(DWM_SYSTEMBACKDROP_TYPE.ACRYLIC.getValue())),
                                WinDef.DWORD.SIZE
                            );
        }
    }

    /**
     * Sets the "roundness" of the corners of the window according to the passed in CornerPreference.
     * Setting the corners of a transparent or undecorated Stage to be round will also make it display native window decorations (drop shadows, etc).
     *
     * @param window the window which you want to change
     * @param cornerPreference the corner preference of the window
     */
    public void setWindowCornerPreference(Window window, CornerPreference cornerPreference) {
        switch (cornerPreference) {
            case RECTANGULAR -> DwmSupport.INSTANCE.DwmSetWindowAttribute(
                    WindowUtils.getNativeHandleOfStage(window),
                    DWMA_WINDOW_ATTRIBUTE.DWMWA_WINDOW_CORNER_PREFERENCE.getValue(),
                    new WinDef.DWORDByReference(new WinDef.DWORD(DWM_CORNER_PREFERENCE.RECTANGULAR.getValue())),
                    WinDef.DWORD.SIZE
            );
            case ROUND -> DwmSupport.INSTANCE.DwmSetWindowAttribute(
                    WindowUtils.getNativeHandleOfStage(window),
                    DWMA_WINDOW_ATTRIBUTE.DWMWA_WINDOW_CORNER_PREFERENCE.getValue(),
                    new WinDef.DWORDByReference(new WinDef.DWORD(DWM_CORNER_PREFERENCE.ROUND.getValue())),
                    WinDef.DWORD.SIZE
            );
            case ROUND_SMALL -> DwmSupport.INSTANCE.DwmSetWindowAttribute(
                    WindowUtils.getNativeHandleOfStage(window),
                    DWMA_WINDOW_ATTRIBUTE.DWMWA_WINDOW_CORNER_PREFERENCE.getValue(),
                    new WinDef.DWORDByReference(new WinDef.DWORD(DWM_CORNER_PREFERENCE.ROUND_SMALL.getValue())),
                    WinDef.DWORD.SIZE
            );
        }
    }

    /**        BELOW STILL NOT WORKING
    public void setWindowBackdrop(Dialog dialog, Backdrop backdrop) {
        Stage dialogStage = (Stage) dialog.getDialogPane().getScene().getWindow();
        String tempTitle = "XYUZADCSD";
        String dialogTitle = dialogStage.getTitle();
        dialogStage.setTitle(tempTitle);
        WinDef.HWND hWnd = User32.INSTANCE.FindWindow(null, tempTitle);
        dialogStage.setTitle(dialogTitle);

        switch (backdrop) {
            case NONE -> DwmSupport.INSTANCE.DwmSetWindowAttribute(
                    hWnd,
                    DWMA_WINDOW_ATTRIBUTE.DWMWA_SYSTEMBACKDROP_TYPE.getValue(),
                    new WinDef.DWORDByReference(new WinDef.DWORD(DWM_SYSTEMBACKDROP_TYPE.NONE.getValue())),
                    WinDef.DWORD.SIZE
            );
            case MICA -> DwmSupport.INSTANCE.DwmSetWindowAttribute(
                    hWnd,
                    DWMA_WINDOW_ATTRIBUTE.DWMWA_SYSTEMBACKDROP_TYPE.getValue(),
                    new WinDef.DWORDByReference(new WinDef.DWORD(DWM_SYSTEMBACKDROP_TYPE.MICA.getValue())),
                    WinDef.DWORD.SIZE
            );
            case MICA_ALT -> DwmSupport.INSTANCE.DwmSetWindowAttribute(
                    hWnd,
                    DWMA_WINDOW_ATTRIBUTE.DWMWA_SYSTEMBACKDROP_TYPE.getValue(),
                    new WinDef.DWORDByReference(new WinDef.DWORD(DWM_SYSTEMBACKDROP_TYPE.MICA_ALT.getValue())),
                    WinDef.DWORD.SIZE
            );
            case ACRYLIC -> DwmSupport.INSTANCE.DwmSetWindowAttribute(
                    hWnd,
                    DWMA_WINDOW_ATTRIBUTE.DWMWA_SYSTEMBACKDROP_TYPE.getValue(),
                    new WinDef.DWORDByReference(new WinDef.DWORD(DWM_SYSTEMBACKDROP_TYPE.ACRYLIC.getValue())),
                    WinDef.DWORD.SIZE
            );
        }

        DWM.setWindowAttribute(
                hWnd,
                DWMA_WINDOW_ATTRIBUTE.DWMWA_USE_IMMERSIVE_DARK_MODE.getValue(),
                true
        );
    } **/

    private int toRGBInt(final Color color) {
        return (doubleTo8Bit(color.getBlue()) << 16)
                | (doubleTo8Bit(color.getGreen()) << 8)
                | doubleTo8Bit(color.getRed());
    }

    private int doubleTo8Bit(final double number) {
        return (int) Math.min(255.0, Math.max(number * 255.0, 0.0));
    }

}
