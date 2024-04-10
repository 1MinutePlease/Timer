package de.paulflohr.timer.event;

import de.paulflohr.timer.Main;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinLeaveListener implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (Main.getInstance().getSettings().isAutoResume() && Main.getInstance().getSettings().isVisible()) {
            Main.getInstance().getTimer().setRunning(true);
            Bukkit.getLogger().info(Main.getInstance().prefixedMessage("The timer has been resumed automatically."));
        } else if (event.getPlayer().isOp()) {
            event.getPlayer().sendMessage(Main.getInstance().prefixedMessage("The timer is currently paused. Use /timer resume to resume it."));
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerQuit(PlayerQuitEvent event) {
        if (Bukkit.getOnlinePlayers().isEmpty() &&
                Main.getInstance().getTimer().isRunning() &&
                Main.getInstance().getSettings().isAutoResume()) {
            Main.getInstance().getTimer().setRunning(false);
            Bukkit.getLogger().info(Main.getInstance().prefixedMessage("The timer has been stopped automatically."));
        }
    }
}