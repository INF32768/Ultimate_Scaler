package me.inf32768.ultimatescaler.mixins;

import net.minecraft.world.gen.densityfunction.DensityFunctionTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import static me.inf32768.ultimatescaler.UltimateScaler.config;

@Mixin(DensityFunctionTypes.Shift.class)
public abstract class MixinShift implements DensityFunctionTypes.Offset {

    /**
     * @author INF32768
     * @reason Modify the parameters passed to the sample method
     */
    @Overwrite
    public double sample(NoisePos pos) {
        return this.sample((double)pos.blockX() * config.globalXScale + config.globalXOffset, (double)pos.blockY() * config.globalYScale + config.globalYOffset, (double)pos.blockZ() * config.globalZScale + config.globalZOffset) * (double)4.0F;
    }
}
