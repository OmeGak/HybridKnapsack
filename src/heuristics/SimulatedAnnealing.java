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
		if(comparateKnapsack.evaluate() == -1){
			currentSolution = knapsack;
		}else{
			//Compare
			if(knapsack.calculateTotalValue() < comparateKnapsack.calculateTotalValue()){
				currentSolution = comparateKnapsack;
			}else{
				//If the temperature is greater than 0 (you can
				//use a worse solution than the actual one)
				if(temperature > 0){
					currentSolution = comparateKnapsack;
					temperature -= freezingT;
				}
				
				currentSolution = knapsack;
			}
		}
		
		return currentSolution;
	}
}


