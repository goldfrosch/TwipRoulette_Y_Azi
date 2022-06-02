package com.goldfrosch.utils;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import static com.goldfrosch.utils.Constants.CONTENT_PREFIX;
import static com.goldfrosch.utils.Constants.DISASTER_LIST;

import static com.goldfrosch.TwipRoulette.plugin;

public class DisasterUtils {
  public static void setEntityWaterInRadius(LivingEntity entity) {
    var entityLoc = entity.getLocation();
    var blockX = entityLoc.getBlockX();
    var blockZ = entityLoc.getBlockZ();
    var blockY = entityLoc.getBlockY();

    List<Block> blocks = new ArrayList<>();

    for (int x = blockX - 3; x <= blockX + 3; x++){
      for (int z = blockZ - 1; z <= blockZ + 1; z++){
        for(int y = 0; y < 5; y++) {
          blocks.add(Objects.requireNonNull(entityLoc.getWorld()).getBlockAt(x, blockY + y, z));
        }
      }
    }

    for (int x = blockX - 1; x <= blockX + 1; x++){
      for (int z = blockZ - 3; z <= blockZ + 3; z++){
        for(int y = 0; y < 5; y++) {
          blocks.add(Objects.requireNonNull(entityLoc.getWorld()).getBlockAt(x, blockY + y, z));
        }
      }
    }

    for (Block block : blocks){
      block.setType(Material.WATER);
    }
  }

  public static void killEntitiesInPlayerRadius(Player player) {
    for(Entity entity: player.getNearbyEntities(15, 1, 15)) {
      if(!(entity instanceof Player) && entity instanceof LivingEntity) {
        ((LivingEntity) entity).setHealth(0);
      }
    }
  }

  public static void spawnEntityTnt(Player player) {
    player.getWorld().spawnEntity(player.getLocation().add(0.0D, 1.0D, 1.0D), EntityType.PRIMED_TNT);
    player.getWorld().spawnEntity(player.getLocation().add(1.0D, 1.0D, 0.0D), EntityType.PRIMED_TNT);
    player.getWorld().spawnEntity(player.getLocation().add(0.0D, 1.0D, 0.0D), EntityType.PRIMED_TNT);
    player.getWorld().spawnEntity(player.getLocation().add(-1.0D, 1.0D, 0.0D), EntityType.PRIMED_TNT);
    player.getWorld().spawnEntity(player.getLocation().add(0.0D, 1.0D, -1.0D), EntityType.PRIMED_TNT);
  }

  public static void createWaterJail(Player player) {
    Axolotl waterBomb = (Axolotl) player.getWorld().spawnEntity(player.getLocation(), EntityType.AXOLOTL);
    waterBomb.setCustomName("뭘 봐");
    waterBomb.setVariant(Axolotl.Variant.BLUE);

    Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
      setEntityWaterInRadius(waterBomb);
      player.setRemainingAir(0);
      player.playSound(player.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1, 1);
      player.spawnParticle(Particle.EXPLOSION_LARGE, waterBomb.getLocation(), 2);
      waterBomb.setHealth(0);
    }, 20 * 2);
  }

  public static void spawnBossEntity(Player player) {
    EntityType[] list = {
        EntityType.RAVAGER,
        EntityType.PIGLIN_BRUTE,
        EntityType.CAVE_SPIDER
    };

    if (player.getLocation().getBlock().getType().equals(Material.WATER)) {
      player.getWorld().spawnEntity(player.getLocation(), EntityType.GUARDIAN);
      player.getWorld().spawnEntity(player.getLocation(), EntityType.GUARDIAN);
    } else {
      var random = new Random().nextInt(list.length);

      if(random < 2) {
        player.getWorld().spawnEntity(player.getLocation(), list[random]);
      } else {
        player.getWorld().spawnEntity(player.getLocation(), list[random]);
        player.getWorld().spawnEntity(player.getLocation(), list[random]);
        player.getWorld().spawnEntity(player.getLocation(), list[random]);
        player.getWorld().spawnEntity(player.getLocation(), list[random]);
      }
    }

  }

  public static void playDisaster(Player player, int key) {
    switch (key) {
      case 1:
        player.getWorld().getBlockAt(player.getLocation()).setType(Material.LAVA);
        player.sendMessage(CONTENT_PREFIX + "뜨거운 열기의 현장");
        break;
      case 3:
        killEntitiesInPlayerRadius(player);
        player.sendMessage(CONTENT_PREFIX + "주변 15칸의 몬스터를 전부 죽입니다");
        break;
      case 5:
        player.setHealth(0);
        player.sendMessage(CONTENT_PREFIX + "- ㅋ -");
        break;
      case 6:
        spawnEntityTnt(player);
        player.sendMessage(CONTENT_PREFIX + "예술은 폭발이다!");
        break;
      case 7:
        spawnBossEntity(player);
        player.sendMessage(CONTENT_PREFIX + "강력한 몬스터가 접근합니다");
      case 8:
        createWaterJail(player);
        player.sendMessage(CONTENT_PREFIX + "귀여운 아홀로틀이다");
    }
  }

  public static void setPlayerRandomDisaster(Player player) {
    var random = Math.random() * 100;
    for (int i = 0; i < DISASTER_LIST.length; i++) {
      var chance = 0;
      if (i != 0) {
        for (int j = 0; j < i; j++) {
          chance += DISASTER_LIST[j].getChance();
        }
      }

      if (chance < random && random <= chance + DISASTER_LIST[i].getChance()) {
        playDisaster(player, i);
        break;
      }
    }
  }
}
