package com.pixelduke.window;

import com.sun.jna.platform.win32.WinDef;
import impl.com.pixelduke.window.win32.DWM;
import impl.com.pixelduke.window.win32.DWMA_WINDOW_ATTRIBUTE;
import impl.com.pixelduke.window.win32.DWM_SYSTEMBACKDROP_TYPE;
import impl.com.pixelduke.window.win32.DwmSupport;
import javafx.scene.paint.Color;
import javafx.stage.Window;

public class Win11ThemeWindowManager implements ThemeWindowManager {

    public enum Backdrop {
        NONE,
        MICA,
        MICA_ALT,
        ACRYLIC
    }

    public void setDarkModeForWindowFrame(Window window, boolean darkMode) {
        DWM.setWindowAttribute(
                WindowUtils.getNativeHandleOfStage(window),
                DWMA_WINDOW_ATTRIBUTE.DWMWA_USE_IMMERSIVE_DARK_MODE.getValue(),
                darkMode
        );
    }

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

    private int toRGBInt(final Color color) {
        return (doubleTo8Bit(color.getBlue()) << 16)
                | (doubleTo8Bit(color.getGreen()) << 8)
                | doubleTo8Bit(color.getRed());
    }

    private int doubleTo8Bit(final double number) {
        return (int) Math.min(255.0, Math.max(number * 255.0, 0.0));
    }

}
