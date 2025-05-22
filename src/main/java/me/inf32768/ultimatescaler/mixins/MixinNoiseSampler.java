package me.inf32768.ultimatescaler.mixins;

import net.minecraft.world.gen.densityfunction.DensityFunction;
import net.minecraft.world.gen.densityfunction.DensityFunctionTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import me.inf32768.ultimatescaler.config.WorldGenOptions;

@Mixin(DensityFunctionTypes.Noise.class)
public abstract class MixinNoiseSampler {
    @ModifyArgs(method = "sample", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/gen/densityfunction/DensityFunction$Noise;sample(DDD)D"))
    private void modifyNoiseSampleArgs(Args args, DensityFunction.NoisePos pos) {

        double x = (pos.blockX() * WorldGenOptions.globalScale[0] + WorldGenOptions.globalOffset[0]) * this.getXzScale();
        double y = (pos.blockY() * WorldGenOptions.globalScale[1] + WorldGenOptions.globalOffset[1]) * this.getYScale();
        double z = (pos.blockZ() * WorldGenOptions.globalScale[2] + WorldGenOptions.globalOffset[2]) * this.getXzScale();

        args.set(0, x);
        args.set(1, y);
        args.set(2, z);
    }

    @Accessor("xzScale")
    public abstract double getXzScale();
    @Accessor("yScale")
    public abstract double getYScale();
}
