package com.pixelduke.window;

import com.sun.jna.Platform;

public abstract class ThemeWindowManagerFactory {

    public static ThemeWindowManager create() {
        String osName = System.getProperty("os.name");

        // Check which operating system and version we're running and return appropriate ThemeWindowManager
        switch (Platform.getOSType()) {
            case Platform.WINDOWS:
                switch (osName) {
                    case "Windows 10":
                        return new Win10ThemeWindowManager();
                    case "Windows 11":
                        return new Win11ThemeWindowManager();
                    default:
                        break;
                }
                break;
            case Platform.MAC:
                return new MacThemeWindowManager();
            case Platform.LINUX:
                return new LinuxThemeWindowManager();
            default:
                break;
        }

        throw new RuntimeException("Unsupported Window Operating System");
    }
}
