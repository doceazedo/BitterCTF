package com.doceazedo.bitterctf.utils

import com.doceazedo.bitterctf.Main
import com.doceazedo.bitterctf.enums.Team
import org.bukkit.Bukkit
import org.bukkit.Sound
import java.util.*

suspend fun endGame (winner: Team) {
    Main.isRoundActive = false
    Main.isGameWon = true
    positionAllPlayers()

    // val ranking = Main.playerPoints.toList().sortedBy { (_, value) -> value}.toMap()
    val winnerColoredStr = if (winner == Team.RED) "§4§lequipe vermelha" else "§1§lequipe azul"
    val winnerCapsStr = if (winner == Team.RED) "VERMELHA" else "AZUL"
    val hr = "-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-"

    for (player in Bukkit.getServer().onlinePlayers) {
        val color = if (Main.playerTeam[player.uniqueId] == winner) "§a" else "§c"

        if (Main.playerTeam[player.uniqueId] == winner) {
            player.sendTitle("§a§lVITÓRIA!", "Parabéns, a $winnerColoredStr venceu!", 0, 80, 10)
            player.playSound(player.location, Sound.UI_TOAST_CHALLENGE_COMPLETE, 1f, 1f)
        } else {
            player.sendTitle("§c§lDERROTA!", "A $winnerColoredStr venceu.", 0, 80, 10)
            player.playSound(player.location, Sound.ENTITY_GHAST_DEATH, 1f, 1f)
        }

        var score = Main.playerPoints[player.uniqueId]
        if (score == null) score = 0

        player.sendMessage(" ")
        player.sendMessage("${color}$hr")
        player.sendMessage(" ")
        player.sendMessage("${color}VITÓRIA DA EQUIPE $winnerCapsStr!")
        player.sendMessage("${color}Você fez §e$score pontos${color}.")
        player.sendMessage(" ")
        player.sendMessage("${color}$hr")
        player.sendMessage(" ")
    }
}