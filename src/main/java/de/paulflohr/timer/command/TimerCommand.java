package de.paulflohr.timer.command;

import de.paulflohr.timer.Main;
import de.paulflohr.timer.util.TimeFormat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class TimerCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 2) return false;

        switch (args[0]) {
            case "color":
                ChatColor color;
                try {
                    color = ChatColor.valueOf(args[1]);
                } catch (IllegalArgumentException e) {
                    sender.sendMessage(Main.getInstance().prefixedMessage("Invalid color!"));
                    return false;
                }

                if (Main.getInstance().getSettings().getColor() == color) {
                    sender.sendMessage(Main.getInstance().prefixedMessage("Color is already set to " + color + color.name() + ChatColor.RESET + "!"));
                    return false;
                }
                Main.getInstance().getSettings().setColor(color);
                sender.sendMessage(Main.getInstance().prefixedMessage("Color set to " + color + color.name() + ChatColor.RESET + "!"));

                break;
            case "autoResume":
                boolean autoResume;
                try {
                    autoResume = Boolean.parseBoolean(args[1]);
                } catch (IllegalArgumentException e) {
                    sender.sendMessage(Main.getInstance().prefixedMessage("Invalid boolean!"));
                    return false;
                }

                if (Main.getInstance().getSettings().isAutoResume() == autoResume) {
                    sender.sendMessage(Main.getInstance().prefixedMessage("AutoResume is already set to " + autoResume + "!"));
                    return false;
                }
                Main.getInstance().getSettings().setAutoResume(autoResume);
                sender.sendMessage(Main.getInstance().prefixedMessage("AutoResume set to " + autoResume + "!"));

                break;
            case "visible":
                boolean visible;
                try {
                    visible = Boolean.parseBoolean(args[1]);
                } catch (IllegalArgumentException e) {
                    sender.sendMessage(Main.getInstance().prefixedMessage("Invalid boolean!"));
                    return false;
                }

                if (Main.getInstance().getSettings().isVisible() == visible) {
                    sender.sendMessage(Main.getInstance().prefixedMessage("Visibility is already set to " + visible + "!"));
                    return false;
                }
                Main.getInstance().getSettings().setVisible(visible);
                Main.getInstance().getTimer().setRunning(false);
                sender.sendMessage(Main.getInstance().prefixedMessage("Visibility set to " + visible + "!"));
                break;
            case "format":
                TimeFormat format;
                switch (args[1].toLowerCase()) {
                    case "digital":
                        format = TimeFormat.Digital;
                        break;
                    case "verbose":
                        format = TimeFormat.Verbose;
                        break;
                    default:
                        sender.sendMessage(Main.getInstance().prefixedMessage("Invalid format!"));
                        return false;
                }

                if (Main.getInstance().getSettings().getTimeFormat() == format) {
                    sender.sendMessage(Main.getInstance().prefixedMessage("Format is already set to " + format.name() + "!"));
                    return false;
                }
                Main.getInstance().getSettings().setTimeFormat(format);
                sender.sendMessage(Main.getInstance().prefixedMessage("Format set to " + format.name() + "!"));

                break;
            case "set":
                int time;
                try {
                    time = Integer.parseInt(args[1]);
                } catch (NumberFormatException e) {
                    sender.sendMessage(Main.getInstance().prefixedMessage("Invalid number!"));
                    return false;
                }

                Main.getInstance().getTimer().setTime(time);
                Bukkit.broadcastMessage(Main.getInstance().prefixedMessage("Time set to " + time + "!"));

                break;
            default:
                return false;
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        ArrayList<String> list = new ArrayList<>();
        if (args.length == 0) return list;
        if (args.length == 1) {
            list.add("color");
            list.add("autoResume");
            list.add("visible");
            list.add("format");
            list.add("set");
        } else if (args.length == 2) {
            if (args[0].equalsIgnoreCase("color")) {
                for (ChatColor color : ChatColor.values()) {
                    list.add(color.name());
                }
            } else if (args[0].equalsIgnoreCase("autoResume") || args[0].equalsIgnoreCase("visible")) {
                list.add("true");
                list.add("false");
            } else if (args[0].equalsIgnoreCase("format")) {
                list.add("digital");
                list.add("verbose");
            } else if (args[0].equalsIgnoreCase("set")) {
                list.add("0");
            }
        }
        ArrayList<String> completerList = new ArrayList<>();
        String arg = args[args.length - 1].toLowerCase();
        for (String s : list) {
            String s1 = s.toLowerCase();
            if (s1.startsWith(arg)) {
                completerList.add(s);
            }
        }
        return completerList;
    }
}
