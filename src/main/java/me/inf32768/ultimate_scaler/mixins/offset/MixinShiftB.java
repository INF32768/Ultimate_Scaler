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

@Mixin(DensityFunctionTypes.ShiftB.class)
public abstract class MixinShiftB {
    @ModifyArgs(method = "sample", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/gen/densityfunction/DensityFunctionTypes$ShiftB;sample(DDD)D"))
    private void modifyArgs(Args args, DensityFunction.NoisePos pos) {
        double x = config.bigIntegerRewrite ? Util.getBigIntegerOffsetPos(pos.blockX(), Direction.Axis.X).doubleValue() : Util.getDoubleOffsetPos(pos.blockX(), Direction.Axis.X);
        double z = config.bigIntegerRewrite ? Util.getBigIntegerOffsetPos(pos.blockZ(), Direction.Axis.Z).doubleValue() : Util.getDoubleOffsetPos(pos.blockZ(), Direction.Axis.Z);
        args.set(0, z);
        args.set(1, x);
        args.set(2, 0.0D);
    }
}
