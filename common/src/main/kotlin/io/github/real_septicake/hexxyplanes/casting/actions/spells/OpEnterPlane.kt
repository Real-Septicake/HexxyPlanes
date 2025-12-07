package io.github.real_septicake.hexxyplanes.casting.actions.spells

import at.petrak.hexcasting.api.casting.RenderedSpell
import at.petrak.hexcasting.api.casting.castables.SpellAction
import at.petrak.hexcasting.api.casting.eval.CastingEnvironment
import at.petrak.hexcasting.api.casting.iota.Iota
import at.petrak.hexcasting.api.casting.mishaps.MishapBadCaster
import at.petrak.hexcasting.api.misc.MediaConstants
import io.github.real_septicake.hexxyplanes.HexxyplanesDimension
import io.github.real_septicake.hexxyplanes.getPlane
import net.minecraft.server.level.ServerPlayer
import java.util.*

object OpEnterPlane : SpellAction {
    override val argc = 1

    override fun execute(args: List<Iota>, env: CastingEnvironment): SpellAction.Result {
        val dest = args.getPlane(0, 1)
        val target = env.castingEntity
        if(target !is ServerPlayer)
            throw MishapBadCaster()

        return SpellAction.Result(
            Spell(dest.player.uuid),
            if(HexxyplanesDimension.WORLD_KEY == target.level().dimension()) MediaConstants.DUST_UNIT * 5 else MediaConstants.CRYSTAL_UNIT * 10,
            listOf()
        )
    }

    private data class Spell(val uuid: UUID) : RenderedSpell {
        override fun cast(env: CastingEnvironment) {
            val player = env.castingEntity as ServerPlayer
            HexxyplanesDimension.goToPlane(env.world.server.getLevel(HexxyplanesDimension.WORLD_KEY)!!, player, uuid)
        }
    }
}