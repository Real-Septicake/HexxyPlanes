package io.github.real_septicake.hexxyplanes.casting.mishaps

import at.petrak.hexcasting.api.casting.eval.CastingEnvironment
import at.petrak.hexcasting.api.casting.iota.Iota
import at.petrak.hexcasting.api.casting.mishaps.Mishap
import net.minecraft.world.item.DyeColor

class MishapNotInDemiplane : Mishap() {
    override fun accentColor(ctx: CastingEnvironment, errorCtx: Context) = dyeColor(DyeColor.GRAY)

    override fun errorMessage(ctx: CastingEnvironment, errorCtx: Context) = error("not_in_demiplane")

    override fun execute(env: CastingEnvironment, errorCtx: Context, stack: MutableList<Iota>) {
        // NO-OP
    }

}