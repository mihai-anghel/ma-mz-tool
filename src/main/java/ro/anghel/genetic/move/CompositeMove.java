package ro.anghel.genetic.move;

import java.util.Arrays;
import java.util.List;

import ro.anghel.genetic.Genotype;

public class CompositeMove<T_Gene, T_Genotype extends Genotype<T_Gene, T_Genotype>>
		implements Move<T_Gene, T_Genotype> {

	private final List<Move<T_Gene, T_Genotype>> moves;

	@SafeVarargs
	public CompositeMove(final Move<T_Gene, T_Genotype>... moves) {
		if (moves == null || moves.length == 0) {
			throw new IllegalArgumentException("At least one move is required");
		}
		this.moves = Arrays.asList(moves);
	}

	public CompositeMove(final List<Move<T_Gene, T_Genotype>> moves) {
		if (moves == null || moves.isEmpty()) {
			throw new IllegalArgumentException("At least one move is required");
		}
		this.moves = List.copyOf(moves); // immutable copy
	}

	@Override
	public void apply(final T_Genotype genotype,
			final T_Genotype[] population) {
		for (final Move<T_Gene, T_Genotype> move : moves) {
			move.apply(genotype, population);
		}
	}

	public int size() {
		return moves.size();
	}

	public List<Move<T_Gene, T_Genotype>> getMoves() {
		return moves;
	}
}
