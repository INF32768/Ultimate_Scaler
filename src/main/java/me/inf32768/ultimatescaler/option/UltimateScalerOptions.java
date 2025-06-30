package me.inf32768.ultimatescaler.option;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.ConfigHolder;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import me.shedaniel.clothconfig2.api.*;
import me.shedaniel.clothconfig2.gui.entries.*;
import me.shedaniel.clothconfig2.impl.builders.SubCategoryBuilder;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.toast.SystemToast;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import org.lwjgl.glfw.GLFW;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Optional;

public class UltimateScalerOptions {
    public static ConfigHolder<ConfigImpl> holder;
    public static ConfigImpl config;
    public static BigInteger[] globalIntegerOffset = {BigInteger.ZERO, BigInteger.ZERO, BigInteger.ZERO};
    public static BigInteger[] globalIntegerScale = {BigInteger.ONE, BigInteger.ONE, BigInteger.ONE};
    public static int lastDigitOfOffsetX;
    public static int lastDigitOfOffsetZ;
    public static double scaleXFloatPart;
    public static double scaleZFloatPart;
    public static double[] globalScale = {1.0, 1.0, 1.0};
    public static ModifierKeyCode optionMenuKey;

    @Config(name = "ultimatescaler")
    public static class ConfigImpl implements ConfigData {

        private String[] globalOffsetStr = {""};
        private String[] globalScaleStr = {"1.0", "1.0", "1.0"};
        private String[] globalIntegerOffsetStr = {"0", "0", "0"};
        private int optionMenuKeyCode = GLFW.GLFW_KEY_U;
        private Modifier optionMenuModifier = Modifier.of(false, true, false);
        public boolean showTerrainPos = true;
        public FarlandsPos farlandsPos = FarlandsPos.DEFAULT;
        public double farlandsCustomDivider = 33554432;
        public boolean limitReturnValue = false;
        public int maxNoiseLogarithmValue = 7;
        public boolean bigIntegerRewrite = false;
        public boolean simulateEndRings = false;

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
                config.globalIntegerOffsetStr[i] = globalIntegerOffset[i].toString();
                config.globalScaleStr[i] = Double.toString(globalScale[i]);
            }

            TextListEntry worldGenHeader = entryBuilder.startTextDescription(Text.translatable("ultimatescaler.options.worldgen.header").styled(s -> s.withBold(true).withColor(Formatting.YELLOW))).build();
            StringListListEntry globalOffsetEntry = entryBuilder.startStrList(Text.translatable("ultimatescaler.options.worldgen.offset.globalOffset"), Arrays.asList(config.globalIntegerOffsetStr))
                    .setTooltip(Text.translatable("ultimatescaler.options.parsableInt.tooltip"))
                    .setDefaultValue(Arrays.asList("0", "0", "0"))
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

            SubCategoryBuilder experimental = entryBuilder.startSubCategory(Text.translatable("ultimatescaler.options.worldgen.experimental"));
            TextListEntry experimentalHeader = entryBuilder.startTextDescription(Text.translatable("ultimatescaler.options.worldgen.experimental.header").styled(s -> s.withBold(true).withColor(Formatting.RED))).build();
            BooleanListEntry bigIntegerRewriteEntry = entryBuilder.startBooleanToggle(Text.translatable("ultimatescaler.options.worldgen.bigIntegerRewrite"), config.bigIntegerRewrite)
                    .setDefaultValue(false)
                    .setTooltip(Text.translatable("ultimatescaler.options.worldgen.bigIntegerRewrite.tooltip.1")
                            .append(Text.translatable("ultimatescaler.options.worldgen.bigIntegerRewrite.tooltip.2").styled(s -> s.withColor(Formatting.YELLOW)))
                            .append(Text.translatable("ultimatescaler.options.worldgen.bigIntegerRewrite.tooltip.3").styled(s -> s.withColor(Formatting.GOLD)))
                            .append(Text.translatable("ultimatescaler.options.worldgen.bigIntegerRewrite.tooltip.4"))
                            .append(Text.translatable("ultimatescaler.options.worldgen.bigIntegerRewrite.tooltip.5").styled(s -> s.withColor(Formatting.GREEN)))
                    )
                    .build();
            BooleanListEntry simulateEndRingsEntry = entryBuilder.startBooleanToggle(Text.translatable("ultimatescaler.options.worldgen.simulateEndRings"), config.simulateEndRings)
                    .setDefaultValue(false)
                    .setTooltip(Text.translatable("ultimatescaler.options.worldgen.simulateEndRings.tooltip"))
                    .setRequirement(Requirement.all(bigIntegerRewriteEntry::getValue))
                    .build();
            experimental.add(experimentalHeader);
            experimental.add(bigIntegerRewriteEntry);
            experimental.add(simulateEndRingsEntry);
            worldGen.addEntry(experimental.build());

