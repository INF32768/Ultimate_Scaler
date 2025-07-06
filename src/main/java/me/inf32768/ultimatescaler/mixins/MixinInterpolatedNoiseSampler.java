package me.inf32768.ultimatescaler.mixins;

import me.inf32768.ultimatescaler.util.Util;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.noise.InterpolatedNoiseSampler;
import net.minecraft.world.gen.densityfunction.DensityFunction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import static me.inf32768.ultimatescaler.option.UltimateScalerOptions.config;

@Mixin(InterpolatedNoiseSampler.class)
public abstract class MixinInterpolatedNoiseSampler {
    //扩展取值范围
    @ModifyConstant(method = "<clinit>", constant = @Constant(doubleValue = 0.001))
    private static double modifyMinScaleAndFactorRange(double original) {
        return config.expandDatapackValueRange ? Double.NEGATIVE_INFINITY : original;
    }

    @ModifyConstant(method = "<clinit>", constant = @Constant(doubleValue = 1000.0))
    private static double modifyMaxScaleAndFactorRange(double original) {
        return config.expandDatapackValueRange ? Double.POSITIVE_INFINITY : original;
    }

    @ModifyConstant(method = "method_42385", constant = @Constant(doubleValue = 1.0))
    private static double modifyMinSmearScaleMultiplierRange(double original) {
        return config.expandDatapackValueRange ? Double.NEGATIVE_INFINITY : original;
    }

    @ModifyConstant(method = "method_42385", constant = @Constant(doubleValue = 8.0))
    private static double modifyMaxSmearScaleMultiplierRange(double original) {
        return config.expandDatapackValueRange ? Double.POSITIVE_INFINITY : original;
    }

    //偏移地形
    @ModifyVariable(method = "sample", at = @At("STORE"), ordinal = 0)
    private double modifyBlockX(double x, DensityFunction.NoisePos pos) {
        // 修改 x 坐标
        return config.bigIntegerRewrite ? Util.getBigIntegerOffsetPos(pos.blockX(), Direction.Axis.X).doubleValue() * getScaledXzScale() : Util.getDoubleOffsetPos(pos.blockX(), Direction.Axis.X) * getScaledXzScale();
    }

    @ModifyVariable(method = "sample", at = @At("STORE"), ordinal = 1)
    private double modifyBlockY(double y, DensityFunction.NoisePos pos) {
        // 修改 y 坐标
        return config.bigIntegerRewrite ? Util.getBigIntegerOffsetPos(pos.blockY(), Direction.Axis.Y).doubleValue() * getScaledYScale() : Util.getDoubleOffsetPos(pos.blockY(), Direction.Axis.Y) * getScaledYScale();
    }

    @ModifyVariable(method = "sample", at = @At("STORE"), ordinal = 2)
    private double modifyBlockZ(double z, DensityFunction.NoisePos pos) {
        // 修改 z 坐标
        return config.bigIntegerRewrite ? Util.getBigIntegerOffsetPos(pos.blockZ(), Direction.Axis.Z).doubleValue() * getScaledXzScale() : Util.getDoubleOffsetPos(pos.blockZ(), Direction.Axis.Z) * getScaledXzScale();
    }

    @Accessor("scaledXzScale")
    abstract double getScaledXzScale();

    @Accessor("scaledYScale")
    abstract double getScaledYScale();
}
