package com.doceazedo.bitterctf.commands

import com.doceazedo.bitterctf.utils.startRound
import com.github.shynixn.mccoroutine.SuspendingCommandExecutor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object StartGameCmd : SuspendingCommandExecutor {
    override suspend fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender is Player) return false
        startRound(false)
        return true
    }
}