package com.goldfrosch.commands;

import com.goldfrosch.TwipRoulette;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

import static com.goldfrosch.utils.RouletteUtils.SetPlayerBuffInRoulette;

public class Commands extends AbstractCommand {
    public Commands(TwipRoulette plugin, String Command) {
        super(plugin,Command);
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, org.bukkit.command.Command command, String alias, String[] args) {
        return null;
    }

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        String prefix = plugin.getConfig().getString("message.prefix").replace("&", "§");

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if(label.equalsIgnoreCase("cmd")){
                if(args.length == 0){
                    player.sendMessage(prefix + "어쩔 티비");
                } else {
                    if(args[1].equalsIgnoreCase("test")) {
                        SetPlayerBuffInRoulette(player);
                    }
                }
            }
        }
        return false;
    }
}
