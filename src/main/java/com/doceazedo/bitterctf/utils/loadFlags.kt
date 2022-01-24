package com.doceazedo.bitterctf.utils

import com.doceazedo.bitterctf.Main
import org.bukkit.Bukkit.*
import org.bukkit.Location
import org.bukkit.World


fun loadFlags () {
    val teams: List<String> = listOf("red", "blue")

    for (team in teams) {
        val w: World = Main.instance!!.server.getWorld(Main.instance!!.config.getString("flags.$team.world").toString()) ?: return
        val x: Double = Main.instance!!.config.getDouble("flags.$team.x")
        val y: Double = Main.instance!!.config.getDouble("flags.$team.y")
        val z: Double = Main.instance!!.config.getDouble("flags.$team.z")

        if (team == "red") {
            Main.redFlag = Location(w, x, y, z).block
        } else if (team == "blue") {
            Main.blueFlag = Location(w, x, y, z).block
        }

        broadcastMessage("$team | w:$w x:$x y:$y z:$z")
    }
}