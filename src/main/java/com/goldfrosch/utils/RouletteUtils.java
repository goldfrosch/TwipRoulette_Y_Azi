package com.goldfrosch.utils;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

import static com.goldfrosch.utils.Constants.ROULETTE_TITLE;

public class RouletteUtils {

  static void ShowRouletteResultTitleMsg(Player player, String msg) {
    player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 5, 5);
    player.sendTitle(ROULETTE_TITLE, msg, 20, 20 ,20);
  }

  public static void PlayerRouletteRoll(Player player, Long money) {
    if (money == 3500) {
      BuffUtils.PlayerBuffInRoulette(player);
    } else if (money == 5500) {
      ItemUtils.getRandomItem(player);
    } else if (money == 8500) {
      EntityUtils.PlayerSpawnEntityRoulette(player);
    } else if (money == 35000) {
      DisasterUtils.setPlayerRandomDisaster(player);
    }
  }
}
