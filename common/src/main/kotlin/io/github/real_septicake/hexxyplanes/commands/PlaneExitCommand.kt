package io.github.real_septicake.hexxyplanes.commands

import com.mojang.brigadier.builder.LiteralArgumentBuilder
import io.github.real_septicake.hexxyplanes.HexplaneExit
import io.github.real_septicake.hexxyplanes.setExit
import net.minecraft.ChatFormatting
import net.minecraft.commands.CommandSourceStack
import net.minecraft.commands.Commands
import net.minecraft.commands.arguments.DimensionArgument
import net.minecraft.commands.arguments.coordinates.BlockPosArgument
import net.minecraft.network.chat.Component

object PlaneExitCommand : HexxyplanesCommand() {
    override fun add(cmd: LiteralArgumentBuilder<CommandSourceStack>) {
        cmd.then(Commands.literal("planeExit")
            .then(Commands.argument("in", DimensionArgument.dimension())
                .then(Commands.argument("at", BlockPosArgument.blockPos())
                    .executes {
                        val dim = DimensionArgument.getDimension(it, "in")
                        val at = BlockPosArgument.getBlockPos(it, "at")
                        return@executes if(setExit(it.source.playerOrException,
                                HexplaneExit(
                                    dim.dimension(),
                                    at
                                )
                            )) {
                            it.source.sendSystemMessage(Component.literal("Successfully set exit"))
                            1
                        } else {
                            it.source.sendSystemMessage(
                                Component.literal("Could not set the exit").withStyle(ChatFormatting.RED)
                            )
                            0
                        }
                    }
                )
            )
        )
    }
}