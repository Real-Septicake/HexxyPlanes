package io.github.real_septicake.hexxyplanes.fabric.interop.oneironaut.casting.actions

import at.petrak.hexcasting.api.casting.castables.ConstMediaAction
import at.petrak.hexcasting.api.casting.eval.CastingEnvironment
import at.petrak.hexcasting.api.casting.iota.Iota
import at.petrak.hexcasting.api.casting.iota.Vec3Iota
import at.petrak.hexcasting.api.casting.mishaps.MishapBadCaster
import io.github.real_septicake.hexxyplanes.HexplaneExit
import io.github.real_septicake.hexxyplanes.fabric.getExit
import net.beholderface.oneironaut.casting.iotatypes.DimIota
import net.minecraft.server.level.ServerPlayer

object OpExitBreakdown : ConstMediaAction {
    override val argc = 0

    override fun execute(args: List<Iota>, env: CastingEnvironment): List<Iota> {
        if(env.castingEntity !is ServerPlayer)
            throw MishapBadCaster()
        val player = env.castingEntity as ServerPlayer
        val exit = getExit(env.castingEntity as ServerPlayer) ?: HexplaneExit(
            player.respawnDimension, player.respawnPosition ?: env.world.server.overworld().sharedSpawnPos)
        return listOf(DimIota(exit.dimension), Vec3Iota(exit.position.center.subtract(0.0, 0.5, 0.0)))
    }
}