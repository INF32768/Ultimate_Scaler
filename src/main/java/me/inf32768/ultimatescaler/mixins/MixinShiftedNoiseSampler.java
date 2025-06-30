package me.inf32768.ultimatescaler.mixins;

import net.minecraft.world.gen.densityfunction.DensityFunction;
import net.minecraft.world.gen.densityfunction.DensityFunctionTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import me.inf32768.ultimatescaler.option.UltimateScalerOptions;

@Mixin(DensityFunctionTypes.ShiftedNoise.class)
public abstract class MixinShiftedNoiseSampler {
    @ModifyArgs(method = "sample", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/gen/densityfunction/DensityFunction$Noise;sample(DDD)D"))
    private void modifyNoiseSampleArgs(Args args, DensityFunction.NoisePos pos) {

        double d = (pos.blockX() * UltimateScalerOptions.globalScale[0] + UltimateScalerOptions.globalIntegerOffset[0].doubleValue()) * this.getXzScale() + this.getShiftX().sample(pos);
        double e = (pos.blockY() * UltimateScalerOptions.globalScale[1] + UltimateScalerOptions.globalIntegerOffset[1].doubleValue()) * this.getYScale() + this.getShiftY().sample(pos);
        double f = (pos.blockZ() * UltimateScalerOptions.globalScale[2] + UltimateScalerOptions.globalIntegerOffset[2].doubleValue()) * this.getXzScale() + this.getShiftZ().sample(pos);

        args.set(0, d);
        args.set(1, e);
        args.set(2, f);
    }

    @Accessor("xzScale")
    public abstract double getXzScale();
    @Accessor("yScale")
    public abstract double getYScale();
    @Accessor
    public abstract DensityFunction getShiftX();
    @Accessor
    public abstract DensityFunction getShiftY();
    @Accessor
    public abstract DensityFunction getShiftZ();
}
