package io.github.real_septicake.hexxyplanes.casting.actions.spells

import at.petrak.hexcasting.api.casting.castables.ConstMediaAction
import at.petrak.hexcasting.api.casting.eval.CastingEnvironment
import at.petrak.hexcasting.api.casting.iota.Iota
import at.petrak.hexcasting.api.casting.mishaps.MishapBadCaster
import io.github.real_septicake.hexxyplanes.HexxyplanesDimension
import io.github.real_septicake.hexxyplanes.casting.mishaps.MishapNotInHexplane
import net.minecraft.server.level.ServerPlayer

object  OpExitPlane : ConstMediaAction {
    override val argc = 0

    override fun execute(args: List<Iota>, env: CastingEnvironment): List<Iota> {
        val target = env.castingEntity
        if(target !is ServerPlayer)
            throw MishapBadCaster()
        if(HexxyplanesDimension.WORLD_KEY != target.level().dimension())
            throw MishapNotInHexplane()
        HexxyplanesDimension.exitPlane(env.world, target)
        return listOf()
    }
}