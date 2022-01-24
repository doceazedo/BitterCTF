package com.doceazedo.bitterctf.utils

import com.doceazedo.bitterctf.Main
import com.doceazedo.bitterctf.enums.Team
import org.bukkit.Bukkit
import org.bukkit.GameMode

fun positionAllPlayers () {
    var redIndex = 0
    val redSpawns = loadSpawns(Team.RED)

    var blueIndex = 0
    val blueSpawns = loadSpawns(Team.BLUE)

    for (player in Bukkit.getServer().onlinePlayers) {
        val team = Main.playerTeam[player.uniqueId]

        equipTeamArmor(player)
        player.gameMode = GameMode.SURVIVAL
        player.health = 20.0

        if (team == Team.RED) {
            player.teleport(lookAt(redSpawns[redIndex], blueSpawns[redIndex]))
            redIndex++
        } else if (team == Team.BLUE) {
            player.teleport(lookAt(blueSpawns[blueIndex], redSpawns[blueIndex]))
            blueIndex++
        }
    }
}