package impl.com.pixelduke.window.win32;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.PointerType;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinNT;

public interface DwmSupport extends Library {
    DwmSupport INSTANCE = Native.load("dwmapi", DwmSupport.class);

    WinNT.HRESULT DwmSetWindowAttribute(
            WinDef.HWND hwnd,
            int dwAttribute,
            PointerType pvAttribute,
            int cbAttribute
    );
}
