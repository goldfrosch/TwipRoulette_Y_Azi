package com.goldfrosch.data.item;

import com.goldfrosch.utils.ChatUtils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.List;

import static com.goldfrosch.utils.Constants.*;

public class ItemData {
  private static ItemStack createItem(Material type, String name, List<String> lore, int amount) {
    var item = new ItemStack(type, amount);
    var itemMeta = item.getItemMeta();

    if (itemMeta != null) {
      itemMeta.setDisplayName(name);
      itemMeta.setLore(lore);
    }

    item.setItemMeta(itemMeta);

    return item;
  }

  private static ItemStack CustomStone() {
    var lore = List.of(
        ChatUtils.setLoreString("굴러다니는 짱 돌")
    );
    return createItem(Material.STONE, COMMON_RANK + "짱 돌", lore, 16);
  }

  private static ItemStack CustomSand() {
    var lore = List.of(
        ChatUtils.setLoreString("모래 마녀"),
        ChatUtils.setLoreString("샌드 위치!")
    );

    return createItem(Material.SAND, COMMON_RANK + "샌 드", lore, 16);
  }

  private static ItemStack CustomDirt() {
    var lore = List.of(
        ChatUtils.setLoreString("흙이 울면 흙흙")
    );

    return createItem(Material.DIRT, COMMON_RANK + "흙", lore, 16);
  }
}
