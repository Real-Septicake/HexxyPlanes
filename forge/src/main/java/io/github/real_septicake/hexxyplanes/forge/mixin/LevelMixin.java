package io.github.real_septicake.hexxyplanes.forge.mixin;

import io.github.real_septicake.hexxyplanes.HexxyplanesDimension;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.border.WorldBorder;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Level.class)
public class LevelMixin {
    @Final
    @Shadow
    private ResourceKey<Level> dimension;

    @Inject(method = "getWorldBorder", at = @At(value = "HEAD"), cancellable = true)
    private void hexplaneWorldBorder(CallbackInfoReturnable<WorldBorder> cir) {
        if(HexxyplanesDimension.INSTANCE.getWORLD_KEY().location().equals(dimension.location())
            && HexxyplanesDimension.INSTANCE.getWORLD_KEY().registry().equals(dimension.registry())) {
            cir.setReturnValue(new WorldBorder());
        }
    }
}
