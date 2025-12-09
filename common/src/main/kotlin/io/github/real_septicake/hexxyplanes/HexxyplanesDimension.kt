package io.github.real_septicake.hexxyplanes

import io.github.real_septicake.hexxyplanes.registry.HexxyplanesBlocks
import net.minecraft.ChatFormatting
import net.minecraft.core.BlockPos
import net.minecraft.core.registries.Registries
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceKey
import net.minecraft.server.level.ServerLevel
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.entity.Entity
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Blocks
import java.util.*

object HexxyplanesDimension {

    val WORLD_KEY = ResourceKey.create(
        Registries.DIMENSION,
        Hexxyplanes.id("hexplane")
    )

    const val PLANE_SIZE = 16

    fun repairPlane(world: Level, uuid: UUID) {
        val chunkPos = Hexxyplanes.chunkFromUUID(uuid)
        for(x in chunkPos.minBlockX..<(chunkPos.minBlockX + PLANE_SIZE)) {
            for(y in 0..<PLANE_SIZE) {
                for(z in chunkPos.minBlockZ..<(chunkPos.minBlockZ + PLANE_SIZE)) {
                    val block = BlockPos(x, y, z)
                    if(x == chunkPos.minBlockX || x == chunkPos.minBlockX + PLANE_SIZE - 1 ||
                        y == 0 || y == PLANE_SIZE - 1 ||
                        z == chunkPos.minBlockZ || z == chunkPos.minBlockZ + PLANE_SIZE - 1) {
                        world.setBlock(block, HexxyplanesBlocks.BARRIER.block.defaultBlockState(), 3)
                    } else {
                        val bs = world.getBlockState(block)
                        if(bs.`is`(HexxyplanesBlocks.BARRIER.block) || bs.`is`(HexxyplanesBlocks.BARRIER_SPAWN.block))
                            world.setBlock(block, Blocks.AIR.defaultBlockState(), 3)
                    }
                }
            }
        }
        world.setBlock(BlockPos(chunkPos.minBlockX + 1, 0, chunkPos.minBlockZ + 1), HexxyplanesBlocks.BARRIER_SPAWN.block.defaultBlockState(), 3)
        world.destroyBlock(BlockPos(chunkPos.minBlockX + 1, 1, chunkPos.minBlockZ + 1), true)
        world.destroyBlock(BlockPos(chunkPos.minBlockX + 1, 2, chunkPos.minBlockZ + 1), true)
    }

    fun sendToPlane(world: ServerLevel, entity: Entity, uuid: UUID) {
        repairPlane(world, uuid)
        val chunkPos = Hexxyplanes.chunkFromUUID(uuid)
        entity.teleportTo(world, (chunkPos.minBlockX + 1.5), 1.0, (chunkPos.minBlockZ + 1.5), setOf(), -45f, 0f)
    }

    fun banishFromPlane(world: ServerLevel, by: ServerPlayer, entity: Entity) {
        if(entity is ServerPlayer)
            exitPlane(world, entity)
        else {
            val exit = getExit(by) ?: HexplaneExit(
                by.respawnDimension, by.respawnPosition ?: world.sharedSpawnPos
            )
            entity.teleportTo(
                world.server.getLevel(exit.dimension)!!,
                exit.position.x.toDouble(), exit.position.y.toDouble(), exit.position.z.toDouble(),
                setOf(), 0f, 0f
            )
        }
    }

    fun exitPlane(world: ServerLevel, player: ServerPlayer) {
        val exit = getExit(player)
        if(exit !== null) {
            val dim = world.server.getLevel(exit.dimension)!!
            val pos = exit.position
            player.teleportTo(
                dim, pos.x.toDouble(), pos.y.toDouble(), pos.z.toDouble(), 0f, 0f
            )
            return
        }
        var dest = player.respawnPosition ?: world.sharedSpawnPos
        var dim = player.respawnDimension
        if(dim == WORLD_KEY) {
            dim = Level.OVERWORLD
            dest = world.sharedSpawnPos
            player.sendSystemMessage(
                Component.translatable("hexxyplanes.error.bad_respawn")
                .withStyle(ChatFormatting.BOLD))
        }
        player.teleportTo(world.server.getLevel(dim)!!,
            dest.x.toDouble(), dest.y.toDouble(), dest.z.toDouble(), 0f, 0f)
    }
}