package me.inf32768.ultimatescaler.mixins;

import net.minecraft.util.math.ChunkPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ChunkPos.class)
public abstract class MixinChunkPos {
    @ModifyConstant(method = "<clinit>", constant = @Constant(intValue = 32))
    private static int modifyMaxCoordinate(int constant) {
        return Integer.MIN_VALUE / 2 + 33554432;
    }
}