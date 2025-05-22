package me.inf32768.ultimatescaler.config;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.ConfigHolder;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.clothconfig2.gui.entries.StringListListEntry;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;

import java.util.Arrays;

public class WorldGenOptions {
    public static ConfigHolder<ConfigImpl> holder;
    public static ConfigImpl config;

    public static double[] globalOffset = {0.0, 0.0, 0.0};
    public static double[] globalScale = {1.0, 1.0, 1.0};

    @Config(name = "ultimatescaler")
    public static class ConfigImpl implements ConfigData {

        public String[] globalOffsetStr = {"0.0", "0.0", "0.0"};
        public String[] globalScaleStr = {"1.0", "1.0", "1.0"};


        public static ConfigBuilder getConfigBuilder() {
            ConfigBuilder builder = ConfigBuilder.create().setTitle(Text.translatable("ultimatescaler.options"));
            builder.setGlobalized(true);
            ConfigEntryBuilder entryBuilder = builder.entryBuilder();
            ConfigCategory worldGen = builder.getOrCreateCategory(Text.translatable("ultimatescaler.options.worldgen"));
            for (int i = 0; i < 3; i++) {
                config.globalOffsetStr[i] = Double.toString(globalOffset[i]);
                config.globalScaleStr[i] = Double.toString(globalScale[i]);
            }
            StringListListEntry globalOffsetEntry = entryBuilder.startStrList(Text.translatable("ultimatescaler.options.worldgen.offset.globalOffset"), Arrays.asList(config.globalOffsetStr))
                    .setDefaultValue(Arrays.asList("0.0", "0.0", "0.0"))
                    .setTooltip(Text.translatable("ultimatescaler.options.parsableDouble.tooltip"))
                    .setInsertButtonEnabled(false)
                    .setDeleteButtonEnabled(false)
                    .build();
            StringListListEntry globalScaleEntry = entryBuilder.startStrList(Text.translatable("ultimatescaler.options.worldgen.offset.globalScale"), Arrays.asList(config.globalScaleStr))
                    .setDefaultValue(Arrays.asList("1.0", "1.0", "1.0"))
                    .setTooltip(Text.translatable("ultimatescaler.options.parsableDouble.tooltip"))
                    .setInsertButtonEnabled(false)
                    .setDeleteButtonEnabled(false)
                    .build();
            worldGen.addEntry(globalOffsetEntry);
            worldGen.addEntry(globalScaleEntry);

            builder.setSavingRunnable(() -> {
                boolean validInput = true;

                try {
                    globalOffset = Arrays.stream(globalOffsetEntry.getValue().toArray(new String[3])).mapToDouble(Double::parseDouble).toArray();
                    globalScale = Arrays.stream(globalScaleEntry.getValue().toArray(new String[3])).mapToDouble(Double::parseDouble).toArray();
                } catch (NumberFormatException e) {
                    validInput = false;
                }
                if (validInput) {
                    config.globalOffsetStr = (globalOffsetEntry.getValue().toArray(new String[3]));
                    config.globalScaleStr = (globalScaleEntry.getValue().toArray(new String[3]));
                    holder.save();
                }
            });
            return builder;
        }
    }

    public static void initializeConfig() {
        WorldGenOptions.holder = AutoConfig.register(ConfigImpl.class, Toml4jConfigSerializer::new);
        WorldGenOptions.holder.getConfig();
        AutoConfig.getConfigHolder(ConfigImpl.class).registerSaveListener((manager, data) -> ActionResult.SUCCESS);
        AutoConfig.getConfigHolder(ConfigImpl.class).registerLoadListener((manager, newData) -> ActionResult.SUCCESS);
        config = AutoConfig.getConfigHolder(ConfigImpl.class).getConfig();
        globalOffset = Arrays.stream(config.globalOffsetStr).mapToDouble(Double::parseDouble).toArray();
        globalScale = Arrays.stream(config.globalScaleStr).mapToDouble(Double::parseDouble).toArray();
    }
}