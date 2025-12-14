package io.github.real_septicake.hexxyplanes.fabric.interop.oneironaut

import at.petrak.hexcasting.api.casting.math.HexDir
import io.github.real_septicake.hexxyplanes.fabric.interop.oneironaut.casting.actions.OpExitBreakdown
import io.github.real_septicake.hexxyplanes.registry.HexxyplanesActions

object OneironautCompat {
    fun load() {
        HexxyplanesActions.make("exit_deconstruct", HexDir.NORTH_EAST, "dwawdaawwwa") { OpExitBreakdown }
    }
}