package com.goldfrosch.utils;

import com.goldfrosch.data.buff.BuffType;
import com.goldfrosch.data.buff.BuffVO;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import static com.goldfrosch.TwipRoulette.plugin;
import static com.goldfrosch.utils.Constants.BUFF_LIST;
import static com.goldfrosch.utils.Constants.CONTENT_PREFIX;

public class BuffUtils {
  public static void setPlayerRotate(Player player) {
    Location loc = player.getLocation();

    Location goal = new Location(player.getWorld(), loc.getX(), loc.getY(), loc.getZ(), -loc.getYaw(), -loc.getPitch());
    player.teleport(goal);
  }

  private static void getWinningResult(Player player, int i) {
    BuffVO buff = BUFF_LIST[i];
    String msg = buff.getKey() + " " + (buff.getBuffTime() != 0 ? buff.getBuffTime() : "") + buff.getUnit().getValue();

    RouletteUtils.ShowRouletteResultTitleMsg(player, msg);
    player.sendMessage(CONTENT_PREFIX + msg);

    if (buff.getBuffType().equals(BuffType.EFFECT)) {
      switch (i) {
        case 0:
          buff.setPlayerPotionEffect(player, PotionEffectType.POISON);
          break;
        case 2:
          buff.setPlayerPotionEffect(player, PotionEffectType.SPEED);
          break;
        case 3:
          buff.setPlayerPotionEffect(player, PotionEffectType.SLOW);
          break;
        case 4:
          buff.setPlayerPotionEffect(player, PotionEffectType.FAST_DIGGING);
          break;
        case 5:
          buff.setPlayerPotionEffect(player, PotionEffectType.BLINDNESS);
          break;
        case 6:
          buff.setPlayerPotionEffect(player, PotionEffectType.REGENERATION);
          break;
        default:
          break;
      }
    } else if (buff.getBuffType().equals(BuffType.HUNGER)) {
      buff.setPlayerHunger(player);
    } else if (buff.getBuffType().equals(BuffType.FIRE)) {
      buff.setPlayerBurn(player);
    } else if (buff.getBuffType().equals(BuffType.SPIN)) {
      setPlayerRotate(player);
    }
  }

  public static void PlayerBuffInRoulette(Player player) {
    var random = Math.random() * 100;
    for (int i = 0; i < BUFF_LIST.length; i++) {
      var chance = 0;
      if (i != 0) {
        for (int j = 0; j < i; j++) {
          chance += BUFF_LIST[j].getChance();
        }
      }

      if (chance < random && random <= chance + BUFF_LIST[i].getChance()) {
        getWinningResult(player, i);
        break;
      }
    }
  }
}
