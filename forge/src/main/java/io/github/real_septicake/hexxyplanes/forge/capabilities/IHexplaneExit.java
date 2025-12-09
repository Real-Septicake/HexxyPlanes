package io.github.real_septicake.hexxyplanes.forge.capabilities;

import io.github.real_septicake.hexxyplanes.HexplaneExit;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;
import org.jetbrains.annotations.Nullable;

public interface IHexplaneExit extends INBTSerializable<CompoundTag> {
    @Nullable
    HexplaneExit getExit();
    void setExit(@Nullable HexplaneExit exit);
}
