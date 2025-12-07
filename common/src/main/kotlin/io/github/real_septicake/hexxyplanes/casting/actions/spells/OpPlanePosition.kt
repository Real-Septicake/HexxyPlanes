package io.github.real_septicake.hexxyplanes.casting.actions.spells

import at.petrak.hexcasting.api.casting.castables.ConstMediaAction
import at.petrak.hexcasting.api.casting.eval.CastingEnvironment
import at.petrak.hexcasting.api.casting.iota.Iota
import at.petrak.hexcasting.api.casting.iota.Vec3Iota
import io.github.real_septicake.hexxyplanes.Hexxyplanes
import io.github.real_septicake.hexxyplanes.getPlane
import net.minecraft.world.phys.Vec3

object OpPlanePosition : ConstMediaAction {
    override val argc = 1

    override fun execute(args: List<Iota>, env: CastingEnvironment): List<Iota> {
        val plane = args.getPlane(0, argc)
        val chunkPos = Hexxyplanes.chunkFromUUID(plane.player.uuid)
        return listOf(Vec3Iota(Vec3((chunkPos.minBlockX + 1).toDouble(), 1.0, (chunkPos.minBlockZ + 1).toDouble())))
    }
}