import java.util.Random;

import local_search.GeneticAlgorithmSearch;
import problem.State;

public class NQueensGeneticMutate implements GeneticAlgorithmSearch.Mutate {

	public NQueensGeneticMutate(double prob) {
		this.mutateProbability = prob;
	}
	
	public void mutate(State s) {
		int[] queenLocations = ((NQueensState) s).getQueenLocations();
		int len = queenLocations.length;
		for (int i = 0; i < len; i ++) {
			if (gen.nextDouble() < mutateProbability) {
				/*
				int r = gen.nextInt(2);
				if (r == 0) {
					if (queenLocations[i] == len - 1) {
						queenLocations[i] = 0;
					} else {
						queenLocations[i] = queenLocations[i] + 1;
					}
				} else {
					if (queenLocations[i] == 0) {
						queenLocations[i] = len - 1;
					} else {
						queenLocations[i] = queenLocations[i] - 1;
					}
				}
				*/
				queenLocations[i] = gen.nextInt(len);
			}
		}
	}
	
	private double mutateProbability;
	Random gen = new Random();
	
}
