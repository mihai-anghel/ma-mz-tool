package ro.anghel.mz.genetic;

import java.util.Arrays;

import ro.anghel.genetic.Genotype;
import ro.anghel.mz.domain.FormationType;
import ro.anghel.mz.domain.Player;
import ro.anghel.mz.domain.Position;

public class FormationGenotype implements Genotype<Player, FormationGenotype> {

	private final FormationType formationType;

	private Player[] players;

	private double fitness;

	public FormationGenotype(final FormationType aFormationType,
			final Player[] aPlayers) {
		formationType = aFormationType;
		players = aPlayers;
	}

	@Override
	public void evaluate() {
		double sumRating = 0;
		int i = 0;
		short cnt = 0;
		for (final Position pos : Position.values()) {
			cnt += formationType.getCount(pos);
			final int minCnt = cnt < players.length ? cnt : players.length;
			for (; i < minCnt; ++i) {
				sumRating += pos.calculator().calculateRating(players[i]);
			}
		}
		fitness = cnt != 0 ? sumRating / cnt : 0;
	}

	@Override
	public double getFitness() {
		return fitness;
	}

	@Override
	public Player[] getGenes() {
		return players;
	}

	@Override
	public void setGenes(final Player[] genes) {
		players = genes;
	}

	@Override
	public void setGene(final int aIndex, final Player aGene) {
		players[aIndex] = aGene;
	}

	public Player[] getPlayers() {
		return getGenes();
	}

	public void setPlayers(final Player[] players) {
		setGenes(players);
	}

	public void setPlayer(final Player[] players) {
		setGenes(players);
	}

	public FormationType getFormationType() {
		return formationType;
	}

	@Override
	public FormationGenotype duplicate() {
		final Player[] playersCopy = Arrays.copyOf(players, players.length);

		final FormationGenotype copyGenotype = new FormationGenotype(
				formationType, playersCopy);
		copyGenotype.fitness = fitness;
		return copyGenotype;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("Fitness = ").append(fitness).append("\n Players = ");

		short i = 0;
		short cnt = 0;
		for (final Position pos : Position.values()) {
			cnt += formationType.getCount(pos);
			for (; i < cnt && i < players.length; ++i) {
				sb.append("\n\t").append(pos.name()).append(" - ")
						.append(players[i]);
			}
		}

		return sb.toString();
	}

}
