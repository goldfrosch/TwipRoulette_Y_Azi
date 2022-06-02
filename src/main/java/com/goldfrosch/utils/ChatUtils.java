package com.goldfrosch.utils;

import org.bukkit.ChatColor;

import static com.goldfrosch.utils.Constants.CONTENT_PREFIX;

public class ChatUtils {
  public static String setPrefix(String msg) {
    return CONTENT_PREFIX + msg;
  }

  public static String getMessageInMinecraftColor(String msg) {
    return msg.replace("&", "§");
  }

  public static String setLoreString(String msg) {
    return ChatColor.GRAY + "- " + msg;
  }
}
