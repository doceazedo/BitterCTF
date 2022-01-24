package com.doceazedo.bitterctf.utils

import com.doceazedo.bitterctf.Main
import com.doceazedo.bitterctf.enums.Team
import org.bukkit.Color
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.LeatherArmorMeta

fun equipTeamArmor (player: Player) {
    val color = if (Main.playerTeam[player.uniqueId] == Team.RED) Color.RED else Color.BLUE

    player.inventory.clear()
    player.inventory.helmet = ItemStack(Material.AIR, 1)
    player.inventory.chestplate = ItemStack(Material.AIR, 1)
    player.inventory.leggings = ItemStack(Material.AIR, 1)
    player.inventory.boots = ItemStack(Material.AIR, 1)

    player.inventory.setItem(0, ItemStack(Material.WOODEN_SWORD))
    player.inventory.setItem(1, ItemStack(Material.BOW))
    player.inventory.setItem(2, ItemStack(Material.ARROW, 16)) // previously, slot 17
    // player.inventory.getItem(1)?.addEnchantment(Enchantment.ARROW_INFINITE, 1)

    val helmet = ItemStack(Material.LEATHER_HELMET)
    val helmetMeta: LeatherArmorMeta = helmet.itemMeta as LeatherArmorMeta
    helmetMeta.setColor(color)
    helmet.itemMeta = helmetMeta
    player.inventory.helmet = helmet

    val chestplate = ItemStack(Material.LEATHER_CHESTPLATE)
    val chestplateMeta: LeatherArmorMeta = chestplate.itemMeta as LeatherArmorMeta
    chestplateMeta.setColor(color)
    chestplate.itemMeta = chestplateMeta
    player.inventory.chestplate = chestplate

    val legs = ItemStack(Material.LEATHER_LEGGINGS)
    val legsMeta: LeatherArmorMeta = legs.itemMeta as LeatherArmorMeta
    legsMeta.setColor(color)
    legs.itemMeta = legsMeta
    player.inventory.leggings = legs

    val boots = ItemStack(Material.LEATHER_BOOTS)
    val bootsMeta: LeatherArmorMeta = boots.itemMeta as LeatherArmorMeta
    bootsMeta.setColor(color)
    boots.itemMeta = bootsMeta
    player.inventory.boots = boots
}