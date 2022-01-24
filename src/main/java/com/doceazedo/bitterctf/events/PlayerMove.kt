package com.doceazedo.bitterctf.events

import com.doceazedo.bitterctf.Main
import com.doceazedo.bitterctf.enums.PlayerScore
import com.doceazedo.bitterctf.enums.Team
import com.doceazedo.bitterctf.utils.addPlayerPoints
import com.doceazedo.bitterctf.utils.endGame
import com.doceazedo.bitterctf.utils.startRound
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerMoveEvent

object PlayerMove : Listener {
    @EventHandler
    public suspend fun onPlayerMove(e: PlayerMoveEvent) {
        stealFlag(e)
        hasRoundStarted(e)
    }

    private suspend fun stealFlag(e: PlayerMoveEvent) {
        val team = Main.playerTeam[e.player.uniqueId]

        if ((team == Team.RED && e.player == Main.blueThief) || (team == Team.BLUE && e.player == Main.redThief)) {
            var teamFlag = Main.redFlag
            if (team == Team.BLUE) teamFlag = Main.blueFlag

            val distance = e.player.location.distance(teamFlag!!.location)
            if (distance > 5) return

            Main.redThief = null
            Main.redFlag?.type = Material.RED_WOOL

            Main.blueThief = null
            Main.blueFlag?.type = Material.BLUE_WOOL

            addPlayerPoints(e.player, PlayerScore.CAPTURE_FLAG)

            if (team == Team.RED) Main.redPoints++ else Main.bluePoints++
            if (Main.redPoints >= 5 || Main.bluePoints >= 5) {
                endGame(team)
                return
            }

            Main.rounds++
            startRound(true, team)
        }
    }

    private fun hasRoundStarted(e: PlayerMoveEvent) {
        if (Main.isRoundActive || Main.isGameWon || e.from.block == e.to?.block) return
        e.isCancelled = true
    }
}