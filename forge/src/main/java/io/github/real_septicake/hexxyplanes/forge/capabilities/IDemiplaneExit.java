package io.github.real_septicake.hexxyplanes.forge.capabilities;

import io.github.real_septicake.hexxyplanes.DemiplaneExit;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;
import org.jetbrains.annotations.Nullable;

public interface IDemiplaneExit extends INBTSerializable<CompoundTag> {
    @Nullable
    DemiplaneExit getExit();
    void setExit(@Nullable DemiplaneExit exit);
}
