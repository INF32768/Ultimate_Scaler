package me.inf32768.ultimate_scaler.mixins;

import net.minecraft.world.gen.densityfunction.DensityFunctionTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

import static me.inf32768.ultimate_scaler.option.UltimateScalerOptions.config;

@Mixin(DensityFunctionTypes.class)
public abstract class MixinDensityFunctionTypes {
    //扩大取值范围
    @ModifyConstant(method = "<clinit>", constant = @Constant(doubleValue = 1000000.0))
    private static double modifyMaxConstantRange(double original) {
        return config.expandDatapackValueRange ? Double.POSITIVE_INFINITY : original;
    }

    @ModifyConstant(method = "<clinit>", constant = @Constant(doubleValue = -1000000.0))
    private static double modifyMinConstantRange(double original) {
        return config.expandDatapackValueRange ? Double.NEGATIVE_INFINITY : original;
    }
}
