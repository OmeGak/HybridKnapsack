package heuristics;

import java.util.ArrayList;

import problem.Element;
import problem.Knapsack;


/**
 * Simulated Annealing Class, which represent the algorithm and the solutions.
 * 
 * @author Oxy
 */

public class SimulatedAnnealing extends Heuristic {
	/**
	 * Global variables.
	 * The integer type variables are initialized to 0.
	 */
	private int temperature, freezingT, nIter, MaxIter = 0;	// TODO one declaration per line
	private Knapsack bestSolution, actualSolution;
	private ArrayList<Knapsack> solutions;
	private Knapsack knapsack;
	
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
	public SimulatedAnnealing(int t, int fT, int nI, int Max, Knapsack k){
		this.temperature = t;
		this.freezingT = fT;
		this.nIter = nI;
		this.MaxIter = Max;
		this.knapsack = k;
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
	 * TODO doc
	 */
	public Knapsack executeOnce(Knapsack knapsack) {
		// TODO this is the actual algorithim
		return new Knapsack(knapsack);
	}
}
