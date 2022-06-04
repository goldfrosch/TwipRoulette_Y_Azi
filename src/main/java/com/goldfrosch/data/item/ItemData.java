package com.goldfrosch.data.item;

import com.goldfrosch.utils.ChatUtils;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Random;

import static com.goldfrosch.events.PlayerEvent.KeepInventoryTime;
import static com.goldfrosch.utils.Constants.*;

public class ItemData {
  public static ItemStack createItem(Material type, String name, List<String> lore, int amount) {
    var item = new ItemStack(type, amount);
    var itemMeta = item.getItemMeta();

    if (itemMeta != null) {
      itemMeta.setDisplayName(name);
      itemMeta.setLore(lore);
    }

    item.setItemMeta(itemMeta);

    return item;
  }

  public static ItemStack CustomStone() {
    var lore = List.of(
        ChatUtils.setLoreString("굴러다니는 짱 돌")
    );
    return createItem(Material.STONE, COMMON_RANK + "짱 돌", lore, 16);
  }

  public static ItemStack CustomSand() {
    var lore = List.of(
        ChatUtils.setLoreString("모래 마녀"),
        ChatUtils.setLoreString("샌드 위치!")
    );

    return createItem(Material.SAND, COMMON_RANK + "샌 드", lore, 16);
  }

  public static ItemStack CustomDirt() {
    var lore = List.of(
        ChatUtils.setLoreString("흙이 울면 흙흙")
    );

    return createItem(Material.DIRT, COMMON_RANK + "흙", lore, 16);
  }

  public static ItemStack CustomBone() {
    var lore = List.of(
        ChatUtils.setLoreString("먹지마세요"),
        ChatUtils.setLoreString("마루에게 양보하세요")
    );

    return createItem(Material.BONE, COMMON_RANK + "개 껌", lore, 1);
  }

  public static ItemStack getRandomCommonItem() {
    ItemStack[] list = {
        CustomBone(),
        CustomDirt(),
        CustomSand(),
        CustomStone(),
    };

    return getRandomItem(list);
  }

  public static ItemStack getRandomItem(ItemStack[] list) {
    var random = new Random().nextInt(list.length);

    return list[random];
  }

  public static ItemStack CustomMeat() {
    ItemStack[] meatList = {
        new ItemStack(Material.PORKCHOP, 16),
        new ItemStack(Material.COOKED_PORKCHOP, 16),
        new ItemStack(Material.BEEF, 16),
        new ItemStack(Material.COOKED_BEEF, 16),
        new ItemStack(Material.CHICKEN,16),
        new ItemStack(Material.COOKED_CHICKEN, 16),
        new ItemStack(Material.MUTTON, 16),
        new ItemStack(Material.COOKED_MUTTON, 16),
    };

    return getRandomItem(meatList);
  }

  public static ItemStack RandomDiamondArmor() {
    ItemStack[] armorList = {
        new ItemStack(Material.DIAMOND_HELMET, 1),
        new ItemStack(Material.DIAMOND_CHESTPLATE, 1),
        new ItemStack(Material.DIAMOND_LEGGINGS, 1),
        new ItemStack(Material.DIAMOND_BOOTS, 1),
    };

    return getRandomItem(armorList);
  }
  
  public static ItemStack RandomIronArmor() {
    ItemStack[] armorList = {
        new ItemStack(Material.IRON_HELMET, 1),
        new ItemStack(Material.IRON_CHESTPLATE, 1),
        new ItemStack(Material.IRON_LEGGINGS, 1),
        new ItemStack(Material.IRON_BOOTS, 1),
    };

    return getRandomItem(armorList);
  }

  public static ItemStack RandomLeatherArmor() {
    ItemStack[] armorList = {
        new ItemStack(Material.LEATHER_HELMET, 1),
        new ItemStack(Material.LEATHER_CHESTPLATE, 1),
        new ItemStack(Material.LEATHER_LEGGINGS, 1),
        new ItemStack(Material.LEATHER_BOOTS, 1),
    };

    return getRandomItem(armorList);
  }

  public static ItemStack RandomWoodTool() {
    ItemStack[] toolList = {
        new ItemStack(Material.WOODEN_AXE, 1),
        new ItemStack(Material.WOODEN_HOE, 1),
        new ItemStack(Material.WOODEN_PICKAXE, 1),
        new ItemStack(Material.WOODEN_SHOVEL, 1),
        new ItemStack(Material.WOODEN_SWORD, 1),
    };

    return getRandomItem(toolList);
  }

  public static ItemStack RandomIronTool() {
    ItemStack[] toolList = {
        new ItemStack(Material.IRON_AXE, 1),
        new ItemStack(Material.IRON_HOE, 1),
        new ItemStack(Material.IRON_PICKAXE, 1),
        new ItemStack(Material.IRON_SHOVEL, 1),
        new ItemStack(Material.IRON_SWORD, 1),
    };

    return getRandomItem(toolList);
  }

  public static ItemStack RandomDiamondTool() {
    ItemStack[] toolList = {
        new ItemStack(Material.DIAMOND_AXE, 1),
        new ItemStack(Material.DIAMOND_HOE, 1),
        new ItemStack(Material.DIAMOND_PICKAXE, 1),
        new ItemStack(Material.DIAMOND_SHOVEL, 1),
        new ItemStack(Material.DIAMOND_SWORD, 1),
    };

    return getRandomItem(toolList);
  }

  public static ItemStack getTotem() {
    return new ItemStack(Material.TOTEM_OF_UNDYING, 1);
  }

  public static Enchantment getRandomEnchantment(Enchantment[] enchants) {
    return enchants[new Random().nextInt(enchants.length)];
  }

  public static ItemStack randomEnchantArmor(ItemStack item) {
    Enchantment[] enchants = {
        Enchantment.DURABILITY,
        Enchantment.PROTECTION_ENVIRONMENTAL,
        Enchantment.PROTECTION_EXPLOSIONS,
        Enchantment.PROTECTION_FIRE,
        Enchantment.PROTECTION_PROJECTILE
    };

    ItemMeta itemMeta = item.getItemMeta();
    itemMeta.addEnchant(getRandomEnchantment(enchants), new Random().nextInt(4), true);
    item.setItemMeta(itemMeta);

    return item;
  }

  public static ItemStack randomEnchantTool(ItemStack item) {
    var itemMeta = item.getItemMeta();

    Enchantment[] weaponEnchants = {
        Enchantment.DAMAGE_ALL,
        Enchantment.DURABILITY,
        Enchantment.FIRE_ASPECT,
    };

    Enchantment[] toolEnchants = {
        Enchantment.DURABILITY,
        Enchantment.DIG_SPEED,
        Enchantment.LOOT_BONUS_BLOCKS,
    };

    if(
        item.getType().equals(Material.WOODEN_SWORD) ||
        item.getType().equals(Material.IRON_SWORD) ||
        item.getType().equals(Material.DIAMOND_SWORD)
    ) {
      itemMeta.addEnchant(getRandomEnchantment(weaponEnchants), new Random().nextInt(4), true);
    } else {
      itemMeta.addEnchant(getRandomEnchantment(toolEnchants), new Random().nextInt(5), true);
    }

    item.setItemMeta(itemMeta);

    return item;
  }

  public static void clearInventory(Player player) {
    player.getInventory().clear();
  }

  public static void setKeepInventoryTime() {
    KeepInventoryTime = (60 * 10);
  }
}
