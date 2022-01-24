package com.doceazedo.bitterctf.events

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerItemDamageEvent

object ItemDamage : Listener {
    @EventHandler
    public fun onItemDamage (e: PlayerItemDamageEvent) {
        e.isCancelled = true
    }
}