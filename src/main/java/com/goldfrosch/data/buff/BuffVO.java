package com.goldfrosch.data.buff;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BuffVO {
  private final String key;

  private final double buffValue;

  private final BuffUnit unit;

  private final double chance;
}
