package io.github.real_septicake.hexxyplanes.forge.capabilities;

import io.github.real_septicake.hexxyplanes.DemiplaneExit;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import org.jetbrains.annotations.Nullable;

public class DemiplaneExitCapability implements IDemiplaneExit {
    @Nullable
    private DemiplaneExit exit;

    @Override
    public @Nullable DemiplaneExit getExit() {
        return exit;
    }

    @Override
    public void setExit(@Nullable DemiplaneExit exit) {
        this.exit = exit;
    }

    @Override
    public CompoundTag serializeNBT() {
        if(exit == null)
            return new CompoundTag();
        return (CompoundTag) DemiplaneExit.CODEC.encodeStart(NbtOps.INSTANCE, exit).result().orElse(new CompoundTag());
    }

    @Override
    public void deserializeNBT(CompoundTag arg) {
        this.exit = DemiplaneExit.CODEC.parse(NbtOps.INSTANCE, arg).result().orElse(null);
    }
}
