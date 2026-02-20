package ro.anghel.genetic.move;

import ro.anghel.genetic.Genotype;
import ro.anghel.genetic.util.RandomUtils;

public class ProbabilisticMove<T_Gene, T_Genotype extends Genotype<T_Gene, T_Genotype>>
		implements Move<T_Gene, T_Genotype> {

	private final Move<T_Gene, T_Genotype> delegate;

	private final double probability;

	public ProbabilisticMove(final Move<T_Gene, T_Genotype> delegate,
			final double probability) {
		this.delegate = delegate;
		this.probability = probability;
	}

	public static <T_Gene, T_Genotype extends Genotype<T_Gene, T_Genotype>> ProbabilisticMove<T_Gene, T_Genotype> withProbability(
			final Move<T_Gene, T_Genotype> move, final double probability) {
		return new ProbabilisticMove<>(move, probability);
	}

	@Override
	public void apply(final T_Genotype genotype,
			final T_Genotype[] population) {
		final double random = RandomUtils.nextDouble();
		if (random < probability) {
			delegate.apply(genotype, population);
		}
	}

}
