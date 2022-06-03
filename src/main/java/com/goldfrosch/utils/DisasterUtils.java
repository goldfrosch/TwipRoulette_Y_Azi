package com.goldfrosch.utils;

import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.disguisetypes.PlayerDisguise;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import static com.goldfrosch.utils.Constants.CONTENT_PREFIX;
import static com.goldfrosch.utils.Constants.DISASTER_LIST;

import static com.goldfrosch.TwipRoulette.plugin;

public class DisasterUtils {
  public static void setAnvilDrop(Player player) {
    var playerLoc = player.getLocation();
    var blockX = playerLoc.getBlockX();
    var blockZ = playerLoc.getBlockZ();
    var blockY = playerLoc.getBlockY();

    List<Block> blocks = new ArrayList<>();

    for (int x = blockX - 4; x <= blockX + 4; x++){
      for (int z = blockZ - 1; z <= blockZ + 1; z++){
        blocks.add(Objects.requireNonNull(playerLoc.getWorld()).getBlockAt(x, blockY + 6, z));
      }
    }

    for (int x = blockX - 1; x <= blockX + 1; x++){
      for (int z = blockZ - 4; z <= blockZ + 4; z++){
          blocks.add(Objects.requireNonNull(playerLoc.getWorld()).getBlockAt(x, blockY + 6, z));
      }
    }

    for (Block block : blocks){
      block.setType(Material.ANVIL);
    }
  }

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

    new BukkitRunnable() {
      @Override
      public void run() {
        setEntityWaterInRadius(waterBomb);
        player.setRemainingAir(0);
        player.playSound(player.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1, 1);
        player.spawnParticle(Particle.EXPLOSION_LARGE, waterBomb.getLocation(), 2);
        waterBomb.setHealth(0);
      }
    }.runTaskLater(plugin, 20L);
  }

  public static void spawnBossEntity(Player player) {
    if (player.getLocation().getBlock().getType().equals(Material.WATER)) {
      player.getWorld().spawnEntity(player.getLocation(), EntityType.GUARDIAN);
      player.getWorld().spawnEntity(player.getLocation(), EntityType.GUARDIAN);
    } else {
      var random = new Random().nextInt(3);
      if(random == 0) {
        player.getWorld().spawnEntity(player.getLocation(), EntityType.RAVAGER);
      } else if(random == 1) {
        player.getWorld().spawnEntity(player.getLocation(), EntityType.VINDICATOR);
      } else {
        player.getWorld().spawnEntity(player.getLocation(), EntityType.CAVE_SPIDER);
        player.getWorld().spawnEntity(player.getLocation(), EntityType.CAVE_SPIDER);
        player.getWorld().spawnEntity(player.getLocation(), EntityType.CAVE_SPIDER);
        player.getWorld().spawnEntity(player.getLocation(), EntityType.CAVE_SPIDER);
      }
    }

  }

  public static void setPlayerOverEarth(Player player, int Y) {
    player.teleport(new Location(player.getWorld(), player.getLocation().getBlockX(), Y, player.getLocation().getBlockZ()));
    player.playSound(player.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 2, 2);
  }

  public static void spawnBrothers(Player player) {
    Location loc = player.getLocation();
    Zombie brother1 = (Zombie) player.getWorld().spawnEntity(loc, EntityType.ZOMBIE);
    Zombie brother2 = (Zombie) player.getWorld().spawnEntity(loc, EntityType.ZOMBIE);
    Zombie brother3 = (Zombie) player.getWorld().spawnEntity(loc, EntityType.ZOMBIE);
    Zombie brother4 = (Zombie) player.getWorld().spawnEntity(loc, EntityType.ZOMBIE);

    brother1.setBaby();
    brother2.setBaby();
    brother3.setBaby();
    brother4.setBaby();

    DisguiseAPI.disguiseEntity(brother1, new PlayerDisguise("Y_noeul"));
    DisguiseAPI.disguiseEntity(brother2, new PlayerDisguise("Y_noeul"));
    DisguiseAPI.disguiseEntity(brother3, new PlayerDisguise("Y_noeul"));
    DisguiseAPI.disguiseEntity(brother4, new PlayerDisguise("Y_noeul"));
  }


  public static void playDisaster(Player player, int key) {
    RouletteUtils.ShowRouletteResultTitleMsg(player, DISASTER_LIST[key].getKey());
    player.sendMessage(CONTENT_PREFIX + DISASTER_LIST[key].getKey());
    switch (key) {
      case 0:
        player.sendMessage(CONTENT_PREFIX + "땡!");
        setAnvilDrop(player);
        break;
      case 1:
        player.sendMessage(CONTENT_PREFIX + "뜨거운 열기의 현장");
        player.getWorld().getBlockAt(player.getLocation()).setType(Material.LAVA);
        break;
      case 2:
        player.sendMessage(CONTENT_PREFIX + "우주 체험");
        setPlayerOverEarth(player, 240);
        break;
      case 3:
        player.sendMessage(CONTENT_PREFIX + "주변 15칸의 몬스터를 전부 죽입니다");
        killEntitiesInPlayerRadius(player);
        break;
      case 4:
        player.sendMessage(CONTENT_PREFIX + "가족과 즐거운 시간 보내세요~");
        spawnBrothers(player);
        break;
      case 5:
        player.sendMessage(CONTENT_PREFIX + "- ㅋ -");
        player.setHealth(0);
        break;
      case 6:
        player.sendMessage(CONTENT_PREFIX + "예술은 폭발이다!");
        spawnEntityTnt(player);
        break;
      case 7:
        player.sendMessage(CONTENT_PREFIX + "강력한 몬스터가 접근합니다");
        spawnBossEntity(player);
        break;
      case 8:
        player.sendMessage(CONTENT_PREFIX + "귀여운 아홀로틀이다");
        createWaterJail(player);
        break;
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
