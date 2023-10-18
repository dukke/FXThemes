package com.pixelduke.window;

public abstract class ThemeWindowManagerFactory {
    public static ThemeWindowManager create() {
        String osName = System.getProperty("os.name");

        // Check which operating system we're running and return appropriate ThemeWindowManager
        if (osName.equals("Windows 10")) {
            return new Win10ThemeWindowManager();
        }
        return new Win11ThemeWindowManager();
    }
}
