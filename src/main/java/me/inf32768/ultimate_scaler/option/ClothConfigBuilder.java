package me.inf32768.ultimate_scaler.option;

import me.shedaniel.clothconfig2.api.*;
import me.shedaniel.clothconfig2.gui.entries.*;
import me.shedaniel.clothconfig2.impl.builders.SubCategoryBuilder;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.toast.SystemToast;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.lwjgl.glfw.GLFW;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;

import static me.inf32768.ultimate_scaler.option.UltimateScalerOptions.config;

@Environment(EnvType.CLIENT)
public class ClothConfigBuilder {
    @SuppressWarnings("UnstableApiUsage")
    public static ConfigBuilder getConfigBuilder() {
        ConfigBuilder builder = ConfigBuilder.create().setTitle(Text.translatable("ultimate_scaler.options"));
        builder.setGlobalized(true);
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();
        ConfigCategory general = builder.getOrCreateCategory(Text.translatable("ultimate_scaler.options.general"));
        KeyCodeEntry optionMenuEntry = entryBuilder.startModifierKeyCodeField(Text.translatable("ultimate_scaler.options.general.optionMenuKey"), ModifierKeyCode.of(InputUtil.Type.KEYSYM.createFromCode(config.optionMenuKeyCode), Modifier.of(config.optionMenuModifierValue)))
                .setDefaultValue(ModifierKeyCode.of(InputUtil.Type.KEYSYM.createFromCode(GLFW.GLFW_KEY_U), Modifier.of(false, true, false)))
                .build();
        BooleanListEntry showTerrainPosEntry = entryBuilder.startBooleanToggle(Text.translatable("ultimate_scaler.options.general.showTerrainPos"), config.showTerrainPos)
                .setDefaultValue(true)
                .build();
        general.addEntry(optionMenuEntry);
        general.addEntry(showTerrainPosEntry);

        ConfigCategory worldGen = builder.getOrCreateCategory(Text.translatable("ultimate_scaler.options.worldgen"));

        TextListEntry worldGenHeader = entryBuilder.startTextDescription(Text.translatable("ultimate_scaler.options.worldgen.header").styled(s -> s.withBold(true).withColor(Formatting.YELLOW))).build();
        StringListListEntry globalOffsetEntry = entryBuilder.startStrList(Text.translatable("ultimate_scaler.options.worldgen.offset.globalOffset"), Arrays.stream(config.globalBigDecimalOffset).map(BigDecimal::toString).toList())
                .setTooltip(Text.translatable("ultimate_scaler.options.parsableDecimal.tooltip"))
                .setDefaultValue(Arrays.asList("0", "0", "0"))
                .setInsertButtonEnabled(false)
                .setDeleteButtonEnabled(false)
                .build();
        StringListListEntry globalScaleEntry = entryBuilder.startStrList(Text.translatable("ultimate_scaler.options.worldgen.offset.globalScale"), Arrays.stream(config.globalBigDecimalScale).map(BigDecimal::toString).toList())
                .setTooltip(Text.translatable("ultimate_scaler.options.parsableDecimal.tooltip"))
                .setDefaultValue(Arrays.asList("1", "1", "1"))
                .setInsertButtonEnabled(false)
                .setDeleteButtonEnabled(false)
                .build();
        try {
            entryBuilder.startEnumSelector(Text.translatable("ultimate_scaler.options.worldgen.farLandsPos"), UltimateScalerOptions.FarLandsPos.class, config.farLandsPos);
        } catch (NullPointerException e) {
            SystemToast.show(MinecraftClient.getInstance().getToastManager(), new SystemToast.Type(), Text.translatable("ultimate_scaler.options.worldgen.offset.invalidInput"), Text.of("For enum: " + ConfigManager.readString(UltimateScalerOptions.CONFIG_PATH, "farLandsPos")));
            config.farLandsPos = UltimateScalerOptions.FarLandsPos.DEFAULT;
        }
        EnumListEntry<UltimateScalerOptions.FarLandsPos> farLandsPosEntry = entryBuilder.startEnumSelector(Text.translatable("ultimate_scaler.options.worldgen.farLandsPos"), UltimateScalerOptions.FarLandsPos.class, config.farLandsPos)
                .setDefaultValue(UltimateScalerOptions.FarLandsPos.DEFAULT)
                .setEnumNameProvider((farLandsPos) -> Text.translatable("ultimate_scaler.options.worldgen.FarLandsPos." + farLandsPos.name()))
                .setTooltipSupplier((FarLandsPos) -> Optional.of(new Text[]{Text.translatable("ultimate_scaler.options.worldgen.FarLandsPos." + FarLandsPos.name() + ".tooltip")}))
                .build();
        DoubleListEntry maintainPrecisionCustomDivisorEntry = entryBuilder.startDoubleField(Text.translatable("ultimate_scaler.options.worldgen.maintainPrecisionCustomDivisor"), config.maintainPrecisionCustomDivisor)
                .setDefaultValue(33554432)
                .setMin(0.0)
                .setTooltip(Text.translatable("ultimate_scaler.options.worldgen.maintainPrecisionCustomDivisor.tooltip"))
                .setDisplayRequirement(Requirement.all(() -> farLandsPosEntry.getValue().equals(UltimateScalerOptions.FarLandsPos.CUSTOM)))
                .build();
        BooleanListEntry limitReturnValueEntry = entryBuilder.startBooleanToggle(Text.translatable("ultimate_scaler.options.worldgen.limitReturnValue"), config.limitReturnValue)
                .setDefaultValue(false)
                .build();
        IntegerListEntry maxNoiseValueEntry = entryBuilder.startIntField(Text.translatable("ultimate_scaler.options.worldgen.maxNoiseLogarithmValue"), config.maxNoiseLogarithmValue)
                .setDefaultValue(7)
                .setTooltip(Text.translatable("ultimate_scaler.options.worldgen.maxNoiseLogarithmValue.tooltip"))
                .setDisplayRequirement(Requirement.all(limitReturnValueEntry::getValue))
                .build();
        worldGen.addEntry(worldGenHeader);
        worldGen.addEntry(farLandsPosEntry);
        worldGen.addEntry(maintainPrecisionCustomDivisorEntry);
        worldGen.addEntry(limitReturnValueEntry);
        worldGen.addEntry(maxNoiseValueEntry);
        worldGen.addEntry(globalOffsetEntry);
        worldGen.addEntry(globalScaleEntry);

        SubCategoryBuilder experimental = entryBuilder.startSubCategory(Text.translatable("ultimate_scaler.options.worldgen.experimental"));
        TextListEntry experimentalHeader = entryBuilder.startTextDescription(Text.translatable("ultimate_scaler.options.worldgen.experimental.header").styled(s -> s.withBold(true).withColor(Formatting.RED))).build();
        BooleanListEntry bigIntegerRewriteEntry = entryBuilder.startBooleanToggle(Text.translatable("ultimate_scaler.options.worldgen.bigIntegerRewrite"), config.bigIntegerRewrite)
                .setDefaultValue(false)
                .setTooltip(Text.translatable("ultimate_scaler.options.worldgen.bigIntegerRewrite.tooltip.1")
                        .append(Text.translatable("ultimate_scaler.options.worldgen.bigIntegerRewrite.tooltip.2").styled(s -> s.withColor(Formatting.YELLOW)))
                        .append(Text.translatable("ultimate_scaler.options.worldgen.bigIntegerRewrite.tooltip.3"))
                        .append(Text.translatable("ultimate_scaler.options.worldgen.bigIntegerRewrite.tooltip.4").styled(s -> s.withColor(Formatting.GREEN)))
                )
                .build();
        BooleanListEntry simulateEndRingsEntry = entryBuilder.startBooleanToggle(Text.translatable("ultimate_scaler.options.worldgen.simulateEndRings"), config.simulateEndRings)
                .setDefaultValue(false)
                .setTooltip(Text.translatable("ultimate_scaler.options.worldgen.simulateEndRings.tooltip"))
                .setDisplayRequirement(Requirement.all(bigIntegerRewriteEntry::getValue))
                .build();
        BooleanListEntry extraYOffsetEntry = entryBuilder.startBooleanToggle(Text.translatable("ultimate_scaler.options.worldgen.extraYOffset"), config.extraYOffset)
                .setDefaultValue(false)
                .setTooltip(Text.translatable("ultimate_scaler.options.worldgen.extraYOffset.tooltip"))
                .build();
        experimental.add(experimentalHeader);
        experimental.add(bigIntegerRewriteEntry);
        experimental.add(simulateEndRingsEntry);
        experimental.add(extraYOffsetEntry);
        worldGen.addEntry(experimental.build());

        ConfigCategory fix = builder.getOrCreateCategory(Text.translatable("ultimate_scaler.options.fix"));
        TextListEntry fixHeader = entryBuilder.startTextDescription(Text.translatable("ultimate_scaler.options.fix.header").styled(s -> s.withBold(true).withColor(Formatting.YELLOW))).build();
        BooleanListEntry fixChunkGenerationOutOfBoundEntry = entryBuilder.startBooleanToggle(Text.translatable("ultimate_scaler.options.fix.chunkGenerationOutOfBound"), config.fixChunkGenerationOutOfBound)
                .setDefaultValue(true)
                .setTooltip(Text.translatable("ultimate_scaler.options.fix.chunkGenerationOutOfBound.tooltip"))
                .build();
        BooleanListEntry expandDatapackValueRangeEntry = entryBuilder.startBooleanToggle(Text.translatable("ultimate_scaler.options.fix.expandDatapackValueRange"), config.expandDatapackValueRange)
                .setDefaultValue(true)
                .setTooltip(Text.translatable("ultimate_scaler.options.fix.expandDatapackValueRange.tooltip.1").append(Text.translatable("ultimate_scaler.options.fix.expandDatapackValueRange.tooltip.2").styled(s -> s.withColor(Formatting.GOLD))))
                .requireRestart()
                .build();
        fix.addEntry(fixHeader);
        fix.addEntry(fixChunkGenerationOutOfBoundEntry);
        fix.addEntry(expandDatapackValueRangeEntry);

        builder.setSavingRunnable(() -> {
            try {
                config.globalBigDecimalOffset = globalOffsetEntry.getValue().stream().map(BigDecimal::new).toArray(BigDecimal[]::new);
                config.globalBigDecimalScale = globalScaleEntry.getValue().stream().map(BigDecimal::new).toArray(BigDecimal[]::new);
            } catch (NumberFormatException e) {
                SystemToast.show(MinecraftClient.getInstance().getToastManager(), new SystemToast.Type(), Text.translatable("ultimate_scaler.options.worldgen.offset.invalidInput"), Text.of(e.getMessage()));
            }
            config.optionMenuKeyCode = optionMenuEntry.getValue().getKeyCode().getCode();
            config.optionMenuModifierValue = optionMenuEntry.getValue().getModifier().getValue();
            config.showTerrainPos = showTerrainPosEntry.getValue();
            config.farLandsPos = farLandsPosEntry.getValue();
            config.maintainPrecisionCustomDivisor = maintainPrecisionCustomDivisorEntry.getValue();
            config.limitReturnValue = limitReturnValueEntry.getValue();
            config.maxNoiseLogarithmValue = maxNoiseValueEntry.getValue();
            config.extraYOffset = extraYOffsetEntry.getValue();
            config.bigIntegerRewrite = bigIntegerRewriteEntry.getValue();
            config.simulateEndRings = simulateEndRingsEntry.getValue();
            config.fixChunkGenerationOutOfBound = fixChunkGenerationOutOfBoundEntry.getValue();
            config.expandDatapackValueRange = expandDatapackValueRangeEntry.getValue();
            try {
                UltimateScalerOptions.saveConfig();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        return builder;
    }
}
