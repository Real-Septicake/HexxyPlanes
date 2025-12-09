package io.github.real_septicake.hexxyplanes;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;

public class HexplaneExit {
    public final ResourceKey<Level> dimension;
    public final BlockPos position;
    public HexplaneExit(ResourceKey<Level> dimension, BlockPos position) {
        this.dimension = dimension;
        this.position = position;
    }

    public static final Codec<HexplaneExit> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
            Level.RESOURCE_KEY_CODEC.fieldOf("dimension").forGetter((exit) -> exit.dimension),
            BlockPos.CODEC.fieldOf("position").forGetter((exit) -> exit.position)
    ).apply(instance, instance.stable(HexplaneExit::new)));
}
