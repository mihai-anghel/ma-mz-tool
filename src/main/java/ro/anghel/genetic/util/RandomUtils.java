package ro.anghel.genetic.util;

import java.util.SplittableRandom;
import java.util.concurrent.atomic.AtomicLong;

public final class RandomUtils {

	private static final AtomicLong ROOT_SEED = new AtomicLong(1L);

	private static final ThreadLocal<SplittableRandom> LOCAL = ThreadLocal
			.withInitial(() -> new SplittableRandom(
					ROOT_SEED.getAndAdd(0x9E3779B97F4A7C15L)));

	private RandomUtils() {
	}

	public static void reseed(final long seed) {
		ROOT_SEED.set(seed);
		LOCAL.remove();
	}

	public static boolean nextBoolean() {
		return LOCAL.get().nextBoolean();
	}

	public static int nextInt(final int bound) {
		return LOCAL.get().nextInt(bound);
	}

	public static int nextInt(final int origin, final int bound) {
		return LOCAL.get().nextInt(origin, bound);
	}

	public static long nextLong(final long bound) {
		return LOCAL.get().nextLong(bound);
	}

	public static long nextLong(final long origin, final long bound) {
		return LOCAL.get().nextLong(origin, bound);
	}

	public static double nextDouble() {
		return LOCAL.get().nextDouble();
	}

	public static double nextDouble(final double bound) {
		return LOCAL.get().nextDouble(bound);
	}

	public static double nextDouble(final double origin, final double bound) {
		return LOCAL.get().nextDouble(origin, bound);
	}

	public static float nextFloat() {
		return LOCAL.get().nextFloat();
	}

	public static float nextFloat(final float bound) {
		return LOCAL.get().nextFloat(bound);
	}

	public static float nextFloat(final float origin, final float bound) {
		return LOCAL.get().nextFloat(origin, bound);
	}

	public static <T> T nextOf(final T[] values) {
		final int idx = nextInt(values.length);
		return values[idx];
	}

	public static <T> void shuffle(final T[] array) {
		for (int i = array.length - 1; i > 0; --i) {
			final int j = nextInt(i + 1);
			final T tmp = array[i];
			array[i] = array[j];
			array[j] = tmp;
		}
	}
}
