package impl.com.pixelduke.window.win32.windows11;

public enum DWMA_WINDOW_ATTRIBUTE {
    DWMWA_USE_IMMERSIVE_DARK_MODE(20),
    DWMWA_BORDER_COLOR(34),
    DWMWA_CAPTION_COLOR(35),
    DWMWA_TEXT_COLOR(36),
    DWMWA_SYSTEMBACKDROP_TYPE(38),
    DWMWA_WINDOW_CORNER_PREFERENCE(33);

    private final int value;

    DWMA_WINDOW_ATTRIBUTE(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
