package com.goldfrosch.data.item;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ItemVO {
  private final String key;

  private final double chance;
}
