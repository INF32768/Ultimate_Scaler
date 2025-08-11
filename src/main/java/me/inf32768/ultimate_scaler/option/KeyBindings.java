package me.inf32768.ultimate_scaler.option;

import me.inf32768.ultimate_scaler.UltimateScaler;
import me.shedaniel.clothconfig2.api.Modifier;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

import static me.inf32768.ultimate_scaler.option.UltimateScalerOptions.config;

@Environment(EnvType.CLIENT)
public class KeyBindings {
    private static boolean isCtrlPressed() {
        return InputUtil.isKeyPressed(MinecraftClient.getInstance().getWindow().getHandle(), GLFW.GLFW_KEY_LEFT_CONTROL) || InputUtil.isKeyPressed(MinecraftClient.getInstance().getWindow().getHandle(), GLFW.GLFW_KEY_RIGHT_CONTROL);
    }

    private static boolean isShiftPressed() {
        return InputUtil.isKeyPressed(MinecraftClient.getInstance().getWindow().getHandle(), GLFW.GLFW_KEY_LEFT_SHIFT) || InputUtil.isKeyPressed(MinecraftClient.getInstance().getWindow().getHandle(), GLFW.GLFW_KEY_RIGHT_SHIFT);
    }

    private static boolean isAltPressed() {
        return InputUtil.isKeyPressed(MinecraftClient.getInstance().getWindow().getHandle(), GLFW.GLFW_KEY_LEFT_ALT) || InputUtil.isKeyPressed(MinecraftClient.getInstance().getWindow().getHandle(), GLFW.GLFW_KEY_RIGHT_ALT);
    }

    public static void init() {
        if (UltimateScaler.IS_CLOTH_CONFIG_PRESENT) {
            ClientTickEvents.END_CLIENT_TICK.register(client -> {
                //不知道为什么，这里的KeyBinding.isPressed()只能检测到一个按键，所以只能用InputUtil.isKeyPressed()来判断组合按键是否被按下
                if (InputUtil.isKeyPressed(MinecraftClient.getInstance().getWindow().getHandle(), config.optionMenuKeyCode) && MinecraftClient.getInstance().currentScreen == null) {
                    if (isCtrlPressed() == Modifier.of(config.optionMenuModifierValue).hasControl() && isShiftPressed() == Modifier.of(config.optionMenuModifierValue).hasShift() && isAltPressed() == Modifier.of(config.optionMenuModifierValue).hasAlt()) {
                        MinecraftClient.getInstance().setScreen(ClothConfigBuilder.getConfigBuilder().build());
                    }
                }
            });
        }
    }
}