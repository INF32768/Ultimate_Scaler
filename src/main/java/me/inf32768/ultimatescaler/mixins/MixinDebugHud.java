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

import static me.inf32768.ultimatescaler.UltimateScaler.config;

@Mixin(DebugHud.class)
public abstract class MixinDebugHud {
    @Inject(at = @At(value = "INVOKE", target = "Ljava/util/List;add(Ljava/lang/Object;)Z", ordinal = 4), method = "getLeftText")
    protected void getLeftText(CallbackInfoReturnable<List<String>> cir, @Local List<String> list) {
        MinecraftClient mc = MinecraftClient.getInstance();
        Vec3d pos = null;
        if (mc.player != null) {
            pos = mc.player.getPos();
        }
        if (pos == null) {
            return;
        }
        double x = pos.x * config.globalXScale + config.globalXOffset;
        double y = pos.y * config.globalYScale + config.globalYOffset;
        double z = pos.z * config.globalZScale + config.globalZOffset;
        list.add(String.format(Locale.ROOT, "TerrainXYZ: %.0f %.0f %.0f", x, y, z));
    }
}