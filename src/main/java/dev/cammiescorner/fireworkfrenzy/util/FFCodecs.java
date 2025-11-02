package dev.cammiescorner.fireworkfrenzy.util;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;

import java.util.function.Function;

public class FFCodecs {

	public static final Codec<Double> NON_NEGATIVE_DOUBLE = doubleRangeWithMessage(0.0F, Double.MAX_VALUE, value -> "Value must be >= 0: " + value);

	private static Codec<Double> doubleRangeWithMessage(double min, double max, Function<Double, String> errorMessage) {
		return Codec.DOUBLE
			.validate(
				value -> value >= min && value <= max
					? DataResult.success(value)
					: DataResult.error(() -> errorMessage.apply(value))
			);
	}
}
