package impl.com.pixelduke.window.win32.windows10;

public enum AccentState {
    ACCENT_DISABLED(0),
    ACCENT_ENABLE_GRADIENT(1),
    ACCENT_ENABLE_TRANSPARENTGRADIENT(2),
    ACCENT_ENABLE_BLURBEHIND(3),
    ACCENT_ENABLE_ACRYLICBLURBEHIND(4),
    ACCENT_INVALID_STATE(5);

    private final int value;

    AccentState(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
