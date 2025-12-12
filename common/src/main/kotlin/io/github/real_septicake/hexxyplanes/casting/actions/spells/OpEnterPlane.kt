package io.github.real_septicake.hexxyplanes.casting.actions.spells

import at.petrak.hexcasting.api.casting.RenderedSpell
import at.petrak.hexcasting.api.casting.castables.SpellAction
import at.petrak.hexcasting.api.casting.eval.CastingEnvironment
import at.petrak.hexcasting.api.casting.getEntity
import at.petrak.hexcasting.api.casting.iota.Iota
import at.petrak.hexcasting.api.casting.mishaps.MishapBadCaster
import at.petrak.hexcasting.api.casting.mishaps.MishapImmuneEntity
import at.petrak.hexcasting.api.misc.MediaConstants
import at.petrak.hexcasting.api.mod.HexTags
import io.github.real_septicake.hexxyplanes.HexxyplanesDimension
import io.github.real_septicake.hexxyplanes.getPlane
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.entity.Entity
import java.util.*

object OpEnterPlane : SpellAction {
    override val argc = 2

    override fun execute(args: List<Iota>, env: CastingEnvironment): SpellAction.Result {
        val dest = args.getPlane(0, argc)
        val target = args.getEntity(1, argc)
        if(env.castingEntity !is ServerPlayer)
            throw MishapBadCaster()
        if(!target.canChangeDimensions() || target.type.`is`(HexTags.Entities.CANNOT_TELEPORT))
            throw MishapImmuneEntity(target)

        return SpellAction.Result(
            Spell(dest.player.uuid, target),
            if(HexxyplanesDimension.WORLD_KEY == target.level().dimension())
                MediaConstants.DUST_UNIT * 5
            else {
                MediaConstants.SHARD_UNIT * 10
            },
            listOf()
        )
    }

    private data class Spell(val uuid: UUID, val target: Entity) : RenderedSpell {
        override fun cast(env: CastingEnvironment) {
            HexxyplanesDimension.sendToPlane(env.world.server.getLevel(HexxyplanesDimension.WORLD_KEY)!!, target, uuid)
        }
    }
}