package me.inf32768.ultimate_scaler.option;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.inf32768.ultimate_scaler.UltimateScaler;

public class ModMenuIntegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        if (UltimateScaler.IS_CLOTH_CONFIG_PRESENT && UltimateScaler.MC_VERSION >= 4080) {
            return (screen) -> ClothConfigBuilder.getConfigBuilder().setParentScreen(screen).build();
        } else {
            return null;
        }
    }
}
