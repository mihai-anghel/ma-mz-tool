package ro.anghel.perm;

import java.math.BigInteger;

public class PermutationGenerator {

	private final int[] permutation;

	private BigInteger remainingCount;

	private final BigInteger totalCount;

	/**
	 * 
	 * @param number
	 *            - WARNING: Don't make number too large. Recall that the number
	 *            of permutations is a number! which can be very large, even
	 *            when number is as small as 20 -- 20! =
	 *            2,432,902,008,176,640,000 and 21! is too big to fit into a
	 *            Java long, which is why we use BigInteger instead.
	 */
	public PermutationGenerator(final int number) {
		if (number < 1) {
			throw new IllegalArgumentException("Min 1");
		}
		permutation = new int[number];
		totalCount = computeFactorial(number);
		reset();
	}

	public void reset() {
		for (int i = 0; i < permutation.length; ++i) {
			permutation[i] = i;
		}
		remainingCount = new BigInteger(totalCount.toString());
	}

	/**
	 * @return: number of permutations not yet generated
	 */

	public BigInteger getRemainingCount() {
		return remainingCount;
	}

	/**
	 * @return: total number of permutations
	 */

	public BigInteger getTotalCount() {
		return totalCount;
	}

	public boolean hasMore() {
		return remainingCount.compareTo(BigInteger.ZERO) == 1;
	}

	private static BigInteger computeFactorial(final int number) {
		BigInteger fact = BigInteger.ONE;
		for (int i = number; i > 1; --i) {
			fact = fact.multiply(new BigInteger(Integer.toString(i)));
		}
		return fact;
	}

	/**
	 * Generate next permutation (algorithm from Rosen p. 284)
	 */
	public int[] getNext() {

		if (remainingCount.equals(totalCount)) {
			remainingCount = remainingCount.subtract(BigInteger.ONE);
			return permutation;
		}

		// Find largest index j with a[j] < a[j+1]

		int j = permutation.length - 2;
		for (; permutation[j] > permutation[j + 1]; --j) {
		}

		// Find index k such that a[k] is smallest integer
		// greater than a[j] to the right of a[j]

		int k = permutation.length - 1;
		for (; permutation[j] > permutation[k]; --k) {
		}

		// Interchange a[j] and a[k]

		int temp = permutation[k];
		permutation[k] = permutation[j];
		permutation[j] = temp;

		// Put tail end of permutation after j'th position in increasing order
		for (int r = permutation.length - 1, s = j + 1; r > s; --r, ++s) {
			temp = permutation[s];
			permutation[s] = permutation[r];
			permutation[r] = temp;
		}

		remainingCount = remainingCount.subtract(BigInteger.ONE);
		return permutation;

	}

}
