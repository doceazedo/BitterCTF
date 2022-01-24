package com.doceazedo.bitterctf.events

import com.doceazedo.bitterctf.Main
import com.doceazedo.bitterctf.enums.PlayerScore
import com.doceazedo.bitterctf.enums.Team
import com.doceazedo.bitterctf.utils.addPlayerPoints
import com.doceazedo.bitterctf.utils.countdown
import com.doceazedo.bitterctf.utils.loadFlags
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.inventory.ItemStack

object PlayerInteract : Listener {
    @EventHandler
    public fun playerInteract(e: PlayerInteractEvent) {
        if ((e.action == Action.RIGHT_CLICK_BLOCK || e.action == Action.LEFT_CLICK_BLOCK) && e.hand == EquipmentSlot.HAND) {

            if (e.player.hasPermission("bitterctf.admin") && e.player.inventory.itemInMainHand == ItemStack(Material.STICK)) {
                var loc = e.clickedBlock?.location
                var team: String? = null

                // FIXME: blocked by "create setup command"
                if (e.clickedBlock?.type == Material.RED_WOOL) team = "red"
                if (e.clickedBlock?.type == Material.BLUE_WOOL) team = "blue"
                if (team == null) return

                e.isCancelled = true

                Main.instance?.config?.set("flags.$team.world", loc?.world?.name)
                Main.instance?.config?.set("flags.$team.x", loc?.x)
                Main.instance?.config?.set("flags.$team.y", loc?.y)
                Main.instance?.config?.set("flags.$team.z", loc?.z)
                // TODO: Save block type

                Main.instance?.saveConfig()
                Main.instance?.reloadConfig()
                loadFlags()

                e.player.sendMessage("§aDefinido a flag do time §e$team§a!")

                return
            }

            val team = Main.playerTeam[e.player.uniqueId]
            val thiefName = e.player.displayName
            var flagWasCaptured = false

            if (team == Team.RED && e.clickedBlock == Main.blueFlag) {
                flagWasCaptured = true

                e.clickedBlock?.type = Material.AIR
                e.player.inventory.helmet = ItemStack(Material.BLUE_BANNER)
                Main.blueThief = e.player
            }

            if (team == Team.BLUE && e.clickedBlock == Main.redFlag) {
                flagWasCaptured = true

                e.clickedBlock?.type = Material.AIR
                e.player.inventory.helmet = ItemStack(Material.RED_BANNER)
                Main.redThief = e.player
            }

            if (flagWasCaptured) {
                e.player.playSound(e.player.location, Sound.ENTITY_ITEM_PICKUP, 1f, 1f)
                addPlayerPoints(e.player, PlayerScore.PICK_FLAG)

                for (player in Bukkit.getServer().onlinePlayers) {
                    if (Main.playerTeam[player.uniqueId] != team) {
                        player.sendMessage("§4$thiefName §ccapturou a bandeira da sua equipe!")
                        if (player != e.player) player.playSound(player.location, Sound.ENTITY_BAT_TAKEOFF, 2f, 1f)
                    } else {
                        player.sendMessage("§e$thiefName §acapturou a bandeira inimiga!")
                        player.playSound(player.location, Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f)
                    }
                }
            }
        }
    }
}