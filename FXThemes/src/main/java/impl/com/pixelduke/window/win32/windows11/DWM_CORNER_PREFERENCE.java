package impl.com.pixelduke.window.win32.windows11;

public enum DWM_CORNER_PREFERENCE {
    RECTANGULAR(1),
    ROUND(2),
    ROUND_SMALL(3);

    private final int value;

    DWM_CORNER_PREFERENCE(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}