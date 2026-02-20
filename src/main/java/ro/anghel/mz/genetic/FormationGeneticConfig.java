package ro.anghel.mz.genetic;

import ro.anghel.genetic.move.CompositeMove;
import ro.anghel.genetic.move.Move;
import ro.anghel.genetic.move.OrderChangingMutation;
import ro.anghel.genetic.move.ProbabilisticMove;
import ro.anghel.genetic.move.SinglePointCrossover;
import ro.anghel.mz.domain.Player;
import ro.anghel.mz.service.genetic.GeneticFormationOptimizer;

public final class FormationGeneticConfig {

	private static final int POPULATION_SIZE = 350;
	private static final int NR_OF_GENERATIONS = 2500;

	private static final double CROSSOVER_PROBABILITY = 0.2;
	private static final double MUTATION_PROBABILITY = 0.25;

	private static final Move<Player, FormationGenotype> CROSSOVER = new SinglePointCrossover<>(
			0);
	private static final Move<Player, FormationGenotype> MUTATION = new OrderChangingMutation<>(
			0);

	private static final Move<Player, FormationGenotype> PROBABILISTIC_CROSSOVER = ProbabilisticMove
			.withProbability(CROSSOVER, CROSSOVER_PROBABILITY);

	private static final Move<Player, FormationGenotype> PROBABILISTIC_MUTATION = ProbabilisticMove
			.withProbability(MUTATION, MUTATION_PROBABILITY);

	private static final Move<Player, FormationGenotype> MOVE = new CompositeMove<>(
			PROBABILISTIC_CROSSOVER, PROBABILISTIC_MUTATION);

	public static final GeneticFormationOptimizer FORMATION_OPTIMIZER = new GeneticFormationOptimizer(
			POPULATION_SIZE, NR_OF_GENERATIONS, MOVE);

	private FormationGeneticConfig() {
	} // prevent instantiation
}
