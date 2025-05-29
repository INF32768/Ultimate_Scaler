package me.inf32768.ultimatescaler.option;

import com.mojang.blaze3d.systems.RenderSystem;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.client.gui.screen.Screen;

public class ModMenuIntegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return (screen) -> RenderSystem.isOnRenderThread() && Screen.hasShiftDown() ? (Screen) AutoConfig.getConfigScreen(UltimateScalerOptions.ConfigImpl.class, screen).get() : UltimateScalerOptions.ConfigImpl.getConfigBuilder().setParentScreen(screen).build();
    }
}
