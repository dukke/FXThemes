module com.pixelduke.fxthemes {
    requires transitive javafx.graphics;
    requires transitive javafx.controls;

    requires com.sun.jna;
    requires com.sun.jna.platform;

    exports com.pixelduke.window;

    exports impl.com.pixelduke.window.win32.windows10 to com.sun.jna;
}