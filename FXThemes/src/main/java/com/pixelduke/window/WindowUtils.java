package com.pixelduke.window;

import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef;
import javafx.stage.Window;

import java.lang.reflect.Method;

public class WindowUtils {

    /**
     * Function will obtain the pointer to the native window and return as a Windows specific handle.
     * @param stage The JavaFX stage window
     * @return A WinDef.HWND instance representing the window's platform's native pointer.
     */
    public static WinDef.HWND getNativeHandleOfStage(Window stage) {
        final Pointer pointer = new Pointer(getNativeHandleOfStageAsLong(stage));
        return new WinDef.HWND(pointer);
    }

    /**
     * Function will obtain the pointer to the native window and return as a 64 bit long value.
     * @param stage The JavaFX stage window
     * @return A NativeLong representing the window's platform's native pointer.
     */
    public static NativeLong getNativeHandleOfStageAsNativeLong(Window stage) {
        return new NativeLong(getNativeHandleOfStageAsLong(stage));
    }

    /**
     * Function will obtain the pointer to the native window and return as a 64 bit long value.
     * @param stage The JavaFX stage window
     * @return A long representing the window's platform's native pointer.
     */
    public static long getNativeHandleOfStageAsLong(Window stage) {
        try {
            final Method getPeer = Window.class.getDeclaredMethod("getPeer");
            getPeer.setAccessible(true);
            final Object tkStage = getPeer.invoke(stage);
            final Method getRawHandle = tkStage.getClass().getMethod("getRawHandle");
            getRawHandle.setAccessible(true);
            Long ptr = (Long) getRawHandle.invoke(tkStage);
            return ptr;
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0l; // bad. Should be a valid pointer
        }
    }
}
