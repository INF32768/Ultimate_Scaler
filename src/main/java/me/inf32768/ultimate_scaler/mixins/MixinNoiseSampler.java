package me.inf32768.ultimate_scaler.mixins;

import me.inf32768.ultimate_scaler.util.Util;
import net.minecraft.util.math.Direction;
import net.minecraft.world.gen.densityfunction.DensityFunction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import static me.inf32768.ultimate_scaler.option.UltimateScalerOptions.config;

@Mixin(targets = "net.minecraft.world.gen.densityfunction.DensityFunctionTypes$Noise")
public abstract class MixinNoiseSampler {
    @ModifyArgs(method = "sample", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/gen/densityfunction/DensityFunction$Noise;sample(DDD)D"))
    private void modifyNoiseSampleArgs(Args args, DensityFunction.NoisePos pos) {
        double x = (config.bigIntegerRewrite ? Util.getBigIntegerOffsetPos(pos.blockX(), Direction.Axis.X).doubleValue() : Util.getDoubleOffsetPos(pos.blockX(), Direction.Axis.X)) * this.getXzScale();
        double y = (config.bigIntegerRewrite ? Util.getBigIntegerOffsetPos(pos.blockY(), Direction.Axis.Y).doubleValue() : Util.getDoubleOffsetPos(pos.blockY(), Direction.Axis.Y)) * this.getYScale();
        double z = (config.bigIntegerRewrite ? Util.getBigIntegerOffsetPos(pos.blockZ(), Direction.Axis.Z).doubleValue() : Util.getDoubleOffsetPos(pos.blockZ(), Direction.Axis.Z)) * this.getXzScale();

        args.set(0, x);
        args.set(1, y);
        args.set(2, z);
    }

    @Accessor("xzScale")
    public abstract double getXzScale();
    @Accessor("yScale")
    public abstract double getYScale();
}
