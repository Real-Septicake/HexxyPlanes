package io.github.real_septicake.hexxyplanes.casting.actions

import at.petrak.hexcasting.api.casting.castables.ConstMediaAction
import at.petrak.hexcasting.api.casting.eval.CastingEnvironment
import at.petrak.hexcasting.api.casting.iota.Iota
import at.petrak.hexcasting.api.casting.iota.NullIota
import io.github.real_septicake.hexxyplanes.casting.iota.PlaneIota
import net.minecraft.server.level.ServerPlayer

object OpGetPlane : ConstMediaAction {
    override val argc = 0

    override fun execute(args: List<Iota>, env: CastingEnvironment): List<Iota> {
        val player = env.castingEntity ?: return listOf(NullIota())
        println(player.javaClass.canonicalName)
        if(player !is ServerPlayer)
            return listOf(NullIota())
        return listOf(PlaneIota(player))
    }
}