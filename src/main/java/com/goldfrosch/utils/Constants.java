package com.goldfrosch.utils;

import com.goldfrosch.data.buff.BuffVO;
import com.goldfrosch.data.buff.BuffUnit;
import lombok.RequiredArgsConstructor;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.Objects;

import static com.goldfrosch.TwipRoulette.plugin;
import static com.goldfrosch.utils.ChatUtils.getMessageInMinecraftColor;

@RequiredArgsConstructor
public class Constants {
  public final static FileConfiguration config = plugin.getConfig();
  public final static String TWIP_KEY = config.getString("twip.y_azi.key");

  public final static String ROULETTE_TITLE = ChatColor.AQUA + "[ " + ChatColor.BLUE + "룰렛 결과" + ChatColor.AQUA + " ]";

  public final static String CONTENT_PREFIX = getMessageInMinecraftColor(Objects.requireNonNull(config.getString("message.prefix")));

  public final static BuffVO[] BUFF_LIST = {
      new BuffVO("독 디버프", 1, BuffUnit.SECOND, 10.0),
      new BuffVO("화상 디버프", 1, BuffUnit.SECOND, 5.0),
      new BuffVO("배고픔 디버프", 1, BuffUnit.AMOUNT, 10.0),
      new BuffVO("배부름 버프", 1, BuffUnit.AMOUNT, 15.0),
      new BuffVO("뒤돌기", 1, BuffUnit.EMPTY, 15.0),
      new BuffVO("회복", 1, BuffUnit.AMOUNT, 10.0),
      new BuffVO("속도 증가", 1, BuffUnit.SECOND, 15.0),
      new BuffVO("속도 감소", 1, BuffUnit.SECOND, 10.0),
      new BuffVO("성급함", 1, BuffUnit.SECOND, 5.0),
      new BuffVO("시야 가리기", 1, BuffUnit.SECOND, 5.0),
  };
}
