package io.github.real_septicake.hexxyplanes.commands

import com.mojang.brigadier.builder.LiteralArgumentBuilder
import io.github.real_septicake.hexxyplanes.Hexxyplanes
import net.minecraft.commands.CommandSourceStack
import net.minecraft.commands.Commands
import net.minecraft.commands.arguments.EntityArgument
import net.minecraft.network.chat.Component

object GetChunkCommand : HexxyplanesCommand() {
    override fun add(cmd: LiteralArgumentBuilder<CommandSourceStack>) {
        cmd.then(Commands.literal("coords")
            .then(Commands.argument("for", EntityArgument.player())
                .executes {
                    val p = EntityArgument.getPlayer(it, "for")
                    val chunk = Hexxyplanes.chunkFromUUID(p.uuid)
                    it.source.player?.sendSystemMessage(Component.literal("For ${p.name.string}: ${chunk.minBlockX} 0 ${chunk.minBlockZ}"))
                    1
                }
            )
        )
    }
}