package me.inf32768.ultimatescaler.mixins;

import me.inf32768.ultimatescaler.option.UltimateScalerOptions;
import net.minecraft.world.gen.densityfunction.DensityFunction;
import net.minecraft.world.gen.densityfunction.DensityFunctionTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(DensityFunctionTypes.WeirdScaledSampler.class)
public abstract class MixinWeirdScaledSampler {

    @ModifyArgs(method = "apply(Lnet/minecraft/world/gen/densityfunction/DensityFunction$NoisePos;D)D",at = @At(value = "INVOKE", target = "Lnet/minecraft/world/gen/densityfunction/DensityFunction$Noise;sample(DDD)D"))
    private void modifyNoiseSampleArgs(Args args, DensityFunction.NoisePos pos, double density) {
        double rarity = this.getRarityValueMapper().scaleFunction.get(density);
        args.set(0, (pos.blockX() * UltimateScalerOptions.globalScale[0] + UltimateScalerOptions.globalIntegerOffset[0].doubleValue()) * rarity);
        args.set(1, (pos.blockY() * UltimateScalerOptions.globalScale[1] + UltimateScalerOptions.globalIntegerOffset[1].doubleValue()) * rarity);
        args.set(2, (pos.blockZ() * UltimateScalerOptions.globalScale[2] + UltimateScalerOptions.globalIntegerOffset[2].doubleValue()) * rarity);
    }

    @Accessor("rarityValueMapper")
    public abstract DensityFunctionTypes.WeirdScaledSampler.RarityValueMapper getRarityValueMapper();
}
