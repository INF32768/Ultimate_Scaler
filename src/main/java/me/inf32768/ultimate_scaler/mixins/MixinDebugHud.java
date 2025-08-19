package me.inf32768.ultimate_scaler.mixins;

import com.llamalad7.mixinextras.sugar.Local;
import me.inf32768.ultimate_scaler.util.Util;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.hud.DebugHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

import static me.inf32768.ultimate_scaler.option.UltimateScalerOptions.config;

@Environment(EnvType.CLIENT)
@Mixin(DebugHud.class)
public abstract class MixinDebugHud {
    @Inject(method = "method_1835", at = @At(value = "INVOKE", target = "Ljava/util/List;add(Ljava/lang/Object;)Z", ordinal = 4), remap = false)
    private static void modifyLeftText(CallbackInfoReturnable<List<String>> cir, @Local List<String> list) {
        if (config.showTerrainPos) {
            Util.appendDebugInfo(null, list);
        }
    }
}