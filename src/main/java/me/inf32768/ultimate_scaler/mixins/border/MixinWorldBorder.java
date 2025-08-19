package me.inf32768.ultimate_scaler.mixins.border;

import net.minecraft.world.border.WorldBorder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static me.inf32768.ultimate_scaler.option.UltimateScalerOptions.config;

@Mixin(WorldBorder.class)
public abstract class MixinWorldBorder {
    @Shadow
    public abstract void setMaxRadius(int maxRadius);

    @Inject(method = "<init>", at = @At("RETURN"))
    private void onInit(CallbackInfo ci) {
        if (config.expandWorldBorder) {
            this.setMaxRadius(Integer.MAX_VALUE);
        }
    }
}