package me.inf32768.ultimate_scaler.mixins;

import me.inf32768.ultimate_scaler.util.Util;
import net.minecraft.util.math.Direction;
import net.minecraft.world.gen.densityfunction.DensityFunction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import static me.inf32768.ultimate_scaler.option.UltimateScalerOptions.config;

@Mixin(targets = "net.minecraft.world.gen.densityfunction.DensityFunctionTypes$YClampedGradient")
public abstract class MixinYClampedGradient {
    @ModifyArgs(method = "sample", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/MathHelper;clampedMap(DDDDD)D"))
    private void modifyArgs(Args args, DensityFunction.NoisePos pos) {
        if (config.extraYOffset) {
            double y = config.bigIntegerRewrite ? Util.getBigIntegerOffsetPos(pos.blockY(), Direction.Axis.Y).doubleValue() : Util.getDoubleOffsetPos(pos.blockY(), Direction.Axis.Y);
            args.set(0, y);
        }
    }
}
