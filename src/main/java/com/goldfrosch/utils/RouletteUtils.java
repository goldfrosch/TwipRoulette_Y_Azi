package com.goldfrosch.utils;

import org.bukkit.entity.Player;

import static com.goldfrosch.utils.ChatUtils.setPrefix;
import static com.goldfrosch.utils.Constants.BUFF_LIST;
import static com.goldfrosch.utils.Constants.ROULETTE_TITLE;

public class RouletteUtils {
  public static void ShowRouletteResultTitleMsg(String msg, Player player) {
    player.sendTitle(ROULETTE_TITLE, msg, 1, 1 ,1);
  }

  public static void ShowRouletteResultMsg(String msg, Player player) {
    player.sendMessage(setPrefix(msg));
  }

  public static void SetPlayerBuffInRoulette(Player player) {
    var random = Math.random() * 100;
    for (int i = 0; i < BUFF_LIST.length; i++) {
      var chance = 0;
      for (int j = 0; j < i; i++) {
        chance += BUFF_LIST[j].getChance();
      }
      player.sendMessage("테스트 환경:" + chance + " , " + random + " , " + (chance + BUFF_LIST[i].getChance()));
//      if (chance < random && random <= chance + BUFF_LIST[i].getChance()) {
//
//      }
    }
  }
}
