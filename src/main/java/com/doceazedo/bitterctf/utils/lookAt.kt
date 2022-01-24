package com.doceazedo.bitterctf.utils

import org.bukkit.Location

fun lookAt(loc: Location, target: Location): Location {
    var loc: Location = loc
    loc = loc.clone()

    val dx: Double = target.getX() - loc.getX()
    val dy: Double = target.getY() - loc.getY()
    val dz: Double = target.getZ() - loc.getZ()

    if (dx != 0.0) {
        if (dx < 0) {
            loc.setYaw((1.5 * Math.PI).toFloat())
        } else {
            loc.setYaw((0.5 * Math.PI).toFloat())
        }
        loc.setYaw(loc.getYaw() as Float - Math.atan(dz / dx).toFloat())
    } else if (dz < 0) {
        loc.setYaw(Math.PI.toFloat())
    }

    val dxz = Math.sqrt(Math.pow(dx, 2.0) + Math.pow(dz, 2.0))

    loc.setPitch(-Math.atan(dy / dxz).toFloat())

    loc.setYaw(-loc.getYaw() * 180f / Math.PI.toFloat())
    loc.setPitch(loc.getPitch() * 180f / Math.PI.toFloat())
    return loc
}