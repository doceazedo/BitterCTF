package com.doceazedo.bitterctf.events

import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityPickupItemEvent

object PickupItem : Listener {
    @EventHandler
    public fun onEntityPickupItem(e: EntityPickupItemEvent) {
        e.isCancelled = true
    }
}