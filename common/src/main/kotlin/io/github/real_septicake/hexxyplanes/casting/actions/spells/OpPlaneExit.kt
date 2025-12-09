package io.github.real_septicake.hexxyplanes.casting.actions.spells

import at.petrak.hexcasting.api.casting.castables.ConstMediaAction
import at.petrak.hexcasting.api.casting.eval.CastingEnvironment
import at.petrak.hexcasting.api.casting.getVec3
import at.petrak.hexcasting.api.casting.iota.Iota
import at.petrak.hexcasting.api.casting.mishaps.MishapBadCaster
import at.petrak.hexcasting.api.casting.mishaps.MishapBadLocation
import at.petrak.hexcasting.api.misc.MediaConstants
import io.github.real_septicake.hexxyplanes.HexplaneExit
import io.github.real_septicake.hexxyplanes.setExit
import io.github.real_septicake.hexxyplanes.toVecI
import net.minecraft.core.BlockPos
import net.minecraft.server.level.ServerPlayer

object OpPlaneExit : ConstMediaAction {
    override val argc = 1
    override val mediaCost = MediaConstants.SHARD_UNIT * 3

    override fun execute(args: List<Iota>, env: CastingEnvironment): List<Iota> {
        val dest = args.getVec3(0, argc)
        env.assertVecInRange(dest)
        if(env.castingEntity !is ServerPlayer)
            throw MishapBadCaster()
        if(!setExit(env.castingEntity as ServerPlayer,
                HexplaneExit(
                    env.world.dimension(),
                    BlockPos(dest.toVecI())
                )
            ))
            throw MishapBadLocation(dest, "bad_dimension")
        return listOf()
    }
}