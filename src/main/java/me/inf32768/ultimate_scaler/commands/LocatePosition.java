package me.inf32768.ultimate_scaler.commands;

import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class LocatePosition {
    private static final SimpleCommandExceptionType INVALID_DECIMAL_EXCEPTION = new SimpleCommandExceptionType(Text.translatable("ultimate_scaler.commands.locate.pos.invalid_decimal"));
    private static final SimpleCommandExceptionType SCALE_INVALID_EXCEPTION = new SimpleCommandExceptionType(Text.translatable("ultimate_scaler.commands.locate.pos.scale_invalid"));
    private static final SimpleCommandExceptionType NOT_FOUND_EXCEPTION = new SimpleCommandExceptionType(Text.translatable("ultimate_scaler.commands.locate.pos.not_found"));
    private static final SimpleCommandExceptionType RANGE_NEGATIVE_EXCEPTION = new SimpleCommandExceptionType(Text.translatable("ultimate_scaler.commands.locate.pos.range_negative"));
    private static final SimpleCommandExceptionType RANGE_TOO_LARGE_EXCEPTION = new SimpleCommandExceptionType(Text.translatable("ultimate_scaler.commands.locate.pos.range_too_large"));
    public static void init() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(literal("locate")
            .then(literal("pos")
                .then(argument("originalPos", StringArgumentType.string())
                    .then(argument("scale", DoubleArgumentType.doubleArg())
                        .then(argument("offset", DoubleArgumentType.doubleArg())
                            .executes(context -> (int) calculate(StringArgumentType.getString(context, "originalPos"), DoubleArgumentType.getDouble(context, "scale"), DoubleArgumentType.getDouble(context, "offset"), "0", context))
                            .then(argument("range", StringArgumentType.string())
                                .executes(context -> (int) calculate(StringArgumentType.getString(context, "originalPos"), DoubleArgumentType.getDouble(context, "scale"), DoubleArgumentType.getDouble(context, "offset"), StringArgumentType.getString(context, "range"), context)))))))));
    }

    private static final BigInteger TWO = BigInteger.valueOf(2);
    private static final BigInteger MAX_ITERATIONS = BigInteger.valueOf(100000);

    public static double calculate(String originalPos, double scale, double offset, String rangeArg,
                                   CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        // 参数校验
        if (scale <= 0) throw SCALE_INVALID_EXCEPTION.create();

        // 精确数值转换
        BigDecimal originalPosBigDecimal = parseBigDecimal(originalPos);
        BigDecimal scaleBigDecimal = new BigDecimal(scale);
        BigDecimal offsetBigDecimal = new BigDecimal(offset);
        BigDecimal searchRange = parseRange(rangeArg, originalPosBigDecimal, scaleBigDecimal, offsetBigDecimal);

        // 二分查找逻辑
        BigInteger[] result = binarySearch(originalPosBigDecimal, scaleBigDecimal, offsetBigDecimal, searchRange);

        // 结果处理
        return handleResult(result, context);
    }

    private static BigDecimal parseRange(String rangeArg, BigDecimal target, BigDecimal scale, BigDecimal offset)
            throws CommandSyntaxException {
        BigDecimal range = parseBigDecimal(rangeArg);

        if (range.compareTo(BigDecimal.ZERO) == 0) {
            return new BigDecimal(Double.MAX_VALUE)
                    .divide(scale, 100, RoundingMode.HALF_UP)
                    .subtract(offset);
        }

        if (range.compareTo(BigDecimal.ZERO) < 0) throw RANGE_NEGATIVE_EXCEPTION.create();
        if (range.compareTo(new BigDecimal("1E+100")) > 0) throw RANGE_TOO_LARGE_EXCEPTION.create();

        return range;
    }

    private static BigDecimal parseBigDecimal(String rangeArg) throws CommandSyntaxException {
        try {
            return new BigDecimal(rangeArg);
        } catch (Exception e) {
            throw LocatePosition.INVALID_DECIMAL_EXCEPTION.create();
        }
    }

    private static BigInteger[] binarySearch(BigDecimal target, BigDecimal scale, BigDecimal offset, BigDecimal range)
            throws CommandSyntaxException {
        BigInteger low = range.negate().toBigInteger();
        BigInteger high = range.toBigInteger();
        BigInteger iterations = BigInteger.ZERO;

        while (high.subtract(low).compareTo(BigInteger.ONE) > 0) {
            if (iterations.compareTo(MAX_ITERATIONS) > 0) {
                throw RANGE_TOO_LARGE_EXCEPTION.create();
            }

            BigInteger mid = low.add(high).divide(TWO);
            BigDecimal midValue = new BigDecimal(mid)
                    .multiply(scale)
                    .add(offset)
                    .setScale(target.scale(), RoundingMode.HALF_UP);

            if (midValue.compareTo(target) >= 0) {
                high = mid;
            } else {
                low = mid;
            }

            iterations = iterations.add(BigInteger.ONE);
        }

        return new BigInteger[]{low, high};
    }

    private static double handleResult(BigInteger[] result, CommandContext<ServerCommandSource> context)
            throws CommandSyntaxException {
        // 验证最终结果
        if (result[1].compareTo(result[0].add(BigInteger.ONE)) != 0) {
            throw NOT_FOUND_EXCEPTION.create();
        }

        // 反馈结果
        if (context != null) {
            context.getSource().sendFeedback(
                    () -> Text.translatable("ultimate_scaler.commands.locate.pos.success", result[1].toString()),
                    false
            );
        }
        return result[1].doubleValue();
    }
}
