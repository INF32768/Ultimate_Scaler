package me.inf32768.ultimatescaler.mixins;

import net.minecraft.util.math.noise.InterpolatedNoiseSampler;
import net.minecraft.world.gen.densityfunction.DensityFunction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import static me.inf32768.ultimatescaler.UltimateScaler.config;

@Mixin(InterpolatedNoiseSampler.class)
public abstract class MixinInterpolatedNoiseSampler {
    @ModifyVariable(method = "sample", at = @At("STORE"), ordinal = 0)
    private double modifyBlockX(double x, DensityFunction.NoisePos pos) {
        // 修改 x 坐标
        return ((double) pos.blockX() * config.globalXScale + config.globalXOffset) * getScaledXzScale();
    }

    @ModifyVariable(method = "sample", at = @At("STORE"), ordinal = 1)
    private double modifyBlockY(double y, DensityFunction.NoisePos pos) {
        // 修改 y 坐标
        return ((double) pos.blockY() * config.globalYScale + config.globalYOffset) * getScaledYScale();
    }

    @ModifyVariable(method = "sample", at = @At("STORE"), ordinal = 2)
    private double modifyBlockZ(double z, DensityFunction.NoisePos pos) {
        // 修改 z 坐标
        return ((double) pos.blockZ() * config.globalZScale + config.globalZOffset) * getScaledXzScale();
    }

    @Accessor("scaledXzScale")
    abstract double getScaledXzScale();

    @Accessor("scaledYScale")
    abstract double getScaledYScale();
}
