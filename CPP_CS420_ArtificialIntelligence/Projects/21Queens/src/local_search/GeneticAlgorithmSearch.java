package local_search;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

import problem.Problem;
import problem.State;
import search.IResultObject;
import search.LocalSearch;

/**
 * Implementation for the Genetics Algorithm
 * @author JLepere2
 * @date 02/07/2018
 */
public class GeneticAlgorithmSearch extends LocalSearch {

	/**
	 * Creates a Genetics Algorithm object.
	 * @param fun the objective function to evaluate the states
	 * @param sbsConversion an object to convert states to bit streams and bit streams to states
	 * @param populationSize the size of the population
	 * @param mutateProbability the probability of mutation (0.0,1.0)
	 * @param fitnessGoal the fitness goal to reach
	 * @param maxGenerations the max generations
	 */
	public GeneticAlgorithmSearch(ObjectiveFunction fun, Breed breedFunction, Mutate mutateFunction, int populationSize, int fitnessGoal, int maxGenerations) {
		super(fun);
		this.breedFunction = breedFunction;
		this.mutateFunction = mutateFunction;
		this.populationSize = populationSize;
		this.fitnessGoal = fitnessGoal;
		this.maxGenerations = maxGenerations;
		this.searchCost = 0;
	}

	public IResultObject execute(Problem problem) {
		
		// --- INITIALIZATION --- //
		Population population = new Population(populationSize);
		for (int i = 0; i < populationSize; i ++) {
			population.addIndividual(new Individual(problem.getInitialState()));
			searchCost ++;
		}
		
		/*
		 * GENERATION LOOPS 
		 *  - Stop when a OR b
		 *     a) fittestIndividual is sufficient
		 *     b) max generations has been reached 
		 */
		int generations = 0;
		while (population.fittestIndividual.fitnessScore < fitnessGoal && generations < maxGenerations) {
			
			// -- NEW POPULATION -- //
			Population newPopulation = new Population(populationSize);
			
			// -- POPULATE NEW POPULATION -- //
			for (int i = 0; i < populationSize; i ++) {
				
				// -- GET PARENTS -- //
				Individual parent1 = population.getParent();
				Individual parent2 = population.getParent();
				
				// -- BREED PARENTS -- //
				Individual child = population.breed(parent1, parent2);
				
				// -- MUTATE CHILD -- //
				population.mutate(child);
				
				// -- ADD CHILD TO POPULATION -- //
				newPopulation.addIndividual(child);
				searchCost ++;
			}
			
			// -- UPDATE -- //
			population = newPopulation;
			generations ++;
		}
		
		return new LocalSearchResult(population.fittestIndividual.state, searchCost);
	}
	
	/**
	 * An Individual for the population
	 * @author JLepere2
	 * @date 02/07/2018
	 */
	public class Individual {
		
		/**
		 * Creates an Individual with an internal state
		 * @param s the state
		 */
		public Individual(State s) {
			this.state = s;
			this.fitnessScore = function.execute(s);
		}
		
		/**
		 * Gets the state for this individual.
		 * @return the state
		 */
		public State getState() {
			return state;
		}
		
		public String toString() {
			return "" + fitnessScore;
		}
		
		private State state;
		private int fitnessScore;
	}
	
	/**
	 * A Population class for the genetics algorithm
	 * @author JLepere2
	 * @date 02/07/2018
	 */
	private class Population {
		
		/**
		 * Creates a new Population.
		 * @param populationSize the population size
		 */
		public Population(int populationSize) {
			this.population = new PriorityQueue<>(new Comparator<Individual>() {
				public int compare(Individual o1, Individual o2) {
					return o2.fitnessScore - o1.fitnessScore;
				}
			});
			this.currentSize = 0;
			this.totalFitnessScore = 0;
			this.fittestIndividual = null;
		}
		
		/**
		 * Adds an individual to the Population.
		 * @param i the individual
		 */
		public void addIndividual(Individual i) {
			population.add(i);
			currentSize += 1;
			totalFitnessScore += i.fitnessScore;
			if (fittestIndividual == null) {
				fittestIndividual = i;
			} else {
				if (i.fitnessScore > fittestIndividual.fitnessScore) {
					fittestIndividual = i;
				}
			}
		}
		
		/**
		 * Gets a parent Individual for breeding giving priority to those individuals with a higher fitness function.
		 * @return an Individual for breeding.
		 */
		private Individual getParent() {
			double r = gen.nextDouble();
			int i = 1;
			for (Individual ind : population) {
				double v = 1.00 / Math.pow(2, i);
				if (r > v) {
					return ind;
				}
				i += 1;
			}
			return null;
		}
		
		/**
		 * Breeds two individuals to create a child.
		 * @param i1 the first individual
		 * @param i2 the second individual
		 * @return a child
		 */
		private Individual breed(Individual i1, Individual i2) {
			return new Individual(breedFunction.breed(i1.state, i2.state));
		}
		
		/**
		 * Mutates an individual with some probability
		 * @param i the individual
		 * @return a new mutated individual
		 */
		private void mutate(Individual i) {
			mutateFunction.mutate(i.state);
		}
		
		public String toString() {
			if (currentSize == 0) return "0";
			return "" + (totalFitnessScore / currentSize); 
		}
		
		private PriorityQueue<Individual> population;
		private int currentSize;
		private int totalFitnessScore;
		private Individual fittestIndividual;
	}
	
	public interface Breed {
		public State breed(State s1, State s2);
	}
	
	public interface Mutate {
		public void mutate(State s);
	}
	
	private Breed breedFunction;
	private Mutate mutateFunction;
	private int populationSize;
	private int fitnessGoal;
	private int maxGenerations;
	private int searchCost;
	Random gen = new Random();

}
