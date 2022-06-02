package com.goldfrosch.data.item;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.inventory.ItemStack;

@Getter
@RequiredArgsConstructor
public class ItemVO {
  private final String key;

  private final double chance;

  private final ItemStack item;
}
