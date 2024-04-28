package de.paulflohr.timer.timer;

import de.paulflohr.timer.util.ConfigId;
import de.paulflohr.timer.util.TimeFormat;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

public class TimerSettings {

    private final FileConfiguration config;

    private ChatColor color;
    private boolean autoResume;
    private boolean visible;
    private TimeFormat timeFormat;

    public TimerSettings(FileConfiguration config) {
        this.config = config;
    }

    public void load() {
        color = ChatColor.getByChar(config.getString(ConfigId.COLOR.value, String.valueOf(ChatColor.GREEN.getChar())));
        autoResume = config.getBoolean(ConfigId.AUTO_RESUME.value, true);
        visible = config.getBoolean(ConfigId.VISIBILITY.value, true);
        timeFormat = TimeFormat.valueOf(config.getString(ConfigId.TIME_FORMAT.value, TimeFormat.Verbose.name()));
    }

    public void save() {
        config.set(ConfigId.COLOR.value, color.getChar());
        config.set(ConfigId.AUTO_RESUME.value, autoResume);
        config.set(ConfigId.VISIBILITY.value, visible);
        config.set(ConfigId.TIME_FORMAT.value, timeFormat.name());
    }

    public ChatColor getColor() {
        return color;
    }

    public void setColor(ChatColor color) {
        this.color = color;
    }

    public boolean isAutoResume() {
        return autoResume;
    }

    public void setAutoResume(boolean autoResume) {
        this.autoResume = autoResume;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public TimeFormat getTimeFormat() {
        return timeFormat;
    }

    public void setTimeFormat(TimeFormat timeFormat) {
        this.timeFormat = timeFormat;
    }
}
