package com.pixelduke.window;

import com.sun.jna.Platform;
import javafx.stage.Window;

public abstract class ThemeWindowManagerFactory {
    
    public static ThemeWindowManager create() {
        String osName = System.getProperty("os.name");
        int majorVersion = getMajorVersion();
        
        // Check which operating system and version we're running and return appropriate ThemeWindowManager
        switch(Platform.getOSType()){
            case Platform.WINDOWS:
                switch(majorVersion){
                    case 10:
                        return new Win10ThemeWindowManager();
                    case 11:
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
        
        return new ThemeWindowManager(){
            public void setDarkModeForWindowFrame(Window window, boolean darkMode){
                System.out.println("Warning: Unsupported Window Operating System");
            }
        };
    }

    private static int getMajorVersion(){
        int version = -1;
        
        try{
            version = Integer.parseInt(System.getProperty("os.version").split("\\.")[0]);
        }catch (Exception e){
            e.printStackTrace();
        }
        return version;
    }
}
