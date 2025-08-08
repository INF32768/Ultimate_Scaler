package me.inf32768.ultimate_scaler.option;

import me.inf32768.ultimate_scaler.UltimateScaler;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceType;
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
        UltimateScaler.LOGGER.info("[Ultimate Scaler] Config reloaded");
    }

    public static void init() {
        ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(new ConfigReloader());
    }
}
