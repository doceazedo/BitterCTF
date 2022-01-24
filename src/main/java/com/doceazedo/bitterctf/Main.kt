package com.doceazedo.bitterctf

import com.doceazedo.bitterctf.commands.JoinTeamCmd
import com.doceazedo.bitterctf.commands.StartGameCmd
import com.doceazedo.bitterctf.enums.Team
import com.doceazedo.bitterctf.events.*
import com.doceazedo.bitterctf.utils.loadFlags
import com.github.shynixn.mccoroutine.launch
import com.github.shynixn.mccoroutine.registerSuspendingEvents
import com.github.shynixn.mccoroutine.setSuspendingExecutor
import org.bukkit.Bukkit
import org.bukkit.block.Block
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import java.util.*
import kotlin.collections.HashMap

class Main : JavaPlugin() {
    companion object {
        var instance: Main? = null

        var redFlag: Block? = null
        var redThief: Player? = null
        var redPoints: Int = 0

        var blueFlag: Block? = null
        var blueThief: Player? = null
        var bluePoints: Int = 0

        var rounds: Int = 0
        var playerPoints: HashMap<UUID, Int> = HashMap<UUID, Int>()
        var playerTeam: HashMap<UUID, Team> = HashMap<UUID, Team>()
        var isRoundActive: Boolean = true
        var isGameWon: Boolean = false
    }

    override fun onEnable() {
        launch{}

        instance = this

        getCommand("jointeam")?.setExecutor(JoinTeamCmd)
        getCommand("startgame")?.setSuspendingExecutor(StartGameCmd)

        Bukkit.getPluginManager().registerEvents(PlayerInteract, this)
        Bukkit.getPluginManager().registerEvents(FoodLevelChange, this)
        Bukkit.getPluginManager().registerEvents(ItemDamage, this)
        Bukkit.getPluginManager().registerEvents(InventoryClick, this)
        Bukkit.getPluginManager().registerEvents(PlayerDropItem, this)
        Bukkit.getPluginManager().registerEvents(PickupItem, this)
        Bukkit.getPluginManager().registerSuspendingEvents(PlayerMove, this)
        Bukkit.getPluginManager().registerSuspendingEvents(EntityDamage, this)

        instance?.saveDefaultConfig()
        loadFlags()
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}