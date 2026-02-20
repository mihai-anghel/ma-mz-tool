package ro.anghel.genetic;

import ro.anghel.genetic.move.Move;
import ro.anghel.genetic.util.RandomUtils;

public class GeneticAlgorithm<T_Gene, T_Genotype extends Genotype<T_Gene, T_Genotype>> {

	private final Move<T_Gene, T_Genotype> move;
	private final T_Genotype[] population;

	private T_Genotype bestMember;

	public GeneticAlgorithm(final Move<T_Gene, T_Genotype> move,
			final T_Genotype[] population) {
		this.population = population;
		this.move = move;
	}

	public T_Genotype solve(final int nrOfGenerations) {
		int generation = 0;
		evaluateAndKeepBest();
		/*
		 * double bestPrev = bestMember.getFitness();
		 * System.out.println("Generation[" + generation + "].BestMember: " +
		 * bestMember);
		 */
		while (generation < nrOfGenerations) {
			++generation;
			select();

			applyMoves();
			evaluateAndElitist();
			/*
			 * if (bestPrev != bestMember.getFitness()) {
			 * System.out.println("Generation[" + generation + "].BestMember: "
			 * + bestMember); bestPrev = bestMember.getFitness(); }
			 */

		}
		return bestMember;
	}

	private void applyMoves() {
		for (final T_Genotype genotype : population) {
			move.apply(genotype, population);
		}
	}

	private void evaluateAndKeepBest() {
		T_Genotype best = population[0];
		best.evaluate();
		double bestFitness = best.getFitness();

		for (int i = 1; i < population.length; i++) {
			final T_Genotype genotype = population[i];
			genotype.evaluate();
			final double f = genotype.getFitness();
			if (f > bestFitness) {
				bestFitness = f;
				best = genotype;
			}
		}
		bestMember = best.duplicate();
	}

	private void select() {
		final double[] cumFitness = cumFitness();
		for (int i = 0; i < population.length; i++) {
			final double random = RandomUtils.nextDouble();

			int idx = 0;
			while (idx < population.length - 1 && random >= cumFitness[idx]) {
				idx++;
			}
			population[i] = population[idx].duplicate();
		}
	}

	private double[] cumFitness() {
		final double sum = sumFitness();
		final double[] cumFitness = new double[population.length];
		if (sum != 0) {
			double acc = 0;
			for (int i = 0; i < population.length; i++) {
				acc += population[i].getFitness() / sum;
				cumFitness[i] = acc;
			}
		}
		return cumFitness;
	}

	private double sumFitness() {
		double sum = 0;
		for (final T_Genotype genotype : population) {
			sum += genotype.getFitness();
		}
		return sum;
	}

	private void evaluateAndElitist() {

		T_Genotype best = population[0];
		T_Genotype worst = best;

		best.evaluate();
		double bestFitness = best.getFitness();
		double worstFitness = bestFitness;

		for (int i = 1; i < population.length; i++) {
			final T_Genotype genotype = population[i];
			genotype.evaluate();
			final double fitness = genotype.getFitness();

			if (fitness > bestFitness) {
				bestFitness = fitness;
				best = genotype;
			}
			if (fitness < worstFitness) {
				worstFitness = fitness;
				worst = genotype;
			}
		}

		if (bestFitness > bestMember.getFitness()) {
			bestMember = best.duplicate();
		} else {
			// replace worst with elite
			for (int i = 0; i < population.length; i++) {
				if (population[i] == worst) {
					population[i] = bestMember.duplicate();
					break;
				}
			}
		}
	}
}
