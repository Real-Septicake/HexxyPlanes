package io.github.real_septicake.hexxyplanes

import at.petrak.hexcasting.api.casting.eval.CastingEnvironment
import at.petrak.hexcasting.api.casting.eval.CastingEnvironmentComponent
import io.github.real_septicake.hexxyplanes.Hexxyplanes.chunkFromUUID
import io.github.real_septicake.hexxyplanes.HexxyplanesDimension.WORLD_KEY
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.phys.Vec3

class DemiplaneComponent(val env: CastingEnvironment) : CastingEnvironmentComponent.IsVecInRange {
    override fun getKey() = KEY()
    class KEY : CastingEnvironmentComponent.Key<CastingEnvironmentComponent.IsVecInRange>

    override fun onIsVecInRange(vec: Vec3?, current: Boolean): Boolean {
        if (env.world.dimension() === WORLD_KEY) {
            if (env.castingEntity is ServerPlayer) {
                val chunkPos = chunkFromUUID((env.castingEntity as ServerPlayer).uuid)
                val inside =
                    vec!!.x >= chunkPos.minBlockX + 1 && vec.x <= chunkPos.minBlockX + 11 &&
                            vec.z >= chunkPos.minBlockZ + 1 && vec.z <= chunkPos.minBlockZ + 11 &&
                            vec.y >= 1 && vec.y <= 11
                return inside
            } else {
                return false
            }
        }
        return current
    }
}