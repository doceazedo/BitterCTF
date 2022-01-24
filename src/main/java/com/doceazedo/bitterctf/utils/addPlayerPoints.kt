package com.doceazedo.bitterctf.utils

import com.doceazedo.bitterctf.Main
import com.doceazedo.bitterctf.enums.PlayerScore
import org.bukkit.entity.Player

fun addPlayerPoints(player: Player, score: PlayerScore) {
    if (Main.playerPoints[player.uniqueId] == null) Main.playerPoints[player.uniqueId] = 0
    Main.playerPoints[player.uniqueId] = Main.playerPoints[player.uniqueId] as Int + score.points
}