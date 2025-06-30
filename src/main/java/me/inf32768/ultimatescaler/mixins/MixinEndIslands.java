package me.inf32768.ultimatescaler.mixins;

import me.inf32768.ultimatescaler.option.UltimateScalerOptions;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.noise.SimplexNoiseSampler;
import net.minecraft.world.gen.densityfunction.DensityFunctionTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.math.BigInteger;

import static me.inf32768.ultimatescaler.option.UltimateScalerOptions.config;

@Mixin(DensityFunctionTypes.EndIslands.class)
public abstract class MixinEndIslands {
    /**
     * @author INF32768
     * @reason To offset the terrain generation of End Islands.
     */
    @Overwrite
    private static float sample(SimplexNoiseSampler sampler, int x, int z) {
        BigInteger xScaled = BigInteger.valueOf(x).multiply(UltimateScalerOptions.globalIntegerScale[0]).add(UltimateScalerOptions.globalIntegerOffset[0]);
        BigInteger zScaled = BigInteger.valueOf(z).multiply(UltimateScalerOptions.globalIntegerScale[2]).add(UltimateScalerOptions.globalIntegerOffset[2]);
        BigInteger i = xScaled.divide(BigInteger.TWO);
        BigInteger j = zScaled.divide(BigInteger.TWO);
        int k = ((int) (x * UltimateScalerOptions.scaleXFloatPart + UltimateScalerOptions.lastDigitOfOffsetX)) % 2;
        int l = ((int) (z * UltimateScalerOptions.scaleZFloatPart + UltimateScalerOptions.lastDigitOfOffsetZ)) % 2;
        BigInteger xScaledSquared = xScaled.multiply(xScaled);
        BigInteger zScaledSquared = zScaled.multiply(zScaled);
        float f = 100.0F - MathHelper.sqrt(config.simulateEndRings ? (float) (xScaledSquared.intValue() + zScaledSquared.intValue()) : xScaledSquared.add(zScaledSquared).floatValue()) * 8.0F;
        f = MathHelper.clamp(f, -100.0F, 80.0F);

        for(int m = -12; m <= 12; ++m) {
            for(int n = -12; n <= 12; ++n) {
                BigInteger o = i.add(BigInteger.valueOf(m));
                BigInteger p = j.add(BigInteger.valueOf(n));
                if (o.multiply(o).add(p.multiply(p)).compareTo(BigInteger.valueOf(4096)) >= 0 && sampler.sample(o.doubleValue(), p.doubleValue()) < (double)-0.9F) {
                    float g = (MathHelper.abs(o.floatValue()) * 3439.0F + MathHelper.abs(p.floatValue()) * 147.0F) % 13.0F + 9.0F;
                    float h = (float)(k - m * 2);
                    float q = (float)(l - n * 2);
                    float r = 100.0F - MathHelper.sqrt(h * h + q * q) * g;
                    r = MathHelper.clamp(r, -100.0F, 80.0F);
                    f = Math.max(f, r);
                }
            }
        }

        return f;
    }
}

