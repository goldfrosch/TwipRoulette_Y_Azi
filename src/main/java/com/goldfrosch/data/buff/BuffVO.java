package com.goldfrosch.data.buff;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

@Getter
@RequiredArgsConstructor
public class BuffVO {
  private final String key;

  private final int buffValue;

  private final int buffTime;

  private final BuffUnit unit;

  private final double chance;

  private final BuffType buffType;

  public void setPlayerPotionEffect(Player player, PotionEffectType effect) {
    player.addPotionEffect(effect.createEffect(buffTime * 20, buffValue));
  }

  public void setPlayerBurn(Player player) {
    player.setFireTicks(buffTime * 20);
  }

  public void setPlayerHunger(Player player) {
    player.setFoodLevel(
        Math.max((player.getFoodLevel() + buffValue), 0)
    );
  }
}
