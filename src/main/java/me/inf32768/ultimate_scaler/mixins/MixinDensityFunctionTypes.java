package me.inf32768.ultimate_scaler.mixins;

import com.mojang.serialization.Codec;
import net.minecraft.world.gen.densityfunction.DensityFunctionTypes;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;

import static me.inf32768.ultimate_scaler.option.UltimateScalerOptions.config;

@Mixin(DensityFunctionTypes.class)
public abstract class MixinDensityFunctionTypes {
    //扩大取值范围
    @ModifyConstant(method = "<clinit>", constant = @Constant(doubleValue = 1000000.0))
    private static double modifyMaxDistance(double original) {
        return config.expandDatapackValueRange ? Double.POSITIVE_INFINITY : original;
    }
    @Redirect(method = "<clinit>", at = @At(value = "FIELD", target = "Lnet/minecraft/world/gen/densityfunction/DensityFunctionTypes;CONSTANT_RANGE:Lcom/mojang/serialization/Codec;", opcode = Opcodes.GETSTATIC))
    private static Codec<Double> redirectConstantRange() {
        return config.expandDatapackValueRange ? Codec.doubleRange(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY) : Codec.doubleRange(-1000000.0, 1000000.0);
    }
}
