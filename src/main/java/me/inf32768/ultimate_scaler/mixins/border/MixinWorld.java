package me.inf32768.ultimate_scaler.mixins.border;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static me.inf32768.ultimate_scaler.option.UltimateScalerOptions.config;

@Mixin(World.class)
public abstract class MixinWorld {
    @Inject(method = "isValid", at = @At("HEAD"), cancellable = true)
    private static void modifyIsValid(BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        if (config.expandWorldBorder) {
            cir.setReturnValue(true);
        }
    }

    @Inject(method = "isValidHorizontally", at = @At("HEAD"), cancellable = true)
    private static void modifyIsValidHorizontally(CallbackInfoReturnable<Boolean> cir) {
        if (config.expandWorldBorder) {
            cir.setReturnValue(true);
        }
    }

    @ModifyConstant(method = "getTopY", constant = @Constant(intValue = -30000000))
    private int modifyMinCoordinate(int original) {
        return config.expandWorldBorder ? Integer.MIN_VALUE : original;
    }

    @ModifyConstant(method = "getTopY", constant = @Constant(intValue = 30000000))
    private int modifyMaxCoordinate(int original) {
        return config.expandWorldBorder ? Integer.MAX_VALUE : original;
    }
}
