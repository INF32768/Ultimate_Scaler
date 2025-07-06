package me.inf32768.ultimatescaler.util;

import me.inf32768.ultimatescaler.option.UltimateScalerOptions;
import net.minecraft.util.math.Direction;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Util {
    private Util() {}
    @SuppressWarnings("unused")
    public static double getDoubleOffsetPos(double pos, Direction.Axis axis) {
        return pos * UltimateScalerOptions.globalBigDecimalScale[axis.ordinal()].doubleValue() + UltimateScalerOptions.globalBigIntegerOffset[axis.ordinal()].doubleValue();
    }

    public static double getDoubleOffsetPos(int pos, Direction.Axis axis) {
        return pos * UltimateScalerOptions.globalBigDecimalScale[axis.ordinal()].doubleValue() + UltimateScalerOptions.globalBigIntegerOffset[axis.ordinal()].doubleValue();
    }

    @SuppressWarnings("unused")
    public static BigInteger getBigIntegerOffsetPos(double pos, Direction.Axis axis) {
        return BigDecimal.valueOf(pos).multiply(UltimateScalerOptions.globalBigDecimalScale[axis.ordinal()]).add(UltimateScalerOptions.globalBigDecimalOffset[axis.ordinal()]).toBigInteger();
    }

    public static BigInteger getBigIntegerOffsetPos(int pos, Direction.Axis axis) {
        return BigDecimal.valueOf(pos).multiply(UltimateScalerOptions.globalBigDecimalScale[axis.ordinal()]).add(UltimateScalerOptions.globalBigDecimalOffset[axis.ordinal()]).toBigInteger();
    }
}
