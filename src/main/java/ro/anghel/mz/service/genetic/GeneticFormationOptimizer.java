package ro.anghel.mz.service.genetic;

import java.math.BigInteger;
import java.util.LinkedHashSet;
import java.util.Set;

import ro.anghel.genetic.GeneticAlgorithm;
import ro.anghel.genetic.move.Move;
import ro.anghel.genetic.util.RandomUtils;
import ro.anghel.mz.domain.Formation;
import ro.anghel.mz.domain.FormationType;
import ro.anghel.mz.domain.Player;
import ro.anghel.mz.domain.PlayerPosition;
import ro.anghel.mz.domain.Team;
import ro.anghel.mz.genetic.FormationGenotype;
import ro.anghel.mz.service.FormationOptimizer;
import ro.anghel.perm.PermutationGenerator;

public class GeneticFormationOptimizer implements FormationOptimizer {
	private int populationSize = 0;

	private int nrOfGenerations = 0;

	private final Move<Player, FormationGenotype> move;

	public GeneticFormationOptimizer(final int aPopulationSize,
			final int aNrOfGenerations,
			final Move<Player, FormationGenotype> aMove) {
		populationSize = aPopulationSize;
		nrOfGenerations = aNrOfGenerations;
		move = aMove;
	}

	@Override
	public Formation optimize(final Formation aInitialFormation) {
		final PlayerPosition[] starting = aInitialFormation.getStarting();
		final Team team = aInitialFormation.getTeam();
		final Player[] players = team.getNonRestedPlayers();
		final Set<Player> availablePlayers = new LinkedHashSet<>(
				players.length);
		for (final PlayerPosition startingPos : starting) {
			// starting players first
			availablePlayers.add(startingPos.getPlayer());
		}
		for (final Player player : players) {
			// add again all the players, the startion players will remain first
			availablePlayers.add(player);
		}
		final Player[] arrangedPlayers = availablePlayers
				.toArray(new Player[availablePlayers.size()]);
		final FormationType formationType = aInitialFormation
				.getFormationType();

		final GeneticAlgorithm<Player, FormationGenotype> genAlg = new GeneticAlgorithm<>(
				move, generatePopulation(formationType, arrangedPlayers));
		final FormationGenotype best = genAlg.solve(nrOfGenerations);
		return new Formation(formationType, team, best.getPlayers());
	}

	private FormationGenotype[] generatePopulation(
			final FormationType aFormationType, final Player[] aPlayers) {

		final PermutationGenerator permGenerator = new PermutationGenerator(
				aPlayers.length);

		final BigInteger permCnt = permGenerator.getTotalCount();
		final int popSize = permCnt
				.compareTo(BigInteger.valueOf(populationSize)) < 0
						? permCnt.intValue()
						: populationSize;

		final FormationGenotype[] population = new FormationGenotype[popSize];

		// seed with near–optimal solution
		if (popSize > 0) {
			population[0] = new FormationGenotype(aFormationType, aPlayers);
		}

		// create randomized base permutation
		final Player[] randPlayers = aPlayers.clone();
		RandomUtils.shuffle(randPlayers);

		// generate permutations based on shuffled base
		for (int i = 1; i < popSize; ++i) {
			final int[] perm = permGenerator.getNext();
			final Player[] permPlayers = new Player[randPlayers.length];

			for (int j = 0; j < randPlayers.length; ++j) {
				permPlayers[j] = randPlayers[perm[j]];
			}
			population[i] = new FormationGenotype(aFormationType, permPlayers);
		}
		return population;
	}

}
