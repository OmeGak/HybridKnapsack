package Problem;

import java.util.ArrayList;

/**
 * Class to store a instance of the knapsack problem.
 * 
 * @author omegak
 */
public class Knapsack {
	
	/** Total capacity of the knapsack. */
	private int maxWeight;
	
	/** Best possible solution of the instance. */
	private int optimalValue;
	
	/** List of all possible elements to be put in the knapsack. */
	private ArrayList<Element> elements;
	
	/**
	 * Constructor for the instance.
	 * 
	 * @param _capacity Total capacity of the knapsack.
	 * @param _optimalValue Best possible solution of the instance.
	 * @param _elements List of all possible elements to be put in the knapsack.
	 */
	public Knapsack(int _capacity, int _optimalValue, ArrayList<Element> _elements) {
		maxWeight = _capacity;
		optimalValue = _optimalValue;
		elements = new ArrayList<Element>(_elements);
	}
	
	/**
	 * Returns the list of all possible elements to be put in the knapsack.
	 * 
	 * @return the list of all possible elements to be put in the knapsack.
	 */
	public ArrayList<Element> getElements() {
		return elements;
	}
	
	/**
	 * Returns the total capacity of the knapsack.
	 * 
	 * @return the total capacity of the knapsack.
	 */
	public int getMaxWeight() {
		return maxWeight;
	}
	
	/**
	 * Returns the best possible solution of the instance. 
	 * 
	 * @return the best possible solution of the instance. 
	 */
	public int getOptimalValue() {
		return optimalValue;
	}
}
