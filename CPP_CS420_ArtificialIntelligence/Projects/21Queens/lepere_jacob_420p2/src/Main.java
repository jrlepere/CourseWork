import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import local_search.GeneticAlgorithmSearch;
import local_search.LimitedRandomRestartHillClimb;
import local_search.LocalSearchResult;
import problem.Problem;
import search.IResultObject;

public class Main {

	public static void main(String[] args) {
		
		Problem p = new NQueensProblem(N);
		Scanner in = new Scanner(System.in);
		
		printHeading();
		
		while (true) {
			System.out.print("->");
			String line = in.nextLine().trim();
			if (line.equals("RRHC")) {
				printRandRestSolutions(p, 3);
			} else if (line.equals("GEN")) {
				printGenSolutions(p, 3);
			} else if (line.equals("A")) {
				algorithmAnalysis(p);
			} else if (line.equals("Q")) {
				break;
			} else {
				System.out.println("Incorrect Input");
			}
		}
		
		in.close();
		
	}
	
	/**
	 * Program output heading
	 */
	private static void printHeading() {
		System.out.println("21 QUEENS PROJECT");
		System.out.println("OPTIONS:");
		System.out.println("[RRHC] := Random Restart Hill Climb x3 Solutions");
		System.out.println("[GEN] := Genetics Algorithm x3 Solutions");
		System.out.println("[A] := Analysis x100 RRHC & GEN");
		System.out.println("[Q] := Quit");
	}
	
	private static void printRandRestSolutions(Problem p, int numSols) {
		int solutions = 0;
		while (solutions < numSols) {
			LimitedRandomRestartHillClimb randRestart = new LimitedRandomRestartHillClimb(OBJECTIVE_FUNCTION, MAX_RESTARTS);
			IResultObject res = randRestart.execute(p);
			NQueensState goalState = (NQueensState) res.getObject(LocalSearchResult.GOAL_STATE);
			if (p.isGoalState(goalState)) {
				System.out.println(goalState);
				solutions ++;
			}
		}
	}
	
	private static void printGenSolutions(Problem p, int numSols) {
		int populationSize = 100;
		double mutateProbability = (1.00/N);
		int maxGenerations = 5000;
		NQueensGeneticMutate mutateFunction = new NQueensGeneticMutate(mutateProbability);
		int solutions = 0;
		while (solutions < numSols) {
			GeneticAlgorithmSearch genetic = new GeneticAlgorithmSearch(OBJECTIVE_FUNCTION, BREED_FUNCTION, mutateFunction, populationSize, GENETIC_FITNESS_GOAL, maxGenerations);
			IResultObject res = genetic.execute(p);
			NQueensState goalState = (NQueensState) res.getObject(LocalSearchResult.GOAL_STATE);
			if (p.isGoalState(goalState)) {
				System.out.println(goalState);
				solutions ++;
			}
		}
	}
	
	/**
	 * Print algorithm analysis
	 * @param p the problem
	 */
	private static void algorithmAnalysis(Problem p) {
		final int NUMBER_OF_ITERATIONS = 1000;
		
		System.out.println("");
		System.out.println("RRHC");
		System.out.println("FOUND,SEARCH_COST,TIME(ns)");
		getDataRandomRestart(p, NUMBER_OF_ITERATIONS);
		
		System.out.println("");
		
		System.out.println("GEN");
		System.out.println("FOUND,SEARCH_COST,TIME(ns)");
		System.out.println("");
		
		int populationSize = 100;
		double mutateProbability = (1.00/N);
		int maxGenerations = 5000;
		getDataGenetic(p, NUMBER_OF_ITERATIONS, populationSize, mutateProbability, maxGenerations);
		
	}
	
	/**
	 * Executes RandomRestartHillClimb on Problem p, numberOfIterations times.
	 * @param p the Problem to solve.
	 * @param numberOfIterations the number of iterations
	 * @return a list of DataEntrys
	 */
	private static void getDataRandomRestart(Problem p, int numberOfIterations) {
		for (int i = 0; i < numberOfIterations; i ++) {
			LimitedRandomRestartHillClimb randRestart = new LimitedRandomRestartHillClimb(OBJECTIVE_FUNCTION, MAX_RESTARTS);
			long start = System.nanoTime();
			IResultObject res = randRestart.execute(p);
			long end = System.nanoTime();
			NQueensState goalState = (NQueensState) res.getObject(LocalSearchResult.GOAL_STATE);
			Integer searchCost = (Integer) res.getObject(LocalSearchResult.SEARCH_COST);
			DataEntry de = new DataEntry(p.isGoalState(goalState), searchCost, end - start);
			System.out.println(de);
		}
	}
	
	/**
	 * Executes the GeneticsAlgorithm on Problem p, numberOfIterations times.
	 * @param p the Problem to solve
	 * @param numberOfIterations the number of iterations
	 * @param populationSize the size of the population
	 * @param mutateProbability the probability of mutation
	 * @param maxGenerations the max number of generations allowed
	 * @return a list of DataEntrys
	 */
	private static void getDataGenetic(Problem p, int numberOfIterations, int populationSize, double mutateProbability, int maxGenerations) {
		NQueensGeneticMutate mutateFunction = new NQueensGeneticMutate(mutateProbability);
		for (int i = 0; i < numberOfIterations; i ++) {
			GeneticAlgorithmSearch genetic = new GeneticAlgorithmSearch(OBJECTIVE_FUNCTION, BREED_FUNCTION, mutateFunction, populationSize, GENETIC_FITNESS_GOAL, maxGenerations);
			long start = System.nanoTime();
			IResultObject res = genetic.execute(p);
			long end = System.nanoTime();
			NQueensState goalState = (NQueensState) res.getObject(LocalSearchResult.GOAL_STATE);
			Integer searchCost = (Integer) res.getObject(LocalSearchResult.SEARCH_COST);
			DataEntry de = new DataEntry(p.isGoalState(goalState), searchCost, end - start);
			System.out.println(de);
		}
	}
	
	/**
	 * A class to hold data from local search algorithm execution.
	 * @author JLepere2
	 * @data 02/10/2018
	 */
	public static class DataEntry {
		public DataEntry(boolean solutionFound, int searchCost, long executionTime) {
			this.solutionFound = solutionFound;
			this.searchCost = searchCost;
			this.executionTime = executionTime;
		}
		public String toString() {
			return solutionFound + "," + searchCost + "," + executionTime;
		}
		private boolean solutionFound;
		private int searchCost;
		private long executionTime;
	}
	
	private static final int N = 21;
	private static final int MAX_RESTARTS = 25;
	private static final NQueensObjectiveFunction OBJECTIVE_FUNCTION = new NQueensObjectiveFunction(N);
	private static final NQueensGeneticBreed BREED_FUNCTION = new NQueensGeneticBreed();
	private static final int GENETIC_FITNESS_GOAL = ((N-1)*(N))/2;
	
}
