package me.inf32768.ultimate_scaler.mixins;

import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.noise.OctavePerlinNoiseSampler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import static me.inf32768.ultimate_scaler.option.UltimateScalerOptions.config;

@Mixin(OctavePerlinNoiseSampler.class)
public abstract class MixinOctavePerlinNoiseSampler {
    /**
     * @author INF32768
     * @reason To modify the position of the farLands.
     */
    @Overwrite
    public static double maintainPrecision(double value) {
        double result =  switch (config.farLandsPos) {
            case BETA -> value;
            case RELEASE -> Math.abs(value) > Long.MAX_VALUE ? value - Math.signum(value) * Long.MAX_VALUE : (value + 1.6777216E7D) % 3.3554432E7D - 1.6777216E7D;
            case REMOVED -> (value + 1.6777216E7D) % 3.3554432E7D - 1.6777216E7D;
            case CUSTOM -> value - (double) MathHelper.lfloor(value / config.maintainPrecisionCustomDivisor + (double) 0.5F) * config.maintainPrecisionCustomDivisor;
            default ->
                    value - (double) MathHelper.lfloor(value / (double) 3.3554432E7F + (double) 0.5F) * (double) 3.3554432E7F;
        };
        if (config.limitReturnValue) {
            result = Math.log10(Math.abs(result)) > config.maxNoiseLogarithmValue ? Math.pow(10, Math.log10(Math.abs(result)) - Math.floor(Math.log10(Math.abs(result)) - config.maxNoiseLogarithmValue)) * Math.signum(result) : result;
        }
        return result;
    }
}
