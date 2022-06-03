package com.goldfrosch.utils;

import com.goldfrosch.data.entity.EntityVO;
import org.bukkit.entity.Player;

import static com.goldfrosch.TwipRoulette.plugin;
import static com.goldfrosch.utils.Constants.*;

public class EntityUtils {
  public static String getMessage(EntityVO data) {
    return data.getKey() + " " + data.getAmount() + "마리 소환";
  }
  
  public static void SpawnEntityOnPlayer(Player player, EntityVO entity) {
    player.getWorld().spawnEntity(player.getLocation(), entity.getEntity());
    RouletteUtils.ShowRouletteResultTitleMsg(player, getMessage(entity));
    player.sendMessage(CONTENT_PREFIX + getMessage(entity));
  }

  public static EntityVO getRandomEntityVo(EntityVO[] entityList) {
    var random = Math.random() * entityList.length;

    return entityList[(int) Math.floor(random)];
  }

  public static void PlayerSpawnEntityRoulette(Player player) {
    var random = Math.random() * 100;
    if (random <= 60) {
      SpawnEntityOnPlayer(player, getRandomEntityVo(MONSTER_LIST));
    } else if (random <= 90) {
      SpawnEntityOnPlayer(player, getRandomEntityVo(ANIMAL_LIST));
    } else {
      SpawnEntityOnPlayer(player, VILLAGER_ENTITY);
    }
  }
}
