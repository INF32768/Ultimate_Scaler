package me.inf32768.ultimatescaler.mixins;

import me.inf32768.ultimatescaler.util.Util;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.noise.SimplexNoiseSampler;
import net.minecraft.world.gen.densityfunction.DensityFunction;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.math.BigInteger;

import static me.inf32768.ultimatescaler.option.UltimateScalerOptions.config;

@Mixin(targets = "net.minecraft.world.gen.densityfunction.DensityFunctionTypes$EndIslands")
public abstract class MixinEndIslands {
    @Shadow @Final private SimplexNoiseSampler sampler;

    @Inject(method = "sample(Lnet/minecraft/world/gen/densityfunction/DensityFunction$NoisePos;)D", at = @At("TAIL"), cancellable = true)
    public void modifySample(DensityFunction.NoisePos pos, CallbackInfoReturnable<Double> cir) {
        if (config.bigIntegerRewrite) {
            BigInteger x = Util.getBigIntegerOffsetPos(pos.blockX(), Direction.Axis.X).divide(BigInteger.valueOf(8));
            BigInteger z = Util.getBigIntegerOffsetPos(pos.blockZ(), Direction.Axis.Z).divide(BigInteger.valueOf(8));
            cir.setReturnValue(((double) sample(this.sampler, x, z) - (double) 8.0F) / (double) 128.0F);
        }
    }

    @Unique
    private static float sample(SimplexNoiseSampler sampler, BigInteger x, BigInteger z) {
        BigInteger i = x.divide(BigInteger.TWO);
        BigInteger j = z.divide(BigInteger.TWO);
        int k = x.remainder(BigInteger.TWO).intValue();
        int l = z.remainder(BigInteger.TWO).intValue();
        float f = 100.0F - MathHelper.sqrt(config.simulateEndRings ? (float) (x.intValue() * x.intValue() + z.intValue() * z.intValue()) : x.multiply(x).add(z.multiply(z)).floatValue()) * 8.0F;
        f = MathHelper.clamp(f, -100.0F, 80.0F);

        for(int m = -12; m <= 12; ++m) {
            for(int n = -12; n <= 12; ++n) {
                BigInteger o = i.add(BigInteger.valueOf(m));
                BigInteger p = j.add(BigInteger.valueOf(n));
                if (o.multiply(o).add(p.multiply(p)).doubleValue() > 4096 && sampler.sample(o.doubleValue(), p.doubleValue()) < (double)-0.9F) {
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