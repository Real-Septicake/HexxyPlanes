package io.github.real_septicake.hexxyplanes.forge.capabilities;

import io.github.real_septicake.hexxyplanes.HexplaneExit;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import org.jetbrains.annotations.Nullable;

public class HexplaneExitCapability implements IHexplaneExit {
    @Nullable
    private HexplaneExit exit;

    @Override
    public @Nullable HexplaneExit getExit() {
        return exit;
    }

    @Override
    public void setExit(@Nullable HexplaneExit exit) {
        this.exit = exit;
    }

    @Override
    public CompoundTag serializeNBT() {
        if(exit == null)
            return new CompoundTag();
        return (CompoundTag) HexplaneExit.CODEC.encodeStart(NbtOps.INSTANCE, exit).result().orElse(new CompoundTag());
    }

    @Override
    public void deserializeNBT(CompoundTag arg) {
        this.exit = HexplaneExit.CODEC.parse(NbtOps.INSTANCE, arg).result().orElse(null);
    }
}
