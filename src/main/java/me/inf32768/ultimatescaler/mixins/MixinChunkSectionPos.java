/*
    Doesn't seem to be effective, but it's here just in case.
*/

package me.inf32768.ultimatescaler.mixins;

import net.minecraft.util.math.ChunkSectionPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(ChunkSectionPos.class)
public abstract class MixinChunkSectionPos {
    /**
     * @author INF32768
     * @reason Fix the asLong method to return the correct value when X position outsize of 33554432 (2^25)
     */
    @Overwrite
    public static long asLong(int x, int y, int z) {
        long l = 0L;
        l |= ((long)x & 67108863L) << 38;
        l |= ((long)y & 4095L) << 0;
        return l | ((long)z & 67108863L) << 12;
    }

    /**
     * @author INF32768
     * @reason To work with the new asLong method, we need to update the unpackX, unpackY and unpackZ methods
     */
    @Overwrite
    public static int unpackX(long packed) {
        return (int)(packed << 0 >> 38);
    }

    /**
     * @author
     * @reason
     */
    @Overwrite
    public static int unpackY(long packed) {
        return (int)(packed << 52 >> 52);
    }

    /**
     * @author
     * @reason
     */
    @Overwrite
    public static int unpackZ(long packed) {
        return (int)(packed << 26 >> 38);
    }

}
