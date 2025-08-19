package me.inf32768.ultimate_scaler.mixins;

import me.inf32768.ultimate_scaler.util.Util;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.hud.debug.ChunkGenerationStatsDebugHudEntry;
import net.minecraft.client.gui.hud.debug.DebugHudLines;
import net.minecraft.world.World;
import net.minecraft.world.chunk.WorldChunk;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static me.inf32768.ultimate_scaler.option.UltimateScalerOptions.config;

@Environment(EnvType.CLIENT)
@Mixin(ChunkGenerationStatsDebugHudEntry.class)
public abstract class MixinChunkGenerationStatsDebugHudEntry {
    @Inject(method = "render", at = @At("TAIL"))
    private static void modifyDebugInfo(DebugHudLines lines, World world, WorldChunk clientChunk, WorldChunk chunk, CallbackInfo ci) {
        if (config.showTerrainPos) {
            Util.appendDebugInfo(lines, null);
        }
    }
}