package heuristics;

import problem.Knapsack;

/**
 * Simulated Annealing heuristic.
 * 
 * @author Oxy
 */
public class SimulatedAnnealing extends Heuristic {

	/**Freezing rate */
	private final int freezingRate;
	
	/**Initial temperature of the algorithm*/
	private int temperature = 0;

	
	/**
	 * Constructor of SimulatedAnnealing class.
	 * 
	 * @param temperature Temperature, chosen by the user
	 * @param freezingRate Freezing rate 
	 */
	public SimulatedAnnealing(int temperature, int freezingRate){
		this.temperature = temperature;
		this.freezingRate = freezingRate;
	}
	
	/**
	 * SimulatedAnnealing Algorithm execute only once.
	 * 
	 * @param Actual knapsack which you are working.
	 * @return Actual Solution.
	 */
	public Knapsack executeOnce(Knapsack knapsack) {
		int neighbours = generateRandomNumberOfNeighbours();
		Knapsack best = null;
				
		for (int i=0; i<neighbours; i++) {
			Knapsack candidate = generateNeighbour(knapsack);
			
			if (best == null) {
				best = candidate;
			} else if (candidate.compareWith(best) > 0) {
				best = candidate;
			}
		}
		
		if (best.compareWith(knapsack) >= 0) {
			heatTemperature();
			return best;
		} else {
			if (Math.random() >= calculateProbability(best, knapsack)) {
				freezeTemperature();
				return best;
			} else {
				return knapsack;
			}
		}
	}
	
	/**
	 * Calculate the probability of accepting a worse solution than the current one.
	 * 
	 * @param next The candidate for next solution.
	 * @param current The current solution.
	 * @return The probability between 0 and 1.
	 */
	private double calculateProbability(Knapsack next, Knapsack current) {
		int differenceOfValue = current.evaluate() - next.evaluate();
		return Math.exp(differenceOfValue/temperature);
	}
	
	/**
	 * Raises the temperature.
	 */
	private void heatTemperature() {
		temperature += freezingRate;
	}
	
	/**
	 * Lowers the temperature.
	 */
	private void freezeTemperature() {
		temperature -= freezingRate;
	}
}


