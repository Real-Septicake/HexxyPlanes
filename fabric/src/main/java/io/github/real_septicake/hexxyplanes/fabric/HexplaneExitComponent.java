package io.github.real_septicake.hexxyplanes.fabric;

import dev.onyxstudios.cca.api.v3.component.Component;
import io.github.real_septicake.hexxyplanes.HexplaneExit;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import org.jetbrains.annotations.Nullable;

public class HexplaneExitComponent implements Component {
    @Nullable
    private HexplaneExit exit;

    @Nullable
    public HexplaneExit getExit() {
        return exit;
    }

    public void setExit(@Nullable HexplaneExit exit) {
        this.exit = exit;
    }

    @Override
    public void readFromNbt(CompoundTag tag) {
        this.exit = HexplaneExit.CODEC.parse(NbtOps.INSTANCE, tag.get("exit")).result().orElse(null);
    }

    @Override
    public void writeToNbt(CompoundTag tag) {
        if(exit == null)
            tag.put("exit", new CompoundTag());
        else
            tag.put("exit", HexplaneExit.CODEC.encodeStart(NbtOps.INSTANCE, exit).result().orElse(new CompoundTag()));
    }
}
