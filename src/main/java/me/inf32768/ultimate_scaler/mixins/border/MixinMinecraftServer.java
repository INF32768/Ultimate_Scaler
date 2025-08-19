package me.inf32768.ultimate_scaler.mixins.border;

import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static me.inf32768.ultimate_scaler.option.UltimateScalerOptions.config;

@Mixin(MinecraftServer.class)
public abstract class MixinMinecraftServer {
    @Inject(method = "getMaxWorldBorderRadius", at = @At("HEAD"), cancellable = true)
    private void modifyMaxWorldBorderRadius(CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(config.expandWorldBorder ? Integer.MAX_VALUE : 29999984);
    }
}
