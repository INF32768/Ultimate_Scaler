package me.inf32768.ultimatescaler;

import me.inf32768.ultimatescaler.commands.LocatePosition;
import me.inf32768.ultimatescaler.option.KeyBindings;
import me.inf32768.ultimatescaler.option.UltimateScalerOptions;
import net.fabricmc.api.ModInitializer;

public class UltimateScaler implements ModInitializer {

    @Override
    public void onInitialize() {
        LocatePosition.init();
        UltimateScalerOptions.init();
        KeyBindings.init();
    }
}