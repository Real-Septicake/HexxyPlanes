package io.github.real_septicake.hexxyplanes.fabric;

import dev.onyxstudios.cca.api.v3.component.Component;
import io.github.real_septicake.hexxyplanes.DemiplaneExit;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import org.jetbrains.annotations.Nullable;

public class DemiplaneExitComponent implements Component {
    @Nullable
    private DemiplaneExit exit;

    @Nullable
    public DemiplaneExit getExit() {
        return exit;
    }

    public void setExit(@Nullable DemiplaneExit exit) {
        this.exit = exit;
    }

    @Override
    public void readFromNbt(CompoundTag tag) {
        this.exit = DemiplaneExit.CODEC.parse(NbtOps.INSTANCE, tag.get("exit")).result().orElse(null);
    }

    @Override
    public void writeToNbt(CompoundTag tag) {
        if(exit == null)
            tag.put("exit", new CompoundTag());
        else
            tag.put("exit", DemiplaneExit.CODEC.encodeStart(NbtOps.INSTANCE, exit).result().orElse(new CompoundTag()));
    }
}
