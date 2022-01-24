package com.doceazedo.bitterctf.events

import org.bukkit.GameMode
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack

object InventoryClick : Listener {
    @EventHandler
    public fun onInventoryClick (e: InventoryClickEvent) {
        if (e.whoClicked.gameMode != GameMode.SURVIVAL) return
        if (e.currentItem == null || e.currentItem == ItemStack(Material.AIR)) return
        e.isCancelled = true
    }
}