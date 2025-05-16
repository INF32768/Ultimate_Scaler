package me.inf32768.ultimatescaler.mixins;

import net.minecraft.world.gen.densityfunction.DensityFunction;
import net.minecraft.world.gen.densityfunction.DensityFunctionTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import static me.inf32768.ultimatescaler.UltimateScaler.config;

@Mixin(DensityFunctionTypes.Noise.class)
public abstract class MixinNoiseSampler {
    @ModifyArgs(method = "sample", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/gen/densityfunction/DensityFunction$Noise;sample(DDD)D"))
    private void modifyNoiseSampleArgs(Args args, DensityFunction.NoisePos pos) {

        double x = (pos.blockX() * config.globalXScale + config.globalXOffset) * this.getXzScale();
        double y = (pos.blockY() * config.globalYScale + config.globalYOffset) * this.getYScale();
        double z = (pos.blockZ() * config.globalZScale + config.globalZOffset) * this.getXzScale();

        args.set(0, x);
        args.set(1, y);
        args.set(2, z);
    }

    @Accessor("xzScale")
    public abstract double getXzScale();
    @Accessor("yScale")
    public abstract double getYScale();
}
