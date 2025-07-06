package me.inf32768.ultimatescaler.mixins;

import me.inf32768.ultimatescaler.util.Util;
import net.minecraft.util.math.Direction;
import net.minecraft.world.gen.densityfunction.DensityFunction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import static me.inf32768.ultimatescaler.option.UltimateScalerOptions.config;

@Mixin(targets = "net.minecraft.world.gen.densityfunction.DensityFunctionTypes$Shift")
public abstract class MixinShift {
    @ModifyArgs(method = "sample", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/gen/densityfunction/DensityFunctionTypes$Shift;sample(DDD)D"))
    private void modifyArgs(Args args, DensityFunction.NoisePos pos) {
        double x = config.bigIntegerRewrite ? Util.getBigIntegerOffsetPos(pos.blockX(), Direction.Axis.X).doubleValue() : Util.getDoubleOffsetPos(pos.blockX(), Direction.Axis.X);
        double y = config.bigIntegerRewrite ? Util.getBigIntegerOffsetPos(pos.blockY(), Direction.Axis.Y).doubleValue() : Util.getDoubleOffsetPos(pos.blockY(), Direction.Axis.Y);
        double z = config.bigIntegerRewrite ? Util.getBigIntegerOffsetPos(pos.blockZ(), Direction.Axis.Z).doubleValue() : Util.getDoubleOffsetPos(pos.blockZ(), Direction.Axis.Z);
        args.set(0, x);
        args.set(1, y);
        args.set(2, z);
    }
}
