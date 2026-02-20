package ro.anghel.genetic.move;

import ro.anghel.genetic.Genotype;

public interface Move<T_Gene, T_Genotype extends Genotype<T_Gene, T_Genotype>> {
	void apply(T_Genotype genotype, T_Genotype[] population);
}
