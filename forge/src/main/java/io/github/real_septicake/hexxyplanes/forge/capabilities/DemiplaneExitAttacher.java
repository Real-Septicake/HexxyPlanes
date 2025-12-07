package io.github.real_septicake.hexxyplanes.forge.capabilities;

import io.github.real_septicake.hexxyplanes.Hexxyplanes;
import io.github.real_septicake.hexxyplanes.forge.ForgeHexxyplanes;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DemiplaneExitAttacher {
    public static final ResourceLocation IDENTIFIER = Hexxyplanes.id("exit");
    public static class DemiplaneExitProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
        private final IDemiplaneExit backing = new DemiplaneExitCapability();
        private final LazyOptional<IDemiplaneExit> optional = LazyOptional.of(() -> backing);

        @Override
        public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> capability, @Nullable Direction arg) {
            return ForgeHexxyplanes.Companion.getDEMIPLANE_EXIT_CAPABILITY().orEmpty(capability, this.optional);
        }

        @Override
        public CompoundTag serializeNBT() {
            return backing.serializeNBT();
        }

        @Override
        public void deserializeNBT(CompoundTag arg) {
            backing.deserializeNBT(arg);
        }
    }
}
