package me.inf32768.ultimate_scaler;

import me.inf32768.ultimate_scaler.option.UltimateScalerOptions;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;

import java.io.IOException;

public class ConfigReloader implements SimpleSynchronousResourceReloadListener {
    private static final Identifier LISTENER_ID = Identifier.of("ultimate_scaler", "config_reloader");

    @Override
    public Identifier getFabricId() {
        return LISTENER_ID;
    }

    @Override
    public void reload(ResourceManager manager) {
        try {
            UltimateScalerOptions.loadConfig();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        UltimateScaler.LOGGER.info("Config reloaded");
    }
}
