package me.inf32768.ultimate_scaler.mixins.border;

import net.minecraft.server.command.ForceLoadCommand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

import static me.inf32768.ultimate_scaler.option.UltimateScalerOptions.config;

@Mixin(ForceLoadCommand.class)
public abstract class MixinForceLoadCommand {
    @ModifyConstant(method = "executeChange", constant = @Constant(intValue = -30000000))
    private static int modifyMinCoordinate(int original) {
        return config.expandWorldBorder ? Integer.MIN_VALUE : original;
    }
    @ModifyConstant(method = "executeChange", constant = @Constant(intValue = 30000000))
    private static int modifyMaxCoordinate(int original) {
        return config.expandWorldBorder ? Integer.MAX_VALUE : original;
    }
}
