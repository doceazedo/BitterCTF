package com.doceazedo.bitterctf.utils

import com.doceazedo.bitterctf.Main
import com.doceazedo.bitterctf.enums.Team
import kotlinx.coroutines.delay
import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.Sound
import org.bukkit.entity.Player

suspend fun startRound(isRestarting: Boolean, winner: Team? = null) {
    Main.isRoundActive = false
    positionAllPlayers()

    for (player in Bukkit.getServer().onlinePlayers) {
        if (isRestarting) {
            val redPoints = Main.redPoints
            val bluePoints = Main.bluePoints
            var title = "§c§lDERROTA"
            var subtitle = "§4[V] §f$redPoints x $bluePoints §1[A]"

            if (Main.playerTeam[player.uniqueId] == winner) {
                title = "§a§lVITÓRIA"
                player.playSound(player.location, Sound.ENTITY_PLAYER_LEVELUP, 1f, 1f)
            } else {
                player.playSound(player.location, Sound.ENTITY_ENDER_DRAGON_GROWL, 1f, 1f)
            }

            if (Main.playerTeam[player.uniqueId] == Team.BLUE) {
                subtitle = "§1[A] §f$bluePoints x $redPoints §4[V]"
            }

            player.sendTitle(title, subtitle, 0, 100, 10)
        } else {
            var title = "§4§lEquipe Vermelha"
            var subtitle = "§fCapture a §1bandeira azul §fe proteja a sua!"

            if (Main.playerTeam[player.uniqueId] == Team.BLUE) {
                title = "§1§lEquipe Azul"
                subtitle = "§fCapture a §4bandeira vermelha §fe proteja a sua!"
            }

            player.sendTitle(title, subtitle, 0, 100, 10)
        }
    }
    delay(6000)

    for (i in 0..4) {
        for (player in Bukkit.getServer().onlinePlayers) {
            countdown(player, 5 - i)
        }
        delay(1000)
    }

    for (player in Bukkit.getServer().onlinePlayers) {
        player.sendTitle("§a§lBOA SORTE!", "Capture a bandeira inimiga!", 0, 40, 10)
        player.playSound(player.location, Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 2f)
    }

    Main.isRoundActive = true
}

fun countdown(player: Player, seconds: Int) {
    val round = Main.rounds + 1
    val plural = if (seconds > 1) "s" else ""
    player.sendTitle("§6§lRODADA $round", "§e§lRodada começando em §c§l$seconds segundo$plural§e§l...", 0, 40, 0)
    player.playSound(player.location, Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f)
}