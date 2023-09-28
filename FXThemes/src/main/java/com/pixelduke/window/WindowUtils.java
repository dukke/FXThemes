package com.pixelduke.window;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef;
import javafx.stage.Window;

import java.lang.reflect.Method;

public class WindowUtils {
    public static WinDef.HWND getNativeHandleOfStage(Window stage) {
        try {
            final Method getPeer = Window.class.getDeclaredMethod("getPeer", null);
            getPeer.setAccessible(true);
            final Object tkStage = getPeer.invoke(stage);
            final Method getRawHandle = tkStage.getClass().getMethod("getRawHandle");
            getRawHandle.setAccessible(true);
            final Pointer pointer = new Pointer((Long) getRawHandle.invoke(tkStage));
            return new WinDef.HWND(pointer);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
