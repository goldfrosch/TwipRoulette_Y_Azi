package com.goldfrosch.data.buff;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum BuffUnit {
  EMPTY(""),
  SECOND("초"),
  AMOUNT("칸");

  private final String value;
}
