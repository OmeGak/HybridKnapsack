package hybridation;

import metaheuristics.Metaheuristic;
import problem.Knapsack;

/**
 * Agent class that executes a metaheuristic in order to solve a given knapsack problem.
 * 
 * @author omegak
 */
public class Agent {
	
	/** The metaheuristic the agent is using to solve the Knapsack problem. */
	private final Metaheuristic solver;
	
	/** The solution the agent is currently handling. */
	private Knapsack currentSolution;
	
	/**
	 * Constructor of the class. It sets the metaheuristic that will be used to solve the given knapsack problem.
	 * 
	 * @param metaheuristic The metaheuristic to solve the knapsack problem.  
	 * @param knapsack The knapsack problem to be solved.
	 */
	public Agent(Metaheuristic metaheuristic, Knapsack knapsack) {
		solver = metaheuristic;
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
	 * Performs a step of the metaheuristic producing a new solution.
	 */
	public void step() {
		solver.execute();
	}
}
