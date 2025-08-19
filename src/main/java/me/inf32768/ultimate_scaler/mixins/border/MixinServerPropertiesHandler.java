package me.inf32768.ultimate_scaler.mixins.border;

import net.minecraft.server.dedicated.ServerPropertiesHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

import static me.inf32768.ultimate_scaler.option.UltimateScalerOptions.config;

@Mixin(ServerPropertiesHandler.class)
public abstract class MixinServerPropertiesHandler {
    @ModifyConstant(method = "<init>", constant = @Constant(intValue = 29999984))
    private int modifyMaxWorldSize(int original) {
        return config.expandWorldBorder ? Integer.MAX_VALUE : original;
    }
}
