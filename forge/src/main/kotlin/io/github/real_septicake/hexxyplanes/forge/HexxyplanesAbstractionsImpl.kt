@file:JvmName("HexxyplanesAbstractionsImpl")

package io.github.real_septicake.hexxyplanes.forge

import io.github.real_septicake.hexxyplanes.DemiplaneExit
import io.github.real_septicake.hexxyplanes.HexxyplanesDimension
import io.github.real_septicake.hexxyplanes.registry.HexxyplanesRegistrar
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.entity.player.Player
import net.minecraftforge.registries.RegisterEvent
import thedarkcolour.kotlinforforge.forge.MOD_BUS
import kotlin.jvm.optionals.getOrNull

val planeCache = mutableMapOf<String, ServerLevel>()

fun <T : Any> initRegistry(registrar: HexxyplanesRegistrar<T>) {
    MOD_BUS.addListener { event: RegisterEvent ->
        event.register(registrar.registryKey) { helper ->
            registrar.init(helper::register)
        }
    }
}

fun getExit(player: Player): DemiplaneExit? {
    return player.getCapability(ForgeHexxyplanes.DEMIPLANE_EXIT_CAPABILITY)
        .resolve().getOrNull()?.exit
}

fun setExit(player: Player, exit: DemiplaneExit): Boolean {
    if(HexxyplanesDimension.WORLD_KEY == exit.dimension)
        return false
    player.getCapability(ForgeHexxyplanes.DEMIPLANE_EXIT_CAPABILITY)
        .resolve().ifPresent { it.exit = exit }
    return true
}