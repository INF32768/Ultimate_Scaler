package me.inf32768.ultimatescaler.mixins;

import net.minecraft.world.gen.densityfunction.DensityFunctionTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import static me.inf32768.ultimatescaler.UltimateScaler.config;

@Mixin(DensityFunctionTypes.ShiftB.class)
public abstract class MixinShiftB implements DensityFunctionTypes.Offset {

    /**
     * @author INF32768
     * @reason Modify the parameters passed to the sample method
     */
    @Overwrite
    public double sample(NoisePos pos) {
        return this.sample((double)pos.blockZ() * config.globalZScale + config.globalZOffset, (double)pos.blockX() * config.globalXScale + config.globalXOffset, (double)0.0F) * (double)4.0F;
    }
}
