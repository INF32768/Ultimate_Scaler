package me.inf32768.ultimatescaler.mixins;

import net.minecraft.world.gen.densityfunction.DensityFunctionTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import me.inf32768.ultimatescaler.config.WorldGenOptions;

@Mixin(DensityFunctionTypes.Shift.class)
public abstract class MixinShift implements DensityFunctionTypes.Offset {

    /**
     * @author INF32768
     * @reason Modify the parameters passed to the sample method
     */
    @Overwrite
    public double sample(NoisePos pos) {
        return this.sample((double)pos.blockX() * WorldGenOptions.globalScale[0] + WorldGenOptions.globalOffset[0], (double)pos.blockY() * WorldGenOptions.globalScale[1] + WorldGenOptions.globalOffset[1], (double)pos.blockZ() * WorldGenOptions.globalScale[2] + WorldGenOptions.globalOffset[2]) * (double)4.0F;
    }
}
