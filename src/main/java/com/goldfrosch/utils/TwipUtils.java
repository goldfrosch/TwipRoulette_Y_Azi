package com.goldfrosch.utils;

import com.outstandingboy.donationalert.platform.Twip;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.io.IOException;

import static com.goldfrosch.TwipRoulette.gameStatus;
import static com.goldfrosch.TwipRoulette.plugin;
import static com.goldfrosch.utils.Constants.CONTENT_PREFIX;
import static com.goldfrosch.utils.Constants.TWIP_KEY;
import static org.bukkit.Bukkit.getServer;

public class TwipUtils {
  public static void DonateRegisterAction() {
    try {
      Twip twip = new Twip(TWIP_KEY);
      plugin.consoleLog("트윕 연결에 성공했습니다! 즐거운 시간 되세요");
      twip.subscribeDonation((donation -> {
//        if(gameStatus) {
//          for(Player player: getServer().getOnlinePlayers()) {
//            player.sendMessage(CONTENT_PREFIX + ChatColor.AQUA + donation.getAmount() + "원" +  ChatColor.YELLOW + "후원 감사합니다.");
//            RouletteUtils.PlayerRouletteRoll(player, donation.getAmount());
//          }
//        }
        for(Player player: getServer().getOnlinePlayers()) {
          player.sendMessage(CONTENT_PREFIX + ChatColor.AQUA + donation.getAmount() + "원" +  ChatColor.YELLOW + "후원 감사합니다.");
          RouletteUtils.PlayerRouletteRoll(player, donation.getAmount());
        }
      }));
    } catch (IOException e) {
      for(Player player: getServer().getOnlinePlayers()) {
        player.sendMessage(ChatColor.RED + "에러 발생: 침착하게 개발자에게 문의 바랍니다");
      }
      e.printStackTrace();
    }
  }
}
