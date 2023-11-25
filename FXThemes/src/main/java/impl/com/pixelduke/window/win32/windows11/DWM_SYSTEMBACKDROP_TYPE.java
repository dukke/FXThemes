package impl.com.pixelduke.window.win32.windows11;

public enum DWM_SYSTEMBACKDROP_TYPE {
    NONE(1),
    MICA(2),
    MICA_ALT(4),
    ACRYLIC(3);

    private final int value;

    DWM_SYSTEMBACKDROP_TYPE(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
