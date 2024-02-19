package com.pixelduke.window;

import com.sun.jna.NativeLong;
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

    public static long getNativeHandleOfStageAsLong(Window stage) {
        try {
            final Method getPeer = Window.class.getDeclaredMethod("getPeer");
            getPeer.setAccessible(true);
            final Object tkStage = getPeer.invoke(stage);
            final Method getPlatformWindow = tkStage.getClass().getMethod("getPlatformWindow");
            final Object jfxWindow = getPlatformWindow.invoke(tkStage);
            final Method getNativeWindow = jfxWindow.getClass().getMethod("getNativeWindow");
            getNativeWindow.setAccessible(true);
            Long ptr = (Long) getNativeWindow.invoke(jfxWindow);
            return ptr;
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0l;
        }
    }
}
