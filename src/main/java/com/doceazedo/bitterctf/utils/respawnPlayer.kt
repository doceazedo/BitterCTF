package com.doceazedo.bitterctf.utils

import com.doceazedo.bitterctf.Main
import com.doceazedo.bitterctf.enums.Team
import org.bukkit.GameMode
import org.bukkit.entity.Player
import kotlin.random.Random

fun respawnPlayer(player: Player) {
    val redSpawns = loadSpawns(Team.RED)
    val blueSpawns = loadSpawns(Team.BLUE)
    val team = Main.playerTeam[player.uniqueId]

    player.health = 20.0
    player.gameMode = GameMode.SURVIVAL
    equipTeamArmor(player)

    if (team == Team.RED) {
        val index = Random.nextInt(0, redSpawns.size - 1)
        player.teleport(lookAt(redSpawns[index], blueSpawns[index]))
    } else if (team == Team.BLUE) {
        val index = Random.nextInt(0, blueSpawns.size - 1)
        player.teleport(lookAt(blueSpawns[index], redSpawns[index]))
    }
}