package ro.anghel.genetic;

public interface Genotype<T_Gene, T_Genotype extends Genotype<T_Gene, T_Genotype>> {

	T_Gene[] getGenes();

	double getFitness();

	void evaluate();

	void setGenes(T_Gene[] genes);

	void setGene(int index, T_Gene gene);

	T_Genotype duplicate();

}
