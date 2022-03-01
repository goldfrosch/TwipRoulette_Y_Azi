package com.goldfrosch.commands;

import com.goldfrosch.MainPlugin;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import java.util.List;

public abstract class AbstractCommand implements TabExecutor {
    protected MainPlugin plugin;
    private String Command;

    public AbstractCommand(MainPlugin plugin, String Command) {
        this.plugin = plugin;
        this.Command = Command;
    }

    public abstract List<String> onTabComplete(CommandSender sender, org.bukkit.command.Command command, String alias, String[] args);
    public abstract boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args);
}
