package me.inf32768.ultimate_scaler.mixins.border;

import net.minecraft.server.command.WorldBorderCommand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import static me.inf32768.ultimate_scaler.option.UltimateScalerOptions.config;

@Mixin(WorldBorderCommand.class)
public abstract class MixinWorldBorderCommand {
    @ModifyArgs(method = "register", at = @At(value = "INVOKE", target = "Lcom/mojang/brigadier/arguments/DoubleArgumentType;doubleArg(DD)Lcom/mojang/brigadier/arguments/DoubleArgumentType;", remap = false))
    private static void modifyDoubleArg(Args args) {
        if (config.expandWorldBorder) {
            args.set(0,Double.NEGATIVE_INFINITY);
            args.set(1,Double.POSITIVE_INFINITY);
        }
    }

    @Redirect(method = "executeCenter", at = @At(value = "INVOKE", target = "Ljava/lang/Math;abs(F)F"))
    private static float modifyAbs(float value) {
        return config.expandWorldBorder ? 0F : Math.abs(value);
    }

    @ModifyConstant(method = "executeSet", constant = @Constant(doubleValue = 1.0))
    private static double modifyConstant(double original) {
        return config.expandWorldBorder ? 0D : original;
    }

    @ModifyConstant(method = "executeSet", constant = @Constant(doubleValue = 59999968))
    private static double modifyConstant2(double original) {
        return config.expandWorldBorder ? Double.POSITIVE_INFINITY : original;
    }
}
