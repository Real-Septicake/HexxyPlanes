package io.github.real_septicake.hexxyplanes.blocks

import io.github.real_septicake.hexxyplanes.HexxyplanesDimension
import net.minecraft.core.BlockPos
import net.minecraft.server.level.ServerLevel
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.Level
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState

class BarrierBlock(props: Properties) : Block(props) {
    override fun attack(state: BlockState, level: Level, pos: BlockPos, player: Player) {
        if(!level.isClientSide){ HexxyplanesDimension.exitPlane(level as ServerLevel, player as ServerPlayer) }
    }

    override fun destroy(level: LevelAccessor, pos: BlockPos, state: BlockState) {
        level.setBlock(pos, state, 3)
    }
}