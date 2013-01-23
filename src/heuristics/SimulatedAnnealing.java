package heuristics;

import java.util.ArrayList;

import problem.Knapsack;

/**
 * Simulated Annealing heuristic.
 * 
 * @author Oxy
 */
public class SimulatedAnnealing extends Heuristic {
	
	/**Initial temperature of the algorithm*/
	private int temperature = 0;
	
	/**Freezing rate */
	private int freezingT = 0;
	
	/**Actual Solution */
	private Knapsack currentSolution;
	
	
	
	public SimulatedAnnealing() {
		// TODO delete default constructor when interface is fully developed
	}
	
	/**
	 * Constructor of SimulatedAnnealing class.
	 * 
	 * @param t Temperature, chosen by the user
	 * @param fT Freezing Temperature 
	 */
	public SimulatedAnnealing(int t, int fT){
		this.temperature = t;
		this.freezingT = fT;
	}
	
	/**
	 * SimulatedAnnealing Algorithm execute only once.
	 * 
	 * @param Actual knapsack which you are working.
	 * @return Actual Solution.
	 */
	public Knapsack executeOnce(Knapsack knapsack) {
		//Random value to calculate the number of neighbors you're going to visit in one step.
		//Value between 1 and 10.	
		int n = generateRandomNumberOfNeighbours();
		
		//Value of the different of Energy (value) between knapsacks
		int dEnergy = 0; 
		
		//Probability of change the knapsack if the comparateKnapsack is worse.
		double probability = 0;
		
		//Comparative probability in case of dEnergy > 0
		double randomProbability = 0;
		
		ArrayList<Knapsack> listOfSolutions = new ArrayList<Knapsack>();
		
		for (int i = 0; i < n; i++){
			listOfSolutions.add(generateNeighbour(knapsack));
		}
		
		currentSolution = knapsack;
	
		//Comparate the different possible Solutions until you get the best of them
		Knapsack comparateKnapsack = listOfSolutions.get(0);
		
		for(int i = 1; i < listOfSolutions.size(); i++){
			if(comparateKnapsack.calculateTotalValue() < listOfSolutions.get(i).calculateTotalValue())
				comparateKnapsack = listOfSolutions.get(i);
		}
		
		
				
		//Comparate the best with the knapsack you're working
		//If the best solution is not valid
		if(comparateKnapsack.evaluate() != -1){
			dEnergy = knapsack.evaluate() - comparateKnapsack.evaluate();
		}
		
		
		probability = calculateProbability(dEnergy, temperature);
		randomProbability = Math.random();
		
		//If the comparateKnapsack is better than our current Knapsack
		if(probability == 1){
			currentSolution = comparateKnapsack;
			temperature += freezingT;
		}else{
			//If the random probability is greater than calculated probability
			if(randomProbability > probability){
				currentSolution = comparateKnapsack;
				temperature -= freezingT;
			}else{
				currentSolution = knapsack;
				temperature -= freezingT;
			}
		}
		
		
		return currentSolution;
	}
	
	/**
	 * Return the probability of change a worse knapsack for the
	 * current one.
	 * @param dEnergy Different of energy between knapsacks
	 * @param t Current Temperature
	 * @return probability of change
	 */

	private double calculateProbability(int dEnergy, int t) {
		
		double changeProbability = 0;
		
		if(dEnergy > 0){
			changeProbability = Math.exp(-(dEnergy)/t);
		}
		else changeProbability = 1;
		
		return changeProbability;
	}
}


