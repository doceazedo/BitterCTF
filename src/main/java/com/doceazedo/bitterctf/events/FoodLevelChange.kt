package com.doceazedo.bitterctf.events

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.FoodLevelChangeEvent

object FoodLevelChange: Listener {
    @EventHandler
    public fun onFoodLevelChange(e: FoodLevelChangeEvent) {
        e.isCancelled = true
        e.foodLevel = 20
    }
}