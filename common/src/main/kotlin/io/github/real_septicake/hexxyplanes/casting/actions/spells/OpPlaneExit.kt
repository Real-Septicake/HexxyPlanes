package io.github.real_septicake.hexxyplanes.casting.actions.spells

import at.petrak.hexcasting.api.casting.castables.ConstMediaAction
import at.petrak.hexcasting.api.casting.eval.CastingEnvironment
import at.petrak.hexcasting.api.casting.iota.Iota
import at.petrak.hexcasting.api.casting.mishaps.MishapBadCaster
import at.petrak.hexcasting.api.casting.mishaps.MishapBadLocation
import at.petrak.hexcasting.api.misc.MediaConstants
import io.github.real_septicake.hexxyplanes.HexplaneExit
import io.github.real_septicake.hexxyplanes.getVecOrNull
import io.github.real_septicake.hexxyplanes.setExit
import io.github.real_septicake.hexxyplanes.toVecI
import net.minecraft.core.BlockPos
import net.minecraft.server.level.ServerPlayer

object OpPlaneExit : ConstMediaAction {
    override val argc = 1
    override val mediaCost = MediaConstants.SHARD_UNIT * 3

    override fun execute(args: List<Iota>, env: CastingEnvironment): List<Iota> {
        val v = args.getVecOrNull(0, argc)
        if (env.castingEntity !is ServerPlayer)
            throw MishapBadCaster()
        v.ifLeft {
            env.assertVecInRange(it)
            if (!setExit(
                    env.castingEntity as ServerPlayer,
                    HexplaneExit(env.world.dimension(), BlockPos(it.toVecI()))
                )
            ) {
                throw MishapBadLocation(it, "bad_dimension")
            }
        }.ifRight {
            setExit(env.castingEntity as ServerPlayer, null)
        }
        return listOf()
    }
}