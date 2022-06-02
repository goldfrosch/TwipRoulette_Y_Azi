package com.goldfrosch.data.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.EntityType;

@Getter
@RequiredArgsConstructor
public class EntityVO {
  private final String key;

  private final EntityType entity;

  private final int amount;
}
