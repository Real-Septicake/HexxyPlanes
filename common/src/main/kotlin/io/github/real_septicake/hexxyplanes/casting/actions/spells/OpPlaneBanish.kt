package io.github.real_septicake.hexxyplanes.casting.actions.spells

import at.petrak.hexcasting.api.casting.RenderedSpell
import at.petrak.hexcasting.api.casting.castables.SpellAction
import at.petrak.hexcasting.api.casting.eval.CastingEnvironment
import at.petrak.hexcasting.api.casting.getEntity
import at.petrak.hexcasting.api.casting.iota.Iota
import at.petrak.hexcasting.api.casting.mishaps.MishapBadCaster
import at.petrak.hexcasting.api.misc.MediaConstants
import io.github.real_septicake.hexxyplanes.HexxyplanesDimension
import io.github.real_septicake.hexxyplanes.casting.mishaps.MishapNotInHexplane
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.entity.Entity

object OpPlaneBanish : SpellAction {
    override val argc = 1

    override fun execute(args: List<Iota>, env: CastingEnvironment): SpellAction.Result {
        if(env.castingEntity !is ServerPlayer)
            throw MishapBadCaster()
        if(HexxyplanesDimension.WORLD_KEY != env.world.dimension())
            throw MishapNotInHexplane()
        val entity = args.getEntity(0, argc)
        env.assertEntityInRange(entity)

        return SpellAction.Result(
            Spell(entity, env.castingEntity as ServerPlayer),
            if(entity.`is`(env.castingEntity!!)) 0 else MediaConstants.SHARD_UNIT * 5,
            listOf()
        )
    }

    private data class Spell(val target: Entity, val by: ServerPlayer) : RenderedSpell {
        override fun cast(env: CastingEnvironment) {
            HexxyplanesDimension.banishFromPlane(env.world, by, target)
        }
    }
}