package me.inf32768.ultimate_scaler.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.debug.DebugHudLines;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.Nullable;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Locale;

import static me.inf32768.ultimate_scaler.option.UltimateScalerOptions.config;

public class Util {
    private Util() {}

    public static int average(int i , int j) {
        return (i & j) + ((i ^ j) >> 1);
    }

    @SuppressWarnings("unused")
    public static double getDoubleOffsetPos(double pos, Direction.Axis axis) {
        return pos * config.globalBigDecimalScale[axis.ordinal()].doubleValue() + config.globalBigDecimalOffset[axis.ordinal()].doubleValue();
    }

    public static double getDoubleOffsetPos(int pos, Direction.Axis axis) {
        return pos * config.globalBigDecimalScale[axis.ordinal()].doubleValue() + config.globalBigDecimalOffset[axis.ordinal()].doubleValue();
    }

    @SuppressWarnings("unused")
    public static BigInteger getBigIntegerOffsetPos(double pos, Direction.Axis axis) {
        return BigDecimal.valueOf(pos).multiply(config.globalBigDecimalScale[axis.ordinal()]).add(config.globalBigDecimalOffset[axis.ordinal()]).toBigInteger();
    }

    public static BigInteger getBigIntegerOffsetPos(int pos, Direction.Axis axis) {
        return BigDecimal.valueOf(pos).multiply(config.globalBigDecimalScale[axis.ordinal()]).add(config.globalBigDecimalOffset[axis.ordinal()]).toBigInteger();
    }

    public static void appendDebugInfo(@Nullable DebugHudLines lines, @Nullable List<String> list) {
        if (lines == null && list == null) {
            throw new IllegalArgumentException("Lines and list cannot be both null");
        }

        MinecraftClient mc = MinecraftClient.getInstance();
        BlockPos pos = null;

        if (mc.getCameraEntity() != null) {
            pos = mc.getCameraEntity().getBlockPos();
        }

        if (pos == null) {
            return;
        }

        if (config.bigIntegerRewrite) {
            String x = Util.getBigIntegerOffsetPos(pos.getX(), Direction.Axis.X).toString();
            String y = Util.getBigIntegerOffsetPos(pos.getY(), Direction.Axis.Y).toString();
            String z = Util.getBigIntegerOffsetPos(pos.getZ(), Direction.Axis.Z).toString();
            if (lines != null) {
                lines.addLine(String.format(Locale.ROOT, "TerrainXYZ: %s %s %s", x, y, z));
                lines.addLine(String.format(Locale.ROOT, "TerrainXYZ (double): %.0f %.0f %.0f", Double.parseDouble(x), Double.parseDouble(y), Double.parseDouble(z)));
            } else {
                list.add(String.format(Locale.ROOT, "TerrainXYZ: %s %s %s", x, y, z));
                list.add(String.format(Locale.ROOT, "TerrainXYZ (double): %.0f %.0f %.0f", Double.parseDouble(x), Double.parseDouble(y), Double.parseDouble(z)));
            }
        } else {
            double x = Util.getDoubleOffsetPos(pos.getX(), Direction.Axis.X);
            double y = Util.getDoubleOffsetPos(pos.getY(), Direction.Axis.Y);
            double z = Util.getDoubleOffsetPos(pos.getZ(), Direction.Axis.Z);
            if (lines != null) {
                lines.addLine(String.format(Locale.ROOT, "TerrainXYZ: %.0f %.0f %.0f", x, y, z));
            } else {
                list.add(String.format(Locale.ROOT, "TerrainXYZ: %.0f %.0f %.0f", x, y, z));
            }
        }
    }
}
