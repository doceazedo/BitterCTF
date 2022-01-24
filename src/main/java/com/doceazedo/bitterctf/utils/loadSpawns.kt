package com.doceazedo.bitterctf.utils

import com.doceazedo.bitterctf.Main
import com.doceazedo.bitterctf.enums.Team
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.World
import java.util.logging.Level

fun loadSpawns (team: Team): List<Location> {
    val teamStr = if (team == Team.RED) "red" else "blue"
    val spawns = Main.instance!!.config.getConfigurationSection("spawns.$teamStr")?.getKeys(false)
    val spawnLocations: MutableList<Location> = mutableListOf()

    if (spawns == null) return listOf<Location>()

    for(spawn in spawns) {
        val w: World? = Main.instance!!.server.getWorld(Main.instance!!.config.getString("spawns.$teamStr.$spawn.world").toString())
        val x: Double = Main.instance!!.config.getDouble("spawns.$teamStr.$spawn.x")
        val y: Double = Main.instance!!.config.getDouble("spawns.$teamStr.$spawn.y")
        val z: Double = Main.instance!!.config.getDouble("spawns.$teamStr.$spawn.z")

        spawnLocations.add(Location(w, x, y, z))
    }

    return spawnLocations
}