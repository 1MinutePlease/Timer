package de.paulflohr.timer.timer;

import de.paulflohr.timer.Main;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Timer {

    private int time;
    private boolean running;

    public Timer(int time) {
        this.time = time;

        startTimer();
    }

    private void startTimer() {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (!Main.getInstance().getSettings().isVisible()) return;

                if (isRunning()){
                    for (Player player : Bukkit.getOnlinePlayers()){
                        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(
                                Main.getInstance().getSettings().getColor() +
                                Main.getInstance().getSettings().getTimeFormat().getTimeFormatted(time)));
                    }
                    time++;
                } else if (time > 0 && !isRunning()) {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(
                                Main.getInstance().getSettings().getColor() +
                                Main.getInstance().getSettings().getTimeFormat().getTimeFormatted(time) + ChatColor.ITALIC + " (paused)"));
                    }
                } else {
                    for (Player player : Bukkit.getOnlinePlayers()){
                        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(
                                Main.getInstance().getSettings().getColor() + "The timer is stopped!"));
                    }
                }
            }
        }.runTaskTimer(Main.getInstance(), 0, 20);
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}
