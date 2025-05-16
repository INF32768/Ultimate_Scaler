package me.inf32768.ultimatescaler.mixins;

import net.minecraft.world.gen.carver.CaveCarver;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import static me.inf32768.ultimatescaler.UltimateScaler.config;

@Mixin(CaveCarver.class)
public abstract class MixinCaveCarver {
    @Unique
    private static final String CARVE_METHOD = "carve(Lnet/minecraft/world/gen/carver/CarverContext;Lnet/minecraft/world/gen/carver/CaveCarverConfig;Lnet/minecraft/world/chunk/Chunk;Ljava/util/function/Function;Lnet/minecraft/util/math/random/Random;Lnet/minecraft/world/gen/chunk/AquiferSampler;Lnet/minecraft/util/math/ChunkPos;Lnet/minecraft/world/gen/carver/CarvingMask;)Z";

    @ModifyVariable(method = CARVE_METHOD, at = @At("STORE"), ordinal = 3)
    private double modifyCarveX(double value) {
        return value * config.globalXScale + config.globalXOffset;
    }
    @ModifyVariable(method = CARVE_METHOD, at = @At("STORE"), ordinal = 4)
    private double modifyCarveY(double value) {
        return value * config.globalYScale + config.globalYOffset;
    }
    @ModifyVariable(method = CARVE_METHOD, at = @At("STORE"), ordinal = 5)
    private double modifyCarveZ(double value) {
        return value * config.globalZScale + config.globalZOffset;
    }

}