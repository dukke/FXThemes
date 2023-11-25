package impl.com.pixelduke.window.win32.windows11;

import com.sun.jna.platform.win32.WinDef;

public class DWM {
    public static void setWindowAttribute(WinDef.HWND hwnd, int attribute, int attributeValue) {
        DwmSupport.INSTANCE.DwmSetWindowAttribute(
                hwnd,
                attribute,
                new WinDef.DWORDByReference(new WinDef.DWORD(attributeValue)),
                WinDef.DWORD.SIZE
        );
    }

    public static void setWindowAttribute(WinDef.HWND hwnd, int attribute, boolean attributeValue) {
        DwmSupport.INSTANCE.DwmSetWindowAttribute(
                hwnd,
                attribute,
                new WinDef.BOOLByReference(new WinDef.BOOL(attributeValue)),
                WinDef.BOOL.SIZE
        );
    }
}
