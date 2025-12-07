package io.github.real_septicake.hexxyplanes.casting.actions.spells

import at.petrak.hexcasting.api.casting.castables.ConstMediaAction
import at.petrak.hexcasting.api.casting.eval.CastingEnvironment
import at.petrak.hexcasting.api.casting.getPlayer
import at.petrak.hexcasting.api.casting.iota.Iota
import io.github.real_septicake.hexxyplanes.HexxyplanesDimension
import io.github.real_septicake.hexxyplanes.getPlane

object OpPlaneKidnap : ConstMediaAction {
    override val argc = 2

    override fun execute(args: List<Iota>, env: CastingEnvironment): List<Iota> {
        val p = args.getPlayer(0, argc)
        val dest = args.getPlane(1, argc)
        env.assertEntityInRange(p)
        HexxyplanesDimension.goToPlane(env.world, p, dest.player.uuid)
        return listOf()
    }
}