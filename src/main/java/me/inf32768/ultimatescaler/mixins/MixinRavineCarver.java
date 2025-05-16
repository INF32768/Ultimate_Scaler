package me.inf32768.ultimatescaler.mixins;

import net.minecraft.world.gen.carver.RavineCarver;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import static me.inf32768.ultimatescaler.UltimateScaler.config;

@Mixin(RavineCarver.class)
public abstract class MixinRavineCarver {
    @ModifyArgs(method = "carve(Lnet/minecraft/world/gen/carver/CarverContext;Lnet/minecraft/world/gen/carver/RavineCarverConfig;Lnet/minecraft/world/chunk/Chunk;Ljava/util/function/Function;Lnet/minecraft/util/math/random/Random;Lnet/minecraft/world/gen/chunk/AquiferSampler;Lnet/minecraft/util/math/ChunkPos;Lnet/minecraft/world/gen/carver/CarvingMask;)Z", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/gen/carver/RavineCarver;carveRavine(Lnet/minecraft/world/gen/carver/CarverContext;Lnet/minecraft/world/gen/carver/RavineCarverConfig;Lnet/minecraft/world/chunk/Chunk;Ljava/util/function/Function;JLnet/minecraft/world/gen/chunk/AquiferSampler;DDDFFFIIDLnet/minecraft/world/gen/carver/CarvingMask;)V"))
    private void modifyCarveRavine(Args args) {
        double x = args.get(6);
        double y = args.get(7);
        double z = args.get(8);

        x = x * config.globalXScale + config.globalXOffset;
        y = y * config.globalYScale + config.globalYOffset;
        z = z * config.globalZScale + config.globalZOffset;

        args.set(6, x);
        args.set(7, y);
        args.set(8, z);
    }
}
