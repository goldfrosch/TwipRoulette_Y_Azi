package com.goldfrosch.events;

import com.goldfrosch.MainPlugin;
import lombok.RequiredArgsConstructor;
import org.bukkit.event.Listener;

@RequiredArgsConstructor
public class NewEvent implements Listener {
    private final MainPlugin plugin;
}
