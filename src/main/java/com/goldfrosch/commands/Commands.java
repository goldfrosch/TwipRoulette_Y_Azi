package com.goldfrosch.commands;

import com.goldfrosch.TwipRoulette;
import com.goldfrosch.utils.BuffUtils;
import com.goldfrosch.utils.DisasterUtils;
import com.goldfrosch.utils.RouletteUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

import static com.goldfrosch.utils.Constants.COMMAND_TITLE;
import static com.goldfrosch.utils.Constants.CONTENT_PREFIX;

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
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if(label.equalsIgnoreCase(COMMAND_TITLE)){
                if (args.length == 0){
                    player.sendMessage(CONTENT_PREFIX + "어쩔 티비");
                } else {
                    switch (args[0]) {
                        case "test":
                            DisasterUtils.createWaterJail(player);
                            break;
                        case "disaster":
                            DisasterUtils.setPlayerRandomDisaster(player);
                            break;
                        case "spin":
                            BuffUtils.setPlayerRotate(player);
                            break;
                        case "start":
                            break;
                        default:
                            player.sendMessage(CONTENT_PREFIX + "존재하지 않는 명령어 입니다.");
                            break;
                    }
                }
            }
        }
        return false;
    }
}
