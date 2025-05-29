package me.inf32768.ultimatescaler.option;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.ConfigHolder;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import me.shedaniel.clothconfig2.api.*;
import me.shedaniel.clothconfig2.gui.entries.*;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import org.lwjgl.glfw.GLFW;

import java.util.Arrays;
import java.util.Optional;

public class UltimateScalerOptions {
    public static ConfigHolder<ConfigImpl> holder;
    public static ConfigImpl config;
    public static double[] globalOffset = {0.0, 0.0, 0.0};
    public static double[] globalScale = {1.0, 1.0, 1.0};
    public static ModifierKeyCode optionMenuKey;

    @Config(name = "ultimatescaler")
    public static class ConfigImpl implements ConfigData {

        private String[] globalOffsetStr = {"0.0", "0.0", "0.0"};
        private String[] globalScaleStr = {"1.0", "1.0", "1.0"};
        private int optionMenuKeyCode;
        private Modifier optionMenuModifier;
        public boolean showTerrainPos = true;
        public FarlandsPos farlandsPos = FarlandsPos.DEFAULT;
        public double farlandsCustomDivider = 33554432;
        public boolean limitReturnValue = false;
        public int maxNoiseLogarithmValue = 7;

        public static ConfigBuilder getConfigBuilder() {
            ConfigBuilder builder = ConfigBuilder.create().setTitle(Text.translatable("ultimatescaler.options"));
            builder.setGlobalized(true);
            ConfigEntryBuilder entryBuilder = builder.entryBuilder();
            ConfigCategory general = builder.getOrCreateCategory(Text.translatable("ultimatescaler.options.general"));
            config.optionMenuKeyCode = optionMenuKey.getKeyCode().getCode();
            config.optionMenuModifier = optionMenuKey.getModifier();
            KeyCodeEntry optionMenuEntry = entryBuilder.startModifierKeyCodeField(Text.translatable("ultimatescaler.options.general.optionMenuKey"), optionMenuKey)
                    .setDefaultValue(ModifierKeyCode.of(InputUtil.Type.KEYSYM.createFromCode(GLFW.GLFW_KEY_U), Modifier.of(false, true, false)))
                    .build();
            BooleanListEntry showTerrainPosEntry = entryBuilder.startBooleanToggle(Text.translatable("ultimatescaler.options.general.showTerrainPos"), config.showTerrainPos)
                    .setDefaultValue(true)
                    .build();
            general.addEntry(optionMenuEntry);
            general.addEntry(showTerrainPosEntry);

            ConfigCategory worldGen = builder.getOrCreateCategory(Text.translatable("ultimatescaler.options.worldgen"));
            for (int i = 0; i < 3; i++) {
                config.globalOffsetStr[i] = Double.toString(globalOffset[i]);
                config.globalScaleStr[i] = Double.toString(globalScale[i]);
            }

            TextListEntry worldGenHeader = entryBuilder.startTextDescription(Text.translatable("ultimatescaler.options.worldgen.header").styled(s -> s.withBold(true).withColor(Formatting.YELLOW))).build();
            StringListListEntry globalOffsetEntry = entryBuilder.startStrList(Text.translatable("ultimatescaler.options.worldgen.offset.globalOffset"), Arrays.asList(config.globalOffsetStr))
                    .setTooltip(Text.translatable("ultimatescaler.options.parsableDouble.tooltip"))
                    .setDefaultValue(Arrays.asList("0.0", "0.0", "0.0"))
                    .setInsertButtonEnabled(false)
                    .setDeleteButtonEnabled(false)
                    .build();
            StringListListEntry globalScaleEntry = entryBuilder.startStrList(Text.translatable("ultimatescaler.options.worldgen.offset.globalScale"), Arrays.asList(config.globalScaleStr))
                    .setTooltip(Text.translatable("ultimatescaler.options.parsableDouble.tooltip"))
                    .setDefaultValue(Arrays.asList("1.0", "1.0", "1.0"))
                    .setInsertButtonEnabled(false)
                    .setDeleteButtonEnabled(false)
                    .build();
            EnumListEntry<FarlandsPos> farlandsPosEntry = entryBuilder.startEnumSelector(Text.translatable("ultimatescaler.options.worldgen.farlandsPos"), FarlandsPos.class, config.farlandsPos)
                    .setDefaultValue(FarlandsPos.DEFAULT)
                    .setEnumNameProvider((farlandsPos) -> Text.translatable("ultimatescaler.options.worldgen.farlandsPos." + farlandsPos.name()))
                    .setTooltipSupplier((farlandsPos) -> Optional.of(new Text[]{Text.translatable("ultimatescaler.options.worldgen.farlandsPos." + farlandsPos.name() + ".tooltip")}))
                    .build();
            DoubleListEntry farlandsCustomDividerEntry = entryBuilder.startDoubleField(Text.translatable("ultimatescaler.options.worldgen.farlandsCustomDivider"), config.farlandsCustomDivider)
                    .setDefaultValue(33554432)
                    .setMin(0.0)
                    .setTooltip(Text.translatable("ultimatescaler.options.worldgen.farlandsCustomDivider.tooltip"))
                    .setRequirement(Requirement.all(() -> farlandsPosEntry.getValue().equals(FarlandsPos.CUSTOM)))
                    .build();
            BooleanListEntry removeFringeLandsEntry = entryBuilder.startBooleanToggle(Text.translatable("ultimatescaler.options.worldgen.limitReturnValue"), config.limitReturnValue)
                    .setDefaultValue(false)
                    .build();
            IntegerListEntry maxNoiseValueEntry = entryBuilder.startIntField(Text.translatable("ultimatescaler.options.worldgen.maxNoiseLogarithmValue"), config.maxNoiseLogarithmValue)
                    .setDefaultValue(7)
                    .setMax(308)
                    .setTooltip(Text.translatable("ultimatescaler.options.worldgen.maxNoiseLogarithmValue.tooltip"))
                    .setRequirement(Requirement.all(removeFringeLandsEntry::getValue))
                    .build();
            worldGen.addEntry(worldGenHeader);
            worldGen.addEntry(farlandsPosEntry);
            worldGen.addEntry(farlandsCustomDividerEntry);
            worldGen.addEntry(removeFringeLandsEntry);
            worldGen.addEntry(maxNoiseValueEntry);
            worldGen.addEntry(globalOffsetEntry);
            worldGen.addEntry(globalScaleEntry);

            builder.setSavingRunnable(() -> {
                boolean validInput = true;

                try {
                    globalOffset = Arrays.stream(globalOffsetEntry.getValue().toArray(new String[3])).mapToDouble(Double::parseDouble).toArray();
                    globalScale = Arrays.stream(globalScaleEntry.getValue().toArray(new String[3])).mapToDouble(Double::parseDouble).toArray();
                } catch (NumberFormatException e) {
                    validInput = false;
                    for (int i = 0; i < 3; i++) {
                        globalOffset[i] = Double.parseDouble(config.globalOffsetStr[i]);
                        globalScale[i] = Double.parseDouble(config.globalScaleStr[i]);
                    }
                }
                if (validInput) {
                    config.globalOffsetStr = (globalOffsetEntry.getValue().toArray(new String[3]));
                    config.globalScaleStr = (globalScaleEntry.getValue().toArray(new String[3]));
                }
                config.optionMenuKeyCode = optionMenuEntry.getValue().getKeyCode().getCode();
                config.optionMenuModifier = optionMenuEntry.getValue().getModifier();
                optionMenuKey.setKeyCode(InputUtil.Type.KEYSYM.createFromCode(config.optionMenuKeyCode));
                optionMenuKey.setModifier(config.optionMenuModifier);
                config.showTerrainPos = showTerrainPosEntry.getValue();
                config.farlandsPos = farlandsPosEntry.getValue();
                config.farlandsCustomDivider = farlandsCustomDividerEntry.getValue();
                config.limitReturnValue = removeFringeLandsEntry.getValue();
                config.maxNoiseLogarithmValue = maxNoiseValueEntry.getValue();
                holder.save();
            });
            return builder;
        }
    }

