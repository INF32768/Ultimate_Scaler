package me.inf32768.ultimate_scaler.mixins;

import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.chunk.AbstractChunkHolder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import static me.inf32768.ultimate_scaler.option.UltimateScalerOptions.config;

@Mixin(AbstractChunkHolder.class)
public abstract class MixinAbstractChunkHolder {
    @ModifyArgs(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/ChunkPos;getChebyshevDistance(Lnet/minecraft/util/math/ChunkPos;)I"))
    private void modifyInit(Args args, ChunkPos pos) {
        if (config.fixChunkGenerationOutOfBound) {
            args.set(0, pos);
        }
    }
}