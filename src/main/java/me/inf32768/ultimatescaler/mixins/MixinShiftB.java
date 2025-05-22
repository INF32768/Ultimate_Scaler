package me.inf32768.ultimatescaler.mixins;

import net.minecraft.world.gen.densityfunction.DensityFunctionTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import me.inf32768.ultimatescaler.config.WorldGenOptions;

@Mixin(DensityFunctionTypes.ShiftB.class)
public abstract class MixinShiftB implements DensityFunctionTypes.Offset {

    /**
     * @author INF32768
     * @reason Modify the parameters passed to the sample method
     */
    @Overwrite
    public double sample(NoisePos pos) {
        return this.sample((double)pos.blockZ() * WorldGenOptions.globalScale[2] + WorldGenOptions.globalOffset[2], (double)pos.blockX() * WorldGenOptions.globalScale[0] + WorldGenOptions.globalOffset[0], 0.0D) * 4.0D;
    }
}
