package me.inf32768.ultimatescaler.mixins;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.DebugHud;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.Locale;

import me.inf32768.ultimatescaler.option.UltimateScalerOptions;

import static me.inf32768.ultimatescaler.option.UltimateScalerOptions.config;

@Mixin(DebugHud.class)
public abstract class MixinDebugHud {
    @Inject(at = @At(value = "INVOKE", target = "Ljava/util/List;add(Ljava/lang/Object;)Z", ordinal = 4), method = "getLeftText")
    protected void getLeftText(CallbackInfoReturnable<List<String>> cir, @Local List<String> list) {
        MinecraftClient mc = MinecraftClient.getInstance();
        Vec3d pos = null;
        if (mc.getCameraEntity() != null) {
            pos = mc.getCameraEntity().getPos();
        }
        if (pos == null) {
            return;
        }
        double x = pos.x * UltimateScalerOptions.globalScale[0] + UltimateScalerOptions.globalIntegerOffset[0].doubleValue();
        double y = pos.y * UltimateScalerOptions.globalScale[1] + UltimateScalerOptions.globalIntegerOffset[1].doubleValue();
        double z = pos.z * UltimateScalerOptions.globalScale[2] + UltimateScalerOptions.globalIntegerOffset[2].doubleValue();
        if (config.showTerrainPos) {
            list.add(String.format(Locale.ROOT, "TerrainXYZ: %.0f %.0f %.0f", x, y, z));
        }
    }
}