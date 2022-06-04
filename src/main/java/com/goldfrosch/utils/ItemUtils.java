package com.goldfrosch.utils;

import com.goldfrosch.data.item.ItemData;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import static com.goldfrosch.utils.Constants.CONTENT_PREFIX;
import static com.goldfrosch.utils.Constants.ITEM_LIST;

public class ItemUtils {
  public static void giveItemInWorld(Player player, ItemStack item) {
    player.getWorld().dropItemNaturally(player.getLocation(), item);
  }
  public static void givePlayerItem(Player player, int i) {
    String msg = ITEM_LIST[i].getKey() + " 지급";
    RouletteUtils.ShowRouletteResultTitleMsg(player, msg);
    player.sendMessage(CONTENT_PREFIX + msg);
    switch (ITEM_LIST[i].getKey()) {
      case "랜덤 꽝 아이템":
        giveItemInWorld(player, ItemData.getRandomCommonItem());
        break;
      case "철 방어구":
        giveItemInWorld(player, ItemData.RandomIronArmor());
        break;
      case "다이아 방어구":
        giveItemInWorld(player, ItemData.RandomDiamondArmor());
        break;
      case "식량":
        giveItemInWorld(player, ItemData.CustomMeat());
        break;
      case "아이템 삭제":
        ItemData.clearInventory(player);
        break;
      case "양동이":
        giveItemInWorld(player, new ItemStack(Material.AXOLOTL_BUCKET, 1));
        break;
      case "나무 도구":
        giveItemInWorld(player, ItemData.RandomWoodTool());
        break;
      case "철 도구":
        giveItemInWorld(player, ItemData.RandomIronTool());
        break;
      case "다이아 도구":
        giveItemInWorld(player, ItemData.RandomDiamondTool());
        break;
      case "인첸트 다이아 도구":
        giveItemInWorld(player, ItemData.randomEnchantTool(ItemData.RandomDiamondTool()));
        break;
      case "불사의 토템":
        giveItemInWorld(player, ItemData.getTotem());
        break;
      case "인벤 세이브 10분":
        if(player.getName().equalsIgnoreCase("Y_Azi")) {
          ItemData.setKeepInventoryTime();
        }
        break;
      case "인첸트 다이아 방어구":
        giveItemInWorld(player, ItemData.randomEnchantArmor(ItemData.RandomDiamondArmor()));
        break;
      case "인첸트 철 방어구":
        giveItemInWorld(player, ItemData.randomEnchantArmor(ItemData.RandomIronArmor()));
        break;
      case "인첸트 철 도구":
        giveItemInWorld(player, ItemData.randomEnchantTool(ItemData.RandomIronTool()));
        break;
      case "가죽 방어구":
        giveItemInWorld(player, ItemData.RandomLeatherArmor());
        break;
      default:
        break;
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
