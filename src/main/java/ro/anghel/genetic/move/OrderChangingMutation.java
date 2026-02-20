package ro.anghel.genetic.move;

import ro.anghel.genetic.Genotype;
import ro.anghel.genetic.util.RandomUtils;

public class OrderChangingMutation<T_Gene, T_Genotype extends Genotype<T_Gene, T_Genotype>>
		implements Move<T_Gene, T_Genotype> {

	private final int maxSplitLength;

	public OrderChangingMutation(final int maxSplitLength) {
		this.maxSplitLength = maxSplitLength;
	}

	public OrderChangingMutation() {
		this(0); // use genes length
	}

	@Override
	public void apply(final T_Genotype genotype,
			final T_Genotype[] population) {
		final T_Gene[] genes = genotype.getGenes();
		final int splitBound = maxSplitLength > 0
				&& maxSplitLength < genes.length ? maxSplitLength
						: genes.length;
		final int idx1 = RandomUtils.nextInt(splitBound);

		// avoid the interchange of the same gene if possible
		int idx2;
		do {
			idx2 = RandomUtils.nextInt(genes.length);
		} while (idx2 == idx1);

		final T_Gene gene1 = genes[idx1];
		final T_Gene gene2 = genes[idx2];

		genotype.setGene(idx1, gene2);
		genotype.setGene(idx2, gene1);
	}

}
