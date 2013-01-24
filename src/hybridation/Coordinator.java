package hybridation;

import heuristics.RandomSearch;

import java.util.ArrayList;

import problem.Evaluator;
import problem.Knapsack;

/**
 * Class of objects responsible of running several agents that implement different heuristics to solve the knapsack
 * problem.
 * 
 * @author omegak
 */
public class Coordinator {

	/** Number of rounds without obtaining any improvement before stopping. */
	private static final int MAX_ROUNDS_WITHOUT_IMPROVEMENT = 30;
	
	/** Maximum size of the memories used by the coordinator. */
	private static final int MEMORY_MAX_SIZE = 30;
	
	/** Threshold for an acceptable solution. */
	private static final double REDIRECTION_THRESHOLD = 0.5;
	
	/** List of agents that solves the problem. */
	private final ArrayList<Agent> agents;
	
	/** The initial knapsack problem. */
	private final Knapsack initialKnapsack;
	
	/** The memory of improvement ratios of agents. */
	private final SortedMemory<Double> improvementRatioMemory = new SortedMemory<Double>(MEMORY_MAX_SIZE);
	
	/** The memory of accepted solutions. */
	private final SortedMemory<EvaluatedKnapsack> solutionMemory = new SortedMemory<EvaluatedKnapsack>(MEMORY_MAX_SIZE);
	
	/** The best solution found. */
	private Knapsack currentBestKnapsack;
	
	/** Counter to keep track of rounds that have produced no improvement. */
	private int roundsNotImproving = 0;
	
	/**
	 * Constructor of the class. It sets the initial knapsack problem.
	 * 
	 * @param mode The mode in which the coordinator will work.
	 * @param knapsack The knapsack problem to be solved.
	 */
	public Coordinator(CoordinatorMode mode, Knapsack knapsack) {
		initialKnapsack = new Knapsack(knapsack);
		initializeCurrentBest();
		agents = mode.generateAgents(currentBestKnapsack);
		Evaluator.reset();
	}
	
	/**
	 * Determines whether the coordinator has finished executing or not. The stopping conditions is having exhausted the
	 * evaluator or having run several rounds without improvement. 
	 * 
	 * @return TRUE if the coordinator has finished, FALSE otherwise.
	 */
	public boolean hasFinished() {
		return (Evaluator.isExhausted()) || (roundsNotImproving > MAX_ROUNDS_WITHOUT_IMPROVEMENT);
	}
	
	/**
	 * Returns the state of the object.
	 * 
	 * @return The evaluation of the best solution found.
	 */
	public String printBestSolution() {
		return Integer.toString(currentBestKnapsack.evaluate());
	}
	
	/**
	 * Creates and runs all the agents implementing different heuristics until they find an acceptable solution. After
	 * each step, if a certain heuristic is performing extremely bad, its search space will be overridden with the
	 * current best solution found.
	 * 
	 * @return The knapsack containing the best solution found.
	 */
	public Knapsack solve() {
		while (!hasFinished()) {
			runAgentsOnce();
			updateCurrentBest();
			redirectSearch();
		}
		
		return new Knapsack(currentBestKnapsack);
	}
	
	/**
	 * Updates memories and redirects the search for those agents performing badly. 
	 */
	private void redirectSearch() {
		double agentPerformance;
		double solutionPerformance;
		
		for (Agent agent : agents) {
			agentPerformance = improvementRatioMemory.add(agent.calculateImprovementRatio());
			
			EvaluatedKnapsack memory = new EvaluatedKnapsack(agent.getCurrentSolution());
			solutionPerformance = solutionMemory.add(memory);
			
			if (agentPerformance <= REDIRECTION_THRESHOLD && solutionPerformance <= REDIRECTION_THRESHOLD) {
				redirectAgent(agent);
			}
		}
	}
	
	/**
	 * Initializes the coordinator by producing an initial best knapsack with a greedy algorithm.
	 */
	private void initializeCurrentBest() {
		Agent agent = new Agent(new RandomSearch(), initialKnapsack);
		agent.executeOnce();
		currentBestKnapsack = agent.getCurrentSolution();
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
	 * Collect the solution from all the agents.
	 */
	private void updateCurrentBest() {
		boolean improved = false;
		
		// Searches for improved solutions
		for (Agent agent : agents) {
			if (agent.evaluateCurrentSolution() > currentBestKnapsack.evaluate()) {
				currentBestKnapsack = agent.getCurrentSolution();
				improved = true;
				roundsNotImproving = 0;
			}
		}
		
		// Increases rounds not improving
		if (!improved) {
			roundsNotImproving++;			
		}
	}
	
	/**
	 * Checks if any of the agents is performing bad enough to reset its search space to the current best solution.
	 */
	private void redirectAgent(Agent agent) {
		EvaluatedKnapsack best = solutionMemory.getBest();
		agent.setCurrentSolution(best.getKnapsack());
	}
}
