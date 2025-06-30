package me.inf32768.ultimatescaler.mixins;

import net.minecraft.world.gen.densityfunction.DensityFunctionTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import me.inf32768.ultimatescaler.option.UltimateScalerOptions;

@Mixin(DensityFunctionTypes.ShiftB.class)
public abstract class MixinShiftB implements DensityFunctionTypes.Offset {

    /**
     * @author INF32768
     * @reason Modify the parameters passed to the sample method
     */
    @Overwrite
    public double sample(NoisePos pos) {
        return this.sample((double)pos.blockZ() * UltimateScalerOptions.globalScale[2] + UltimateScalerOptions.globalIntegerOffset[2].doubleValue(), (double)pos.blockX() * UltimateScalerOptions.globalScale[0] + UltimateScalerOptions.globalIntegerOffset[0].doubleValue(), 0.0D);
    }
}
