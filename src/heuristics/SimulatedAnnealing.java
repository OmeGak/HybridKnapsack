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
		HashMap<Integer, Element> inserted = knapsack.copyInsertedElements();
		HashMap<Integer, Element> notInserted = knapsack.copyNotInsertedElements();
		
		//Random value to calculate the number of neighbors you're going to visit in one step.
		//Value between 1 and 10.
		Random r = new Random();	
		int n = r.nextInt(10)+1;
		
		/*Element to compare*/
		Element e = knapsack.getRandomInsertedElement();
		
		/*Possibles elements to add into the knapsack*/
		ArrayList<Element> posChanges = new ArrayList<Element>();
		for(int i = 0; i < n; i++){
			posChanges.add(knapsack.getRandomNotInsertedElement());
		}
		
		
		/*Change the elements into the Knapsack*/
		for(int i = 0; i < posChanges.size(); i++)
		if(e.getWeight() > posChanges.get(i).getWeight()){
			inserted.remove(e);
			knapsack.insertElement(posChanges.get(i));
			notInserted.remove(posChanges.get(i));
			e = posChanges.get(i);
		}
		
		/*Can we add more elements now?*/
		if(!knapsack.isFull()){
			for(int i = 0; i < notInserted.size(); i++){
				if(knapsack.fits(notInserted.get(i))){
					knapsack.insertElement(notInserted.get(i));
				}
			}
		}
		
		actualSolution = knapsack;
		
		return knapsack;
	}

	
	/**
	 * Returns the min value found in the notInsertedElements list in the Knapsack.
	 * 
	 * @param h1  not Inserted Element List.
	 * @return minE Minimum Value found.
	 */
	private Element minElementFound(HashMap<Integer, Element> h1) {
		
		Element minE = new Element(h1.get(0).getId(),
				h1.get(0).getValue(),
				h1.get(0).getWeight());
		
		for (int i = 1; i < h1.size(); i++){
			if(minE.getWeight() > h1.get(i).getWeight()){
				minE = h1.get(i);
			}
		}
		return minE;
	}
}
