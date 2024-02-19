package com.pixelduke.window;

import com.sun.jna.Platform;

public abstract class ThemeWindowManagerFactory {
    public static ThemeWindowManager create() {
        String osName = System.getProperty("os.name");
        // Check which operating system we're running and return appropriate ThemeWindowManager
        if (Platform.isWindows()) {
            if (osName.equals("Windows 10")) {
                return new Win10ThemeWindowManager();
            } else {
                return new Win11ThemeWindowManager();
            }
        } else if (Platform.isMac()) {
            return new MacThemeWindowManager();
        } else if (Platform.isLinux()) {
            return new LinuxThemeWindowManager();
        } else {
            throw new RuntimeException("Unsupported Window Operating System");
        }
    }
}
