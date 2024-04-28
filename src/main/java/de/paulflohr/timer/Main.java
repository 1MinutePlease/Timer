package de.paulflohr.timer;

import de.paulflohr.timer.command.StartCommand;
import de.paulflohr.timer.command.PauseCommand;
import de.paulflohr.timer.command.TimerCommand;
import de.paulflohr.timer.event.JoinLeaveListener;
import de.paulflohr.timer.timer.Timer;
import de.paulflohr.timer.timer.TimerSettings;
import de.paulflohr.timer.util.ConfigId;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private static Main instance;
    private TimerSettings settings;
    private Timer timer;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        getConfig().options().copyDefaults(true);

        settings = new TimerSettings(getConfig());
        settings.load();

        timer = new Timer(getConfig().getInt(ConfigId.TIME.value, 0));

        getCommand("start").setExecutor(new StartCommand());
        getCommand("pause").setExecutor(new PauseCommand());
        getCommand("timer").setExecutor(new TimerCommand());

        Bukkit.getPluginManager().registerEvents(new JoinLeaveListener(), this);
    }

    @Override
    public void onDisable() {
        settings.save();
        getConfig().set(ConfigId.TIME.value, timer.getTime());
        saveConfig();
    }

    public String prefixedMessage(String message) {
        return ChatColor.GRAY + "[" + settings.getColor() + "Timer" + ChatColor.GRAY + "] " + ChatColor.RESET + message;
    }

    public static Main getInstance() {
        return instance;
    }

    public TimerSettings getSettings() {
        return settings;
    }

    public Timer getTimer() {
        return timer;
    }
}
