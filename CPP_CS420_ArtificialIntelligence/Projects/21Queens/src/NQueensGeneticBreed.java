import java.util.Random;

import local_search.GeneticAlgorithmSearch;
import problem.State;

public class NQueensGeneticBreed implements GeneticAlgorithmSearch.Breed {

	public State breed(State s1, State s2) {
		int[] queenLocations1 = ((NQueensState) s1).getQueenLocations();
		int[] queenLocations2 = ((NQueensState) s2).getQueenLocations();
		int len = queenLocations1.length;
		int crossIndex = len/4 + gen.nextInt(len/2);
		int[] newQueenLocations = new int[len];
		for (int i = 0; i < len; i ++) {
			if (i < crossIndex) {
				newQueenLocations[i] = queenLocations1[i];
			} else {
				newQueenLocations[i] = queenLocations2[i];
			}
		}
		return new NQueensState(newQueenLocations);
	}
	
	Random gen = new Random();

}
