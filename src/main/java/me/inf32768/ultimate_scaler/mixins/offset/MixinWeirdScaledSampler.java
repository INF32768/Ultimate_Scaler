package me.inf32768.ultimate_scaler.mixins.offset;

import me.inf32768.ultimate_scaler.util.Util;
import net.minecraft.util.math.Direction;
import net.minecraft.world.gen.densityfunction.DensityFunction;
import net.minecraft.world.gen.densityfunction.DensityFunctionTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import static me.inf32768.ultimate_scaler.option.UltimateScalerOptions.config;

@Mixin(DensityFunctionTypes.WeirdScaledSampler.class)
public abstract class MixinWeirdScaledSampler {

    @ModifyArgs(method = "apply(Lnet/minecraft/world/gen/densityfunction/DensityFunction$NoisePos;D)D",at = @At(value = "INVOKE", target = "Lnet/minecraft/world/gen/densityfunction/DensityFunction$Noise;sample(DDD)D"))
    private void modifyNoiseSampleArgs(Args args, DensityFunction.NoisePos pos, double density) {
        double xRarity = pos.blockX() / (double) args.get(0);
        double yRarity = pos.blockY() / (double) args.get(1);
        double zRarity = pos.blockZ() / (double) args.get(2);
        double x = config.bigIntegerRewrite ? Util.getBigIntegerOffsetPos(pos.blockX(), Direction.Axis.X).doubleValue() : Util.getDoubleOffsetPos(pos.blockX(), Direction.Axis.X);
        double y = config.bigIntegerRewrite ? Util.getBigIntegerOffsetPos(pos.blockY(), Direction.Axis.Y).doubleValue() : Util.getDoubleOffsetPos(pos.blockY(), Direction.Axis.Y);
        double z = config.bigIntegerRewrite ? Util.getBigIntegerOffsetPos(pos.blockZ(), Direction.Axis.Z).doubleValue() : Util.getDoubleOffsetPos(pos.blockZ(), Direction.Axis.Z);
        args.set(0, Double.isFinite(xRarity) ? x / xRarity : 0);
        args.set(1, Double.isFinite(yRarity) ? y / yRarity : 0);
        args.set(2, Double.isFinite(zRarity) ? z / zRarity : 0);
    }
}
