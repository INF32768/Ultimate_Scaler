package me.inf32768.ultimate_scaler.mixins;

import net.minecraft.block.Blocks;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.chunk.AquiferSampler;
import net.minecraft.world.gen.chunk.ChunkGeneratorSettings;
import net.minecraft.world.gen.chunk.NoiseChunkGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static me.inf32768.ultimate_scaler.option.UltimateScalerOptions.config;

@Mixin(NoiseChunkGenerator.class)
public abstract class MixinNoiseChunkGenerator {
    //偏移海平面和地底熔岩层，替换默认流体
    @Inject(method = "createFluidLevelSampler", at = @At("HEAD"), cancellable = true)
    private static void createFluidLevelSampler(ChunkGeneratorSettings settings, CallbackInfoReturnable<AquiferSampler.FluidLevelSampler> cir) {
        cir.setReturnValue((x, y, z) -> {
            double scale = config.globalBigDecimalScale[1].doubleValue();
            double offset = config.globalBigDecimalOffset[1].doubleValue();
            int lavaLevelY = config.extraYOffset ? (int) (-54D / scale - offset) : -54;
            int seaLevelY = config.extraYOffset ? (int) (settings.seaLevel() / scale - offset) : settings.seaLevel();
            AquiferSampler.FluidLevel lavaLevel = new AquiferSampler.FluidLevel(lavaLevelY, config.replaceUndergroundLava ? Registries.BLOCK.get(Identifier.of(config.replaceUndergroundLavaBlock)).getDefaultState() : Blocks.LAVA.getDefaultState());
            AquiferSampler.FluidLevel waterLevel = new AquiferSampler.FluidLevel(seaLevelY, config.replaceDefaultFluid ? Registries.BLOCK.get(Identifier.of(config.replaceDefaultFluidBlock)).getDefaultState() : settings.defaultFluid());
            return y < Math.min(lavaLevelY, seaLevelY) ? lavaLevel : waterLevel;
        });
    }
}
