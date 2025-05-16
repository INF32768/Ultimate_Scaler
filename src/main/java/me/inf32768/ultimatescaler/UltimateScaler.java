package me.inf32768.ultimatescaler;

import me.inf32768.ultimatescaler.commands.LocatePosition;
import me.inf32768.ultimatescaler.config.WorldGenOptions;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.ModInitializer;

public class UltimateScaler implements ModInitializer {
    public static WorldGenOptions config;

    @Override
    public void onInitialize() {
        LocatePosition.init();

        AutoConfig.register(WorldGenOptions.class, Toml4jConfigSerializer::new);
        config = AutoConfig.getConfigHolder(WorldGenOptions.class).getConfig();
    }
}
