package me.inf32768.ultimatescaler.mixins;

import me.inf32768.ultimatescaler.util.Util;
import net.minecraft.util.math.Direction;
import net.minecraft.world.gen.densityfunction.DensityFunction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import static me.inf32768.ultimatescaler.option.UltimateScalerOptions.config;

@Mixin(targets = "net.minecraft.world.gen.densityfunction.DensityFunctionTypes$ShiftedNoise")
public abstract class MixinShiftedNoiseSampler {
    @ModifyArgs(method = "sample", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/gen/densityfunction/DensityFunction$Noise;sample(DDD)D"))
    private void modifyNoiseSampleArgs(Args args, DensityFunction.NoisePos pos) {
        double d = (config.bigIntegerRewrite ? Util.getBigIntegerOffsetPos(pos.blockX(), Direction.Axis.X).doubleValue() : Util.getDoubleOffsetPos(pos.blockX(), Direction.Axis.X)) * this.getXzScale() + this.getShiftX().sample(pos);
        double e = (config.bigIntegerRewrite ? Util.getBigIntegerOffsetPos(pos.blockY(), Direction.Axis.Y).doubleValue() : Util.getDoubleOffsetPos(pos.blockY(), Direction.Axis.Y)) * this.getYScale() + this.getShiftY().sample(pos);
        double f = (config.bigIntegerRewrite ? Util.getBigIntegerOffsetPos(pos.blockZ(), Direction.Axis.Z).doubleValue() : Util.getDoubleOffsetPos(pos.blockZ(), Direction.Axis.Z)) * this.getXzScale() + this.getShiftZ().sample(pos);

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
