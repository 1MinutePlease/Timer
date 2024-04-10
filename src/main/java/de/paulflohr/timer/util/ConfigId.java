package de.paulflohr.timer.util;

public enum ConfigId {

    AUTO_RESUME("autoResume"),
    COLOR("color"),
    VISIBILITY("visibility"),
    TIME_FORMAT("timeFormat"),
    TIME("time");

    public final String value;

    ConfigId(String value) {
        this.value = value;
    }
}
