package io.github.real_septicake.hexxyplanes.registry

import io.github.real_septicake.hexxyplanes.blocks.BarrierBlock
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.Item
import net.minecraft.world.item.Item.Properties as ItemProperties
import net.minecraft.world.level.block.state.BlockBehaviour.Properties as BlockProperties
import net.minecraft.world.level.ItemLike
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.material.PushReaction

object HexxyplanesBlocks : HexxyplanesRegistrar<Block>(
    Registries.BLOCK,
    { BuiltInRegistries.BLOCK }
) {
    private val BARRIER_PROPS = BlockProperties.copy(Blocks.DEEPSLATE_TILES).strength(Block.INDESTRUCTIBLE)
        .pushReaction(PushReaction.BLOCK).explosionResistance(Blocks.BEDROCK.explosionResistance)
        .isValidSpawn { _, _, _, _ -> false }

    val BARRIER = blockItem("barrier", ItemProperties().stacksTo(64).fireResistant()) {
        BarrierBlock(BARRIER_PROPS)
    }

    val BARRIER_SPAWN = blockItem("barrier_spawn", ItemProperties().stacksTo(64).fireResistant()) {
        BarrierBlock(BARRIER_PROPS)
    }

    private fun <T : Block> blockItem(name: String, props: ItemProperties, builder: () -> T) =
        make(name, builder) { BlockItem(it, props) }

    private fun <B : Block, I : Item> make(name: String, blockBuilder: () -> B, itemBuilder: (B) -> I) : BlockItemEntry<B, I> {
        val blockEntry = register(name, blockBuilder)
        val itemEntry = HexxyplanesItems.register(name) { itemBuilder(blockEntry.value) }
        return BlockItemEntry(blockEntry, itemEntry)
    }

    class BlockItemEntry<B : Block, I : Item>(
        blockEntry: Entry<B>,
        val itemEntry: HexxyplanesRegistrar<Item>.Entry<I>
    ) : Entry<B>(blockEntry), ItemLike {
        val block by ::value
        val item by itemEntry::value
        val itemKey by itemEntry::key

        override fun asItem() = item
    }
}