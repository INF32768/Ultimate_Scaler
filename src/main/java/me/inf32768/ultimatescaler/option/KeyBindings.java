package me.inf32768.ultimatescaler.option;

import me.shedaniel.clothconfig2.api.ModifierKeyCode;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class KeyBindings {
    public static ModifierKeyCode modifierOptionMenuKey = UltimateScalerOptions.optionMenuKey;

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
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            //不知道为什么，这里的KeyBinding.isPressed()只能检测到一个按键，所以只能用InputUtil.isKeyPressed()来判断组合按键是否被按下
            if (InputUtil.isKeyPressed(MinecraftClient.getInstance().getWindow().getHandle(), modifierOptionMenuKey.getKeyCode().getCode()) && MinecraftClient.getInstance().currentScreen == null) {
                if (isCtrlPressed() == modifierOptionMenuKey.getModifier().hasControl() && isShiftPressed() == modifierOptionMenuKey.getModifier().hasShift() && isAltPressed() == modifierOptionMenuKey.getModifier().hasAlt()) {
                    MinecraftClient.getInstance().setScreen(UltimateScalerOptions.ConfigImpl.getConfigBuilder().build());
                }
            }
        });
    }
}