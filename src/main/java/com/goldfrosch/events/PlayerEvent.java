package com.goldfrosch.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerEvent implements Listener {
  public static int KeepInventoryTime = 0;

  @EventHandler
  public void onPlayerDeathEvent(PlayerDeathEvent e) {
    if (KeepInventoryTime > 0) {
      e.setKeepInventory(true);
      e.getDrops().clear();
    } else {
      e.setKeepInventory(false);
    }
  }
}
