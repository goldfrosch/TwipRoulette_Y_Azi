package com.goldfrosch.data.buff;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BuffUnit {
  EMPTY(""),
  SECOND("초"),
  AMOUNT("칸");

  private final String value;
}
