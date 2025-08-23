package me.inf32768.ultimate_scaler.mixins.fixing;

import com.llamalad7.mixinextras.sugar.Local;
import me.inf32768.ultimate_scaler.util.Util;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import static me.inf32768.ultimate_scaler.option.UltimateScalerOptions.config;

@Mixin(targets = "net.minecraft.structure.MineshaftGenerator.MineshaftPart")
public abstract class MixinMineshaftPart {
    @ModifyVariable(method = "cannotGenerate", at = @At("STORE"))
    private static BlockPos.Mutable modifyPos(
        BlockPos.Mutable pos,
        @Local(type = Integer.class, ordinal = 0) int i,
        @Local(type = Integer.class, ordinal = 1) int j,
        @Local(type = Integer.class, ordinal = 2) int k,
        @Local(type = Integer.class, ordinal = 3) int l,
        @Local(type = Integer.class, ordinal = 4) int m,
        @Local(type = Integer.class, ordinal = 5) int n
    ) {
        return config.fixMineshaftCannotGenerate ? new BlockPos.Mutable(Util.average(i, l), Util.average(j, m), Util.average(k, n)) : new BlockPos.Mutable((i + l) / 2, (j + m) / 2, (k + n) / 2);
    }
}
