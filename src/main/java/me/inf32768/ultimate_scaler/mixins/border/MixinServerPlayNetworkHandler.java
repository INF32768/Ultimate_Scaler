package me.inf32768.ultimate_scaler.mixins.border;

import net.minecraft.server.network.ServerPlayNetworkHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static me.inf32768.ultimate_scaler.option.UltimateScalerOptions.config;

@Mixin(ServerPlayNetworkHandler.class)
public abstract class MixinServerPlayNetworkHandler {
    @Inject(method = "clampHorizontal", at = @At("HEAD"), cancellable = true)
    private static void modifyClampHorizontal(double d, CallbackInfoReturnable<Double> cir) {
        if (config.expandWorldBorder) {
            cir.setReturnValue(d);
        }
    }

    @Inject(method = "clampVertical", at = @At("HEAD"), cancellable = true)
    private static void modifyClampVertical(double d, CallbackInfoReturnable<Double> cir) {
        if (config.expandWorldBorder) {
            cir.setReturnValue(d);
        }
    }
}
