package heuristics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import problem.Element;
import problem.Knapsack;


/**
 * Simulated Annealing Class, which represent the algorithm and the solutions.
 * 
 * @author Oxy
 */

public class SimulatedAnnealing extends Heuristic {
	
	
	/**Initial temperature of the algorithm*/
	private int temperature = 0;
	
	/**Freezing rate */
	private int freezingT = 0;
	
	/**Number of iterations */
	private int nIter = 0; 
	
	/**Max number of iterations */
	private int MaxIter = 0;
	
	/**Best Solution found */
	private Knapsack bestSolution;
	
	/**Actual Solution */
	private Knapsack actualSolution;
	
	/**Set of Solutions */
	private ArrayList<Knapsack> solutions;
	
	public SimulatedAnnealing() {
		// TODO delete default constructor when interface is fully developed
	}
	
	/**
	 * Constructor of SimulatedAnnealing class.
	 * @param t Temperature, chosen by the user
	 * @param fT Freezing Temperature 
	 * @param nI Number of iterations
	 * @param Max Max number of iterations
	 * @param k Instance of the problem which We are going to work
	 */
	public SimulatedAnnealing(int t, int fT, int nI, int Max){
		this.temperature = t;
		this.freezingT = fT;
		this.nIter = nI;
		this.MaxIter = Max;
	}
	
	/**
	 * Simulated Annealing Algorithm method
	 */
	public Knapsack SAAlgorithm (){
		// TODO addapt to knapsack changes
//		Element minE = new Element();
//		minE = knapsack.getNotInsertedElements().get(0); /*Random value*/
//		
//		
//		/*Looking for the min element in the instance*/
//		for(int i = 0; i < knapsack.getNotInsertedElements().size(); i++){
//			for(int j = 0; j < knapsack.getNotInsertedElements().size(); j++){
//				
//				if(minE.getWeight() >  knapsack.getNotInsertedElements().get(j).getWeight()){
//					minE = knapsack.getNotInsertedElements().get(j);
//				
//				/*
//				 * If we can put into the knapsack
//				 */
//				if(knapsack.getTotalWeight() + minE.getWeight() <= knapsack.getCapacity()){
//					knapsack.getInsertedElements().add(minE);
//					solutions.add(knapsack);
//					actualSolution = knapsack;
//					knapsack.getNotInsertedElements().remove(minE);
//				}else{
//					break;
//				}
//				}
//			}
//			
//		}
		return actualSolution;
		
	}
	
	/**
	 * SimulatedAnnealing Algorithm execute only once.
	 * 
	 * @param Actual knapsack which you are working.
	 * @return Actual Solution.
	 */
	public Knapsack executeOnce(Knapsack knapsack) {
		// TODO this is the actual algorithim
		
		//Random value to calculate the number of neighbors you're going to visit in one step.
		//Value between 1 and 10.	
		int n = generateRandomNumberOfNeighbours();
		
		ArrayList<Knapsack> listOfSolutions = new ArrayList<Knapsack>();
		
		for (int i = 0; i < n; i++){
			listOfSolutions.add(generateNeighbour(knapsack));
		}
		
		for(int i = 0; i < listOfSolutions.size(); i++){
			if(knapsack.calculateTotalValue() < listOfSolutions.get(i).calculateTotalValue()){
				knapsack = listOfSolutions.get(i);
			}
		}
		
		
		
		return actualSolution;
	}
}
