package com.doceazedo.bitterctf.events

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerDropItemEvent

object PlayerDropItem : Listener {
    @EventHandler
    public fun onPlayerDropItem(e: PlayerDropItemEvent) {
        e.isCancelled = true
    }
}