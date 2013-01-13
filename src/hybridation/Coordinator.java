package hybridation;

import heuristics.HeuristicFactory;

import java.util.ArrayList;

import problem.Knapsack;


/**
 * Class of objects responsible of running several agents that implement different heuristics to solve the knapsack
 * problem.
 * 
 * @author omegak
 */
public class Coordinator {

	/** The initial knapsack problem. */
	private final Knapsack initialKnapsack;
	
	/** The best solution found. */
	private Knapsack currentBestKnapsack;
	
	/** List of agents that solves the problem. */
	private ArrayList<Agent> agents;
	
	/**
	 * Constructor of the class. It sets the initial knapsack problem.
	 * 
	 * @param knapsack The knapsack problem to be solved.
	 */
	public Coordinator(Knapsack knapsack) {
		initialKnapsack = new Knapsack(knapsack);
		currentBestKnapsack = new Knapsack(knapsack);
	}
	
	/**
	 * Returns TRUE if the coordinator has solved the problem, FALSE otherwise.
	 * 
	 * @return TRUE if the coordinator has solved the problem, FALSE otherwise.
	 */
	public boolean hasSolved() {
		// TODO when is it solved?
		return false;
	}
	
	/**
	 * Prints the state of the object.
	 */
	public void print() {
		// TODO print
	}
	
	/**
	 * Creates and runs all the agents implementing different heuristics until they find an acceptable solution. After
	 * each step, if a certain heuristic is performing extremely bad, its search space will be overridden with the
	 * current best solution found.
	 */
	public void solve() {
		
		// Creates agents
		for (HeuristicFactory heuristicType : HeuristicFactory.values()) {
			agents.add(new Agent(heuristicType.create(), initialKnapsack));
		}
		
		// TODO stop condition?
		// N lines without improvement
		while (true) {
			runAgentsOnce();
			redirectAgentsSearch();
		}
	}
	
	/**
	 * Runs one step of each agent.
	 */
	private void runAgentsOnce() {
		for (Agent agent : agents) {
			agent.step();
		}
	}
	
	/**
	 * Checks if any of the agents is performing bad enough to reset its search space to the current best solution.
	 */
	private void redirectAgentsSearch() {
		
		// Checks quality of each agent
		for (Agent agent : agents) {
			// TODO comparative value
			// x% worse than best?
		}
		
		// TODO fix search space
		
	}
}