    public static void init() {
        UltimateScalerOptions.holder = AutoConfig.register(ConfigImpl.class, Toml4jConfigSerializer::new);
        UltimateScalerOptions.holder.getConfig();
        AutoConfig.getConfigHolder(ConfigImpl.class).registerSaveListener((manager, data) -> ActionResult.SUCCESS);
        AutoConfig.getConfigHolder(ConfigImpl.class).registerLoadListener((manager, newData) -> ActionResult.SUCCESS);
        config = AutoConfig.getConfigHolder(ConfigImpl.class).getConfig();
        globalOffset = Arrays.stream(config.globalOffsetStr).mapToDouble(Double::parseDouble).toArray();
        globalScale = Arrays.stream(config.globalScaleStr).mapToDouble(Double::parseDouble).toArray();
        optionMenuKey = ModifierKeyCode.of(InputUtil.Type.KEYSYM.createFromCode(GLFW.GLFW_KEY_O), Modifier.of(false, true, false));
        optionMenuKey.setKeyCode(InputUtil.Type.KEYSYM.createFromCode(config.optionMenuKeyCode));
        optionMenuKey.setModifier(config.optionMenuModifier);
    }

    public enum FarlandsPos {
        BETA,
        RELEASE,
        DEFAULT,
        REMOVED,
        CUSTOM
    }
}