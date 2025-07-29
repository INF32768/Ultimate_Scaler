package me.inf32768.ultimate_scaler.commands;

import com.mojang.brigadier.StringReader;
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
                            .executes(context -> (int) calculate(StringArgumentType.getString(context, "originalPos"), DoubleArgumentType.getDouble(context, "scale"), DoubleArgumentType.getDouble(context, "offset"), 0, context))
                            .then(argument("range", DoubleArgumentType.doubleArg())
                                .executes(context -> (int) calculate(StringArgumentType.getString(context, "originalPos"), DoubleArgumentType.getDouble(context, "scale"), DoubleArgumentType.getDouble(context, "offset"), DoubleArgumentType.getDouble(context, "range"), context)))))))));
    }

     public static double calculate(String originalPos, double scale, double offset, double range, CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        if (scale <= 0) {
            throw SCALE_INVALID_EXCEPTION.create();
        }

        if (range < 0) {
            throw RANGE_NEGATIVE_EXCEPTION.create();
        }

         BigDecimal p;
         try {
             p = new BigDecimal(originalPos);
         } catch (NumberFormatException e) {
             throw INVALID_DECIMAL_EXCEPTION.createWithContext(new StringReader(e.getMessage()));
         }
         BigDecimal s = new BigDecimal(scale);
        BigDecimal o = new BigDecimal(offset);

        if (range == 0) {
            range = Double.MAX_VALUE / scale - offset;
        }

         BigDecimal r;
         try {
             r = new BigDecimal(range);
         } catch (NumberFormatException e) {
             throw RANGE_TOO_LARGE_EXCEPTION.createWithContext(new StringReader(e.getMessage()));
         }

         // 初始化二分查找的上下界
        BigInteger low = r.toBigInteger().negate();
        BigInteger high = low.negate(); // 假设一个足够大的上限

        // 使用二分查找来找到满足条件的x的最小值
        while (low.subtract(high).doubleValue() < -1) {
            BigInteger mid = low.add(high).divide(BigInteger.valueOf(2));
            BigDecimal midBigDecimal = new BigDecimal(mid);

            // 计算mid * s + o
            double resultDouble = midBigDecimal.doubleValue() * s.doubleValue() + o.doubleValue();
            BigDecimal result = new BigDecimal(resultDouble);

            // 检查是否满足result >= p
            if (result.compareTo(p) >= 0) {
                high = mid; // mid满足条件，尝试更小的x
            } else {
                low = mid.add(BigInteger.ONE); // mid不满足条件，尝试更大的x
            }
        }

        // 确保low是满足条件的最小值
        BigDecimal lowBigDecimal = new BigDecimal(low);
        BigDecimal result = lowBigDecimal.multiply(s).add(o);

        if (result.compareTo(p) < 0) {
            low = low.add(BigInteger.ONE);
        }
        if (low.compareTo(r.toBigInteger()) > 0 || low.compareTo(r.toBigInteger().negate()) < 0 || result.compareTo(p) < 0) {
            throw NOT_FOUND_EXCEPTION.create();
        }

        BigInteger finalResult = low;
        if (context == null) {
            System.out.println("Located Position: " + finalResult);
        } else {
            context.getSource().sendFeedback(() -> Text.translatable("ultimate_scaler.commands.locate.pos.success", finalResult.toString()), false);
        }
        return finalResult.doubleValue();
    }
}
