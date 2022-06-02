package com.goldfrosch.data.disaster;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
@Getter
@RequiredArgsConstructor
public class DisasterVO {
  private final String key;
  private final double chance;
}