            builder.setSavingRunnable(() -> {
                boolean validInput = true;

                try {
                    globalIntegerOffset = Arrays.stream(globalOffsetEntry.getValue().toArray(new String[3])).map(BigInteger::new).toArray(BigInteger[]::new);
                    globalScale = Arrays.stream(globalScaleEntry.getValue().toArray(new String[3])).mapToDouble(Double::parseDouble).toArray();
                } catch (NumberFormatException e) {
                    validInput = false;
                    SystemToast.show(MinecraftClient.getInstance().getToastManager(), new SystemToast.Type(), Text.translatable("ultimatescaler.options.worldgen.offset.invalidInput"), Text.of(e.getMessage()));
                    for (int i = 0; i < 3; i++) {
                        globalIntegerOffset[i] = new BigInteger(config.globalIntegerOffsetStr[i]);
                        globalScale[i] = Double.parseDouble(config.globalScaleStr[i]);
                    }
                }
                if (validInput) {
                    config.globalIntegerOffsetStr = (globalOffsetEntry.getValue().toArray(new String[3]));
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
                config.bigIntegerRewrite = bigIntegerRewriteEntry.getValue();
                config.simulateEndRings = simulateEndRingsEntry.getValue();
                for (int i = 0; i < 3; i++) {
                    globalIntegerScale[i] = BigDecimal.valueOf(globalScale[i]).toBigInteger();
                }
                lastDigitOfOffsetX = globalIntegerOffset[0].mod(BigInteger.TEN).intValue();
                lastDigitOfOffsetZ = globalIntegerOffset[2].mod(BigInteger.TEN).intValue();
                scaleXFloatPart = globalScale[0] % 10;
                scaleZFloatPart = globalScale[2] % 10;
                holder.save();
            });
            return builder;
        }
    }

    static {
        holder = AutoConfig.register(ConfigImpl.class, Toml4jConfigSerializer::new);
        holder.getConfig();
        AutoConfig.getConfigHolder(ConfigImpl.class).registerSaveListener((manager, data) -> ActionResult.SUCCESS);
        AutoConfig.getConfigHolder(ConfigImpl.class).registerLoadListener((manager, newData) -> ActionResult.SUCCESS);
        config = AutoConfig.getConfigHolder(ConfigImpl.class).getConfig();
        if (!Arrays.equals(config.globalOffsetStr, new String[]{""})) {
            for (int i = 0; i < config.globalOffsetStr.length; i++) {
                config.globalIntegerOffsetStr[i] = String.valueOf(Math.floor(Double.parseDouble(config.globalOffsetStr[i])));
            }
            config.globalOffsetStr = new String[]{""};
        }
        globalIntegerOffset = Arrays.stream(config.globalIntegerOffsetStr).map(BigInteger::new).toArray(BigInteger[]::new);
        globalScale = Arrays.stream(config.globalScaleStr).mapToDouble(Double::parseDouble).toArray();
        lastDigitOfOffsetX = globalIntegerOffset[0].mod(BigInteger.TEN).intValue();
        lastDigitOfOffsetZ = globalIntegerOffset[2].mod(BigInteger.TEN).intValue();
        scaleXFloatPart = globalScale[0] % 10;
        scaleZFloatPart = globalScale[2] % 10;
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