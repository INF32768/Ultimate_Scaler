package me.inf32768.ultimate_scaler.mixins.border;

import net.minecraft.util.math.ChunkPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import static me.inf32768.ultimate_scaler.option.UltimateScalerOptions.config;

@Mixin(ChunkPos.class)
public abstract class MixinChunkPos {
    @Redirect(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/ChunkPos;toLong(II)J"))
    private static long modifyToLong(int x, int z) {
        return config.expandWorldBorder ? ChunkPos.toLong(134217728, 134217728) : ChunkPos.toLong(x, z);
    }
}
