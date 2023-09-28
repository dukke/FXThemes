package com.pixelduke.window;

public abstract class ThemeWindowManagerFactory {
    public static ThemeWindowManager create() {
        //System.getProperty("os.name");
        // Check which operating system we're running and return appropriate FXWindowManager

        return new Win11ThemeWindowManager();
    }
}
