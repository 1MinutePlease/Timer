package de.paulflohr.timer.command;

import de.paulflohr.timer.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class PauseCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 0) return false;

        if (!Main.getInstance().getTimer().isRunning()) {
            sender.sendMessage(Main.getInstance().prefixedMessage(ChatColor.RED + "The timer is already stopped!"));
            return false;
        }

        Main.getInstance().getTimer().setRunning(false);

        String verb = Main.getInstance().getTimer().getTime() > 0 ? "paused" : "stopped";
        Bukkit.getOnlinePlayers().forEach(player -> player.sendTitle(
                Main.getInstance().getSettings().getColor() + "Timer " + verb,
                null, 10, 50, 10));

        return true;
    }
}
