package me.inf32768.ultimate_scaler.mixins.border;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockRenderView;
import net.minecraft.world.WorldView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static me.inf32768.ultimate_scaler.option.UltimateScalerOptions.config;

@Mixin(WorldView.class)
public interface IMixinWorldView {
    @Inject(method = "getLightLevel(Lnet/minecraft/util/math/BlockPos;I)I", at = @At("HEAD"), cancellable = true)
    private void modifyLightLevel(BlockPos pos, int ambientDarkness, CallbackInfoReturnable<Integer> cir) {
        if (config.expandWorldBorder) {
            cir.setReturnValue(((BlockRenderView) this).getBaseLightLevel(pos, ambientDarkness));
        }
    }
}
