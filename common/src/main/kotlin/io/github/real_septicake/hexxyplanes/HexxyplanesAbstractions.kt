@file:JvmName("HexxyplanesAbstractions")

package io.github.real_septicake.hexxyplanes

import dev.architectury.injectables.annotations.ExpectPlatform
import io.github.real_septicake.hexxyplanes.registry.HexxyplanesRegistrar
import net.minecraft.world.entity.player.Player

fun initRegistries(vararg registries: HexxyplanesRegistrar<*>) {
    for (registry in registries) {
        initRegistry(registry)
    }
}

@ExpectPlatform
fun <T : Any> initRegistry(registrar: HexxyplanesRegistrar<T>) {
    throw AssertionError()
}

@ExpectPlatform
fun getExit(player: Player) : DemiplaneExit? {
    throw AssertionError()
}

@ExpectPlatform
fun setExit(player: Player, exit: DemiplaneExit) : Boolean {
    throw AssertionError()
}