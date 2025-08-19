package me.inf32768.ultimate_scaler.mixins.offset;

import me.inf32768.ultimate_scaler.util.Util;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.noise.SimplexNoiseSampler;
import net.minecraft.world.gen.densityfunction.DensityFunction;
import net.minecraft.world.gen.densityfunction.DensityFunctionTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import java.math.BigInteger;

import static me.inf32768.ultimate_scaler.option.UltimateScalerOptions.config;
@Mixin(DensityFunctionTypes.EndIslands.class)
public abstract class MixinEndIslands {
    @ModifyArgs(method = "sample(Lnet/minecraft/world/gen/densityfunction/DensityFunction$NoisePos;)D", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/gen/densityfunction/DensityFunctionTypes$EndIslands;sample(Lnet/minecraft/util/math/noise/SimplexNoiseSampler;II)F"))
    private void modifyNoisePos(Args args, DensityFunction.NoisePos pos) {
        if (config.bigIntegerRewrite) {
            args.set(1, pos.blockX());
            args.set(2, pos.blockZ());
        }
    }

    @ModifyVariable(method = "sample(Lnet/minecraft/util/math/noise/SimplexNoiseSampler;II)F", at = @At("STORE"), ordinal = 2)
    private static int modifySampleX(int original, SimplexNoiseSampler sampler, int x, int z) {
        return config.bigIntegerRewrite ? Util.getBigIntegerOffsetPos(x, Direction.Axis.X).divide(BigInteger.valueOf(16)).intValue() : original;
    }
    @ModifyVariable(method = "sample(Lnet/minecraft/util/math/noise/SimplexNoiseSampler;II)F", at = @At("STORE"), ordinal = 3)
    private static int modifySampleZ(int original, SimplexNoiseSampler sampler, int x, int z) {
        return config.bigIntegerRewrite ? Util.getBigIntegerOffsetPos(z, Direction.Axis.Z).divide(BigInteger.valueOf(16)).intValue() : original;
    }
    @ModifyVariable(method = "sample(Lnet/minecraft/util/math/noise/SimplexNoiseSampler;II)F", at = @At("STORE"), ordinal = 4)
    private static int modifySampleX1(int original, SimplexNoiseSampler sampler, int x, int z) {
        return config.bigIntegerRewrite ? Util.getBigIntegerOffsetPos(x, Direction.Axis.X).divide(BigInteger.valueOf(8)).remainder(BigInteger.TWO).intValue() : original;
    }
    @ModifyVariable(method = "sample(Lnet/minecraft/util/math/noise/SimplexNoiseSampler;II)F", at = @At("STORE"), ordinal = 5)
    private static int modifySampleZ2(int original, SimplexNoiseSampler sampler, int x, int z) {
        return config.bigIntegerRewrite ? Util.getBigIntegerOffsetPos(z, Direction.Axis.Z).divide(BigInteger.valueOf(8)).remainder(BigInteger.TWO).intValue() : original;
    }
    @ModifyArgs(method = "sample(Lnet/minecraft/util/math/noise/SimplexNoiseSampler;II)F", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/MathHelper;sqrt(F)F", ordinal = 0))
    private static void modifySqrt(Args args, SimplexNoiseSampler sampler, int x, int z) {
        if (config.fixEndRings) {
            if (config.bigIntegerRewrite) {
                BigInteger offsetX = Util.getBigIntegerOffsetPos(x, Direction.Axis.X).divide(BigInteger.valueOf(8));
                BigInteger offsetZ = Util.getBigIntegerOffsetPos(z, Direction.Axis.Z).divide(BigInteger.valueOf(8));
                args.set(0, offsetX.multiply(offsetX).add(offsetZ.multiply(offsetZ)).floatValue());
            } else {
                double xDouble = Util.getDoubleOffsetPos(x, Direction.Axis.X);
                double zDouble = Util.getDoubleOffsetPos(z, Direction.Axis.Z);
                args.set(0, (float) (xDouble * xDouble + zDouble * zDouble));
            }
        } else if (config.bigIntegerRewrite) {
            int offsetX = Util.getBigIntegerOffsetPos(x, Direction.Axis.X).divide(BigInteger.valueOf(8)).intValue();
            int offsetZ = Util.getBigIntegerOffsetPos(z, Direction.Axis.Z).divide(BigInteger.valueOf(8)).intValue();
            args.set(0, (float) (offsetX * offsetX + offsetZ * offsetZ));
        }
    }
}