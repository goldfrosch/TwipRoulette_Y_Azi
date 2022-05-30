package com.goldfrosch.utils;

import com.goldfrosch.data.buff.BuffType;
import com.goldfrosch.data.buff.BuffVO;
import com.goldfrosch.data.buff.BuffUnit;
import com.goldfrosch.data.entity.EntityVO;
import lombok.RequiredArgsConstructor;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.WitherSkeleton;
import org.bukkit.entity.Zombie;

import java.util.Objects;

import static com.goldfrosch.TwipRoulette.plugin;
import static com.goldfrosch.utils.ChatUtils.getMessageInMinecraftColor;

@RequiredArgsConstructor
public class Constants {
  public final static String COMMAND_TITLE = "game";
  public final static FileConfiguration config = plugin.getConfig();
  public final static String TWIP_KEY = config.getString("twip.y_azi.key");

  public final static String ROULETTE_TITLE = ChatColor.AQUA + "[ " + ChatColor.BLUE + "룰렛 결과" + ChatColor.AQUA + " ]";

  public final static String CONTENT_PREFIX = getMessageInMinecraftColor(Objects.requireNonNull(config.getString("message.prefix")));

  public final static BuffVO[] BUFF_LIST = {
      new BuffVO("독 디버프", 2, 10, BuffUnit.SECOND, 10.0, BuffType.EFFECT),
      new BuffVO("화상 디버프", 1, 5,  BuffUnit.SECOND, 5.0, BuffType.EFFECT),
      new BuffVO("속도증가 버프", 1, 7,  BuffUnit.SECOND, 15.0, BuffType.EFFECT),
      new BuffVO("속도감소 버프", 1, 7,  BuffUnit.SECOND, 10.0, BuffType.EFFECT),
      new BuffVO("성급함 버프", 1, 5,  BuffUnit.SECOND, 5.0, BuffType.EFFECT),
      new BuffVO("시야 가리기", 1, 10,  BuffUnit.SECOND, 5.0, BuffType.EFFECT),
      new BuffVO("회복 버프", 1, 5,  BuffUnit.AMOUNT, 10.0, BuffType.EFFECT),
      new BuffVO("배고픔 디버프", -5, 5,  BuffUnit.AMOUNT, 10.0, BuffType.HUNGER),
      new BuffVO("배부름 버프", 5, 5,  BuffUnit.AMOUNT, 15.0, BuffType.HUNGER),
      new BuffVO("뒤돌기", 0, 0,  BuffUnit.EMPTY, 15.0, BuffType.SPIN),
  };

  public final static EntityVO[] MONSTER_LIST = {
      new EntityVO("좀비", EntityType.ZOMBIE, 1),
      new EntityVO("스켈레톤", EntityType.SKELETON, 1),
      new EntityVO("크리퍼", EntityType.CREEPER, 1),
      new EntityVO("거미", EntityType.SPIDER, 1),
      new EntityVO("좀벌레", EntityType.SILVERFISH, 1),
  };

  public final static EntityVO[] ANIMAL_LIST = {
      new EntityVO("돼지", EntityType.PIG, 1),
      new EntityVO("소", EntityType.COW, 1),
      new EntityVO("닭", EntityType.CHICKEN, 1),
      new EntityVO("양", EntityType.SHEEP, 1),
      new EntityVO("토끼", EntityType.RABBIT, 1),
  };

  public final static EntityVO VILLAGER_ENTITY = new EntityVO("주민", EntityType.VILLAGER, 1);
}
