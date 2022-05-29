package com.goldfrosch.utils;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

import static com.goldfrosch.utils.Constants.ROULETTE_TITLE;

public class RouletteUtils {

  public static void ShowRouletteResultTitleMsg(Player player, String msg) {
    player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 5, 5);
    player.sendTitle(ROULETTE_TITLE, msg, 1, 1 ,1);
  }
}
