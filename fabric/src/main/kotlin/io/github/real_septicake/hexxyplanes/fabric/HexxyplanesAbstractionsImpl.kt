@file:JvmName("HexxyplanesAbstractionsImpl")

package io.github.real_septicake.hexxyplanes.fabric

import io.github.real_septicake.hexxyplanes.HexplaneExit
import io.github.real_septicake.hexxyplanes.HexxyplanesDimension
import io.github.real_septicake.hexxyplanes.registry.HexxyplanesRegistrar
import net.minecraft.core.Registry
import net.minecraft.world.entity.player.Player

fun <T : Any> initRegistry(registrar: HexxyplanesRegistrar<T>) {
    val registry = registrar.registry
    registrar.init { id, value -> Registry.register(registry, id, value) }
}

fun getExit(player: Player): HexplaneExit? {
    return HexxyplanesComponents.EXIT.get(player).exit
}

fun setExit(player: Player, exit: HexplaneExit?): Boolean {
    if(exit?.dimension == HexxyplanesDimension.WORLD_KEY)
        return false // disallow setting exit within the plane
    HexxyplanesComponents.EXIT.get(player).exit = exit
    return true
}