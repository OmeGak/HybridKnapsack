package hybridation;

import heuristics.Heuristic;
import problem.Knapsack;

/**
 * Agent class that executes a heuristic in order to solve a given knapsack problem.
 * 
 * @author omegak
 */
public class Agent {
	
	/** The maximum number of iterations the agent can perform. */
	private static final int MAX_ITERATIONS = 150;
	
	/** The minimum number of iterations the agent can perform. */
	private static final int MIN_ITERATIONS = 100;
	
	/** The heuristic the agent is using to solve the Knapsack problem. */
	private final Heuristic solver;
	
	/** The solution the agent is currently handling. */
	private Knapsack currentSolution;
	
	/**
	 * Constructor of the class. It sets the heuristic that will be used to solve the given knapsack problem.
	 * 
	 * @param heuristic The heuristic to solve the knapsack problem.  
	 * @param knapsack The knapsack problem to be solved.
	 */
	public Agent(Heuristic heuristic, Knapsack knapsack) {
		solver = heuristic;
		currentSolution = knapsack;
	}
	
	/**
	 * Returns the evaluation of the current solution.
	 * 
	 * @return the evaluation of the current solution.
	 */
	public int evaluateCurrentSolution() {
		return currentSolution.evaluate();
	}
	
	/**
	 * Returns a copy of the current solution.
	 * 
	 * @return A copy of the current solution.
	 */
	public Knapsack getCurrentSolution() {
		return new Knapsack(currentSolution);
	}
	
	/**
	 * Sets the new current solution.
	 * 
	 * @param knapsack The Knapsack to be set as current solution.
	 */
	public void setCurrentSolution(Knapsack knapsack) {
		currentSolution = new Knapsack(knapsack);
	}
	
	/**
	 * Performs a single iteration of the heuristic producing a new solution.
	 */
	public void executeOnce() {
		currentSolution = solver.executeOnce(currentSolution);
	}
	
	/**
	 * Performs a step of the heuristic producing a new solution.
	 */
	public void step() {
		int iterations = generateIterations();
		
		for (int i=0; i < iterations; i++) {
			currentSolution = solver.executeOnce(currentSolution);			
		}
	}
	
	/**
	 * Generates a random number of iterations for the agent.
	 * 
	 * @return A random number of iterations for the agent.
	 */
	private int generateIterations() {
		return MIN_ITERATIONS + (int) (Math.random() * ((MAX_ITERATIONS - MIN_ITERATIONS) + 1));
	}
}
