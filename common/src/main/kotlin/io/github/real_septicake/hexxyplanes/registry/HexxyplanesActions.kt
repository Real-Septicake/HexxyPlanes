@file:Suppress("unused")

package io.github.real_septicake.hexxyplanes.registry

import at.petrak.hexcasting.api.casting.ActionRegistryEntry
import at.petrak.hexcasting.api.casting.castables.Action
import at.petrak.hexcasting.api.casting.math.HexDir
import at.petrak.hexcasting.api.casting.math.HexPattern
import at.petrak.hexcasting.common.lib.HexRegistries
import at.petrak.hexcasting.common.lib.hex.HexActions
import io.github.real_septicake.hexxyplanes.casting.actions.OpGetPlane
import io.github.real_septicake.hexxyplanes.casting.actions.spells.*

object HexxyplanesActions : HexxyplanesRegistrar<ActionRegistryEntry>(
    HexRegistries.ACTION,
    { HexActions.REGISTRY },
) {
    val GET_PLANE = make("get_plane", HexDir.NORTH_EAST, "dwawd") { OpGetPlane }
    val PLANE_POS = make("plane_pos", HexDir.NORTH_EAST, "dwawdaqqwwqq") { OpPlanePosition }
    val ENTER_PLANE = make("enter_plane", HexDir.NORTH_EAST, "dwawdeewedwwqqqwwqq") { OpEnterPlane }
    val PLANE_EXIT = make("plane_exit", HexDir.NORTH_EAST, "dwawdqaqwwwqaeqwq") { OpPlaneExit }
    val PLANE_EXIT_REVEAL = make("exit_reveal", HexDir.NORTH_EAST, "dwawdqaqwwwqa") { OpExitReveal }
    val PLANE_BANISH = make("plane_banish", HexDir.NORTH_EAST, "dwawddww") { OpPlaneBanish }

    fun make(name: String, startDir: HexDir, signature: String, getAction: () -> Action) = register(name) {
        ActionRegistryEntry(HexPattern.fromAngles(signature, startDir), getAction())
    }
}
