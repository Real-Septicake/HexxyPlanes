package io.github.real_septicake.hexxyplanes.casting.iota

import at.petrak.hexcasting.api.casting.iota.Iota
import at.petrak.hexcasting.api.casting.iota.IotaType
import at.petrak.hexcasting.api.utils.asCompound
import net.minecraft.ChatFormatting
import net.minecraft.nbt.CompoundTag
import net.minecraft.nbt.Tag
import net.minecraft.network.chat.Component
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.entity.player.Player

class PlaneIota(val player: Player) : Iota(TYPE, player) {
    override fun isTruthy() = true

    override fun toleratesOther(that: Iota?): Boolean {
        return typesMatch(this, that)
                && that is PlaneIota
                && this.player.uuid === that.player.uuid
    }

    override fun size() = 2
    override fun depth() = 2

    override fun serialize(): Tag {
        val c = CompoundTag()
        c.putUUID("uuid", player.uuid)
        c.putString("name", player.name.string)
        return c
    }

    companion object {
        val TYPE: IotaType<PlaneIota> = object : IotaType<PlaneIota>() {
            override fun deserialize(tag: Tag, world: ServerLevel): PlaneIota? {
                val c = tag.asCompound
                val uuid = c.getUUID("uuid")
                val player = world.getPlayerByUUID(uuid) ?: return null
                return PlaneIota(player)
            }

            override fun display(tag: Tag): Component {
                val c = tag.asCompound
                val comp = Component.translatable("hexxyplanes.tooltip.hexplane")
                    .append(" (")
                    .withStyle(ChatFormatting.DARK_GREEN)
                comp.append(
                    Component.literal(c.getString("name"))
                    .withStyle(ChatFormatting.AQUA)
                )
                comp.append(")")
                    .withStyle(ChatFormatting.DARK_GREEN)
                return comp
            }

            override fun color(): Int {
                return 0x52dd7c
            }
        }
    }
}