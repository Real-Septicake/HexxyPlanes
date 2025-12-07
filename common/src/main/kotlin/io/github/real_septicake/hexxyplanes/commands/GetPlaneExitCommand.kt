package io.github.real_septicake.hexxyplanes.commands

import com.mojang.brigadier.builder.LiteralArgumentBuilder
import io.github.real_septicake.hexxyplanes.getExit
import net.minecraft.commands.CommandSourceStack
import net.minecraft.commands.Commands
import net.minecraft.network.chat.Component

object GetPlaneExitCommand : HexxyplanesCommand() {
    override fun add(cmd: LiteralArgumentBuilder<CommandSourceStack>) {
        cmd.then(Commands.literal("getPlaneExit")
            .executes {
                val e = getExit(it.source.playerOrException)
                it.source.sendSystemMessage(Component.literal(
                    if (e !== null) "dim: ${e.dimension.location()}, pos: ${e.position.toShortString()}" else "spawn"
                ))
                1
            }
        )
    }
}