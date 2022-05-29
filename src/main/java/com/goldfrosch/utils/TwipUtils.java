package com.goldfrosch.utils;

import com.outstandingboy.donationalert.platform.Twip;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.io.IOException;

import static com.goldfrosch.TwipRoulette.GAME_STATUS;
import static com.goldfrosch.utils.Constants.TWIP_KEY;
import static org.bukkit.Bukkit.getServer;

public class TwipUtils {
  public static void DonateRegisterAction() {
    try {
      Twip twip = new Twip(TWIP_KEY);
      twip.subscribeDonation((donation -> {
        if(!GAME_STATUS) {
          return;
        }
        for(Player player: getServer().getOnlinePlayers()) {
          player.sendMessage(ChatColor.AQUA + String.valueOf(donation.getAmount()) + "원" +  ChatColor.YELLOW + "후원 감사합니다.");
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
