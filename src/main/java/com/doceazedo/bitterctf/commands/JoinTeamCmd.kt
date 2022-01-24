package com.doceazedo.bitterctf.commands

import com.doceazedo.bitterctf.Main
import com.doceazedo.bitterctf.enums.Team
import com.doceazedo.bitterctf.utils.equipTeamArmor
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.util.*

object JoinTeamCmd : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender is Player) return false

        if (args.size != 2 || (args[1] != "red" && args[1] != "blue")) {
            sender.sendMessage("§cUsage: /jointeam <uuid> <red/blue>")
            return false
        }

        val player = Bukkit.getPlayer(UUID.fromString(args[0]))

        if (args[1] == "red") {
            Main.playerTeam[UUID.fromString(args[0])] = Team.RED
            sender.sendMessage("${player?.displayName} added to team RED")
        }

        if (args[1] == "blue") {
            Main.playerTeam[UUID.fromString(args[0])] = Team.BLUE
            sender.sendMessage("${player?.displayName} added to team BLUE")
        }

        return true
    }
}