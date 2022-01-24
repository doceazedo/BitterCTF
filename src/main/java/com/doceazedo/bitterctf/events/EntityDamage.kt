package com.doceazedo.bitterctf.events

import com.doceazedo.bitterctf.Main
import com.doceazedo.bitterctf.enums.PlayerScore
import com.doceazedo.bitterctf.enums.Team
import com.doceazedo.bitterctf.utils.addPlayerPoints
import com.doceazedo.bitterctf.utils.respawnPlayer
import kotlinx.coroutines.delay
import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.entity.Arrow
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.EntityDamageEvent

object EntityDamage: Listener {
    @EventHandler
    public suspend fun onEntityDamageByEntity(e: EntityDamageByEntityEvent) {
        if (e.entity !is Player) return
        val victim = e.entity as Player
        val attacker = if (e.damager is Player) e.damager as Player else
            if (e.damager is Arrow) ((e.damager as Arrow).shooter as Player) else null

        if (Main.playerTeam[victim.uniqueId] == Main.playerTeam[attacker?.uniqueId] || !Main.isRoundActive) {
            e.isCancelled = true
            return
        }

        if (victim.health - e.finalDamage <= 0) {
            e.isCancelled = true
            victim.gameMode = GameMode.SPECTATOR
            victim.health = 20.0

            if (attacker != null) {
                val victimName = victim.displayName
                val victimColor = getColor(Main.playerTeam[victim.uniqueId])
                val victimTeam = Main.playerTeam[victim.uniqueId]
                var victimWasThief = false

                val attackerName = attacker.displayName
                val attackerColor = getColor(Main.playerTeam[attacker.uniqueId])

                Bukkit.broadcastMessage("$attackerColor$attackerName §fmatou $victimColor$victimName")
                attacker.playSound(attacker.location, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f)

                addPlayerPoints(attacker, PlayerScore.KILL_ENEMY)

                if (victim == Main.redThief) {
                    Main.redThief = null
                    Main.redFlag?.type = Material.RED_WOOL
                    victimWasThief = true
                }

                if (victim == Main.blueThief) {
                    Main.blueThief = null
                    Main.blueFlag?.type = Material.RED_WOOL
                    victimWasThief = true
                }

                if (victimWasThief) {
                    addPlayerPoints(attacker, PlayerScore.RECOVER_FLAG)

                    for (player in Bukkit.getServer().onlinePlayers) {
                        if (Main.playerTeam[player.uniqueId] != victimTeam) {
                            player.sendMessage("§4$attackerName §crecuperou a bandeira inimiga!")
                            player.playSound(player.location, Sound.ENTITY_BAT_TAKEOFF, 1f, 1f)
                        } else {
                            player.sendMessage("§e$attackerName §arecuperou a bandeira da sua equipe!")
                            player.playSound(player.location, Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f)
                        }
                    }
                }
            }

            val diedAtRound = Main.rounds
            for (i in 0..9) {
                if (diedAtRound != Main.rounds) return
                countdown(victim, 10 - i)
                delay(1000)
            }
            victim.sendTitle("§a§lVOCÊ RENASCEU!", "", 0, 40, 10)
            respawnPlayer(victim)
        }
    }

    @EventHandler
    public fun onEntityDamage(e: EntityDamageEvent) {
        if (e.entity is Player && e.cause == EntityDamageEvent.DamageCause.FALL) {
            e.isCancelled = true
        }
    }

    private fun countdown(player: Player, seconds: Int) {
        val plural = if (seconds > 1) "s" else ""
        player.sendTitle("§c§lVOCÊ MORREU!", "§e§lVocê vai renascer em §c§l$seconds segundo$plural§e§l...", 0, 40, 0)
    }

    private fun getColor(team: Team?): String {
        if (team == Team.RED) return "§4"
        return "§1"
    }
}