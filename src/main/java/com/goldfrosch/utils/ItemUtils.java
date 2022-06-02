package com.goldfrosch.utils;

import com.goldfrosch.data.item.ItemData;
import org.bukkit.entity.Player;

import static com.goldfrosch.utils.Constants.ITEM_LIST;

public class ItemUtils {
  public static void givePlayerItem(Player player, int i) {
    if(!(ITEM_LIST[i].getItem() == null)) {
      player.getInventory().addItem(ITEM_LIST[i].getItem());
    } else {
      if(ITEM_LIST[i].getKey().equals("아이템 삭제")) {
        ItemData.clearInventory(player);
      } else if (ITEM_LIST[i].getKey().equals("인벤 세이브 10분")) {
        ItemData.setKeepInventoryTime();
      }
    }
  }

  public static void getRandomItem(Player player) {
    var random = Math.random() * 100;
    for (int i = 0; i < ITEM_LIST.length; i++) {
      var chance = 0;
      if (i != 0) {
        for (int j = 0; j < i; j++) {
          chance += ITEM_LIST[j].getChance();
        }
      }

      if (chance < random && random <= chance + ITEM_LIST[i].getChance()) {
        givePlayerItem(player, i);
        break;
      }
    }
  }
}
