package me.inf32768.ultimatescaler.mixins;

import net.minecraft.world.gen.densityfunction.DensityFunction;
import net.minecraft.world.gen.densityfunction.DensityFunctionTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import me.inf32768.ultimatescaler.option.UltimateScalerOptions;

@Mixin(DensityFunctionTypes.Noise.class)
public abstract class MixinNoiseSampler {
    @ModifyArgs(method = "sample", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/gen/densityfunction/DensityFunction$Noise;sample(DDD)D"))
    private void modifyNoiseSampleArgs(Args args, DensityFunction.NoisePos pos) {

        double x = (pos.blockX() * UltimateScalerOptions.globalScale[0] + UltimateScalerOptions.globalOffset[0]) * this.getXzScale();
        double y = (pos.blockY() * UltimateScalerOptions.globalScale[1] + UltimateScalerOptions.globalOffset[1]) * this.getYScale();
        double z = (pos.blockZ() * UltimateScalerOptions.globalScale[2] + UltimateScalerOptions.globalOffset[2]) * this.getXzScale();

        args.set(0, x);
        args.set(1, y);
        args.set(2, z);
    }

    @Accessor("xzScale")
    public abstract double getXzScale();
    @Accessor("yScale")
    public abstract double getYScale();
}
