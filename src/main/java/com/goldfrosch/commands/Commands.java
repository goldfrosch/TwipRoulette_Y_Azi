package com.goldfrosch.commands;

import com.goldfrosch.TwipRoulette;
import com.goldfrosch.utils.*;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

import static com.goldfrosch.TwipRoulette.gameStatus;
import static com.goldfrosch.TwipRoulette.plugin;
import static com.goldfrosch.utils.Constants.COMMAND_TITLE;
import static com.goldfrosch.utils.Constants.CONTENT_PREFIX;
import static org.bukkit.Bukkit.getServer;

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
                            switch (args[1]) {
                                case "buff":
                                    BuffUtils.PlayerBuffInRoulette(player);
                                    break;
                                case "item":
                                    ItemUtils.getRandomItem(player);
                                    break;
                                case "monster":
                                    EntityUtils.PlayerSpawnEntityRoulette(player);
                                    break;
                                case "disaster":
                                    DisasterUtils.setPlayerRandomDisaster(player);
                                    break;
                            }
                            break;
                        case "disaster":
                            DisasterUtils.spawnBossEntity(player);
                            break;
                        case "spin":
                            BuffUtils.setPlayerRotate(player);
                            break;
                        case "start":
                            if(gameStatus) {
                                player.sendMessage(CONTENT_PREFIX + "이미 게임이 시작되어 있습니다.");
                            } else {

                                for(Player players: getServer().getOnlinePlayers()) {
                                    players.sendMessage(ChatColor.AQUA + "==================================");
                                    players.sendMessage(ChatColor.DARK_PURPLE + "       트윕 룰렛");
                                    players.sendMessage(ChatColor.GRAY+ "  제작자: GoldFrosch (김 아홀로틀)");
                                    players.sendMessage(ChatColor.GRAY+ "  제작 기간: 5/29 ~ 6/3");
                                    players.sendMessage(ChatColor.GRAY+ "  오류 나도 봐줘라!");
                                    players.sendMessage(ChatColor.AQUA + "==================================");
                                    new BukkitRunnable() {
                                        @Override
                                        public void run() {
                                            new BukkitRunnable() {
                                                int count = 6;
                                                @Override
                                                public void run() {
                                                    count--;
                                                    if(count == 0) {
                                                        players.sendMessage(CONTENT_PREFIX + "게임 시작");
                                                        cancel();
                                                    }
                                                    players.sendMessage(CONTENT_PREFIX + count + "초 뒤 시작합니다");
                                                }

                                            }.runTaskTimer(plugin, 0L, 20L);
                                            gameStatus = true;
                                        }
                                    }.runTaskLater(plugin, 60L);
                                }

                            }
                            break;
                        case "stop":
                            player.sendMessage(CONTENT_PREFIX + "장비를 정지합니다");
                            gameStatus = false;
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
