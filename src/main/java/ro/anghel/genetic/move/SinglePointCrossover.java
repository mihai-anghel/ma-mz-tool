package ro.anghel.genetic.move;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

import ro.anghel.genetic.Genotype;
import ro.anghel.genetic.util.RandomUtils;

public class SinglePointCrossover<T_Gene, T_Genotype extends Genotype<T_Gene, T_Genotype>>
		implements Move<T_Gene, T_Genotype> {

	private final int maxSplitLength;

	public SinglePointCrossover(final int maxSplitLength) {
		this.maxSplitLength = maxSplitLength;
	}

	public SinglePointCrossover() {
		this(0); // use genes length
	}

	@Override
	public void apply(final T_Genotype genotype,
			final T_Genotype[] population) {
		if (population.length < 2) {
			return;
		}
		// pick another randomly
		T_Genotype other;
		do {
			other = RandomUtils.nextOf(population);
		} while (other == genotype);

		crossover(genotype, other);
	}

	private void crossover(final T_Genotype genotype1,
			final T_Genotype genotype2) {
		final T_Gene[] genes1 = genotype1.getGenes();
		final T_Gene[] genes2 = genotype2.getGenes();
		final int geneCount = genes1.length;

		// determine split point
		final int splitBound = maxSplitLength > 0 && maxSplitLength <= geneCount
				? maxSplitLength
				: geneCount;
		final int split = RandomUtils.nextInt(splitBound);

		// swap pools contain all genes from the other parent
		// we will remove any genes already fixed in the first split section
		final Deque<T_Gene> swap1 = new LinkedList<>(Arrays.asList(genes2));
		final Deque<T_Gene> swap2 = new LinkedList<>(Arrays.asList(genes1));

		// initialize new arrays from sources, including unchanged genes too
		final T_Gene[] newGenes1 = Arrays.copyOf(genes1, geneCount);
		final T_Gene[] newGenes2 = Arrays.copyOf(genes2, geneCount);

		// remove genes already copied in the first part
		for (int i = 0; i < split; i++) {
			swap1.remove(newGenes1[i]);
			swap2.remove(newGenes2[i]);
		}

		// fill remaining positions with remaning part
		for (int i = split; i < geneCount; i++) {
			newGenes1[i] = swap1.removeFirst();
			newGenes2[i] = swap2.removeFirst();
		}

		// write back new genes
		genotype1.setGenes(newGenes1);
		genotype2.setGenes(newGenes2);
	}
}
