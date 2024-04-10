package de.paulflohr.timer.command;

import de.paulflohr.timer.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class StartCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 0) return false;

        if (Main.getInstance().getTimer().isRunning()) {
            sender.sendMessage(Main.getInstance().prefixedMessage(ChatColor.RED + "The timer is already running!"));
            return false;
        }

        Main.getInstance().getTimer().setRunning(true);

        String verb = Main.getInstance().getTimer().getTime() > 0 ? "resumed" : "started";
        Bukkit.getOnlinePlayers().forEach(player -> player.sendTitle(
                Main.getInstance().getSettings().getColor() + "Timer " + verb,
                null, 10, 50, 10));

        return true;
    }
}
