package Problem;

import java.util.ArrayList;

/**
 * Class to store a instance of the knapsack problem. It handles both the inserted and not inserted elements as well as
 * the capacity of the knapsack and the optimal solution value for comparison purposes.
 * 
 * @author omegak
 */
public class Knapsack {
	
	/** Value for invalid solutions. */
	static private int INVALID = -1;
	
	/** Total capacity of the knapsack. */
	private int capacity;
	
	/** Best possible solution of the instance. */
	private int optimalValue;
	
	/** List of inserted elements. */
	private ArrayList<Element> insertedElements;
	
	/** List of not inserted elements. */
	private ArrayList<Element> notInsertedElements;
	
	/**
	 * Constructor for the instance.
	 * 
	 * @param _capacity Total capacity of the knapsack.
	 * @param _optimalValue Best possible solution of the instance.
	 * @param _notInsertedElements List of not inserted elements.
	 */
	public Knapsack(int _capacity, int _optimalValue, ArrayList<Element> _notInsertedElements) {
		capacity = _capacity;
		optimalValue = _optimalValue;
		
		// Creates the lists
		insertedElements = new ArrayList<Element>();
		notInsertedElements = new ArrayList<Element>(_notInsertedElements);
	}
	
	/**
	 * Constructor for the instance.
	 * 
	 * @param _capacity Total capacity of the knapsack.
	 * @param _optimalValue Best possible solution of the instance.
	 * @param _insertedElements List of inserted elements.
	 * @param _notInsertedElements List of not inserted elements.
	 */
	public Knapsack(int _capacity, int _optimalValue, ArrayList<Element> _insertedElements, ArrayList<Element> _notInsertedElements) {
		capacity = _capacity;
		optimalValue = _optimalValue;
		
		// Creates the lists
		insertedElements = new ArrayList<Element>(_insertedElements);
		notInsertedElements = new ArrayList<Element>(_notInsertedElements);
	}
	
	/**
	 * Evaluation function that returns the profit of the solution. 
	 * 
	 * @return the profit of the solution. Negative for invalid solutions.
	 */
	public int evaluate() {
		if (getTotalWeight() <= capacity) {
			return getTotalValue();
		} else {
			return INVALID;
		}
	}
	
	/**
	 * Returns the total capacity of the knapsack.
	 * 
	 * @return the total capacity of the knapsack.
	 */
	public int getCapacity() {
		return capacity;
	}
	
	/**
	 * Returns the best possible solution of the instance. 
	 * 
	 * @return the best possible solution of the instance. 
	 */
	public int getOptimalValue() {
		return optimalValue;
	}
	
	/**
	 * Returns the total value of inserted elements.
	 * 
	 * @return the total value of inserted elements.
	 */
	public int getTotalValue() {
		int value = 0;
		
		for (int i=0; i<insertedElements.size(); i++) {
			value += insertedElements.get(i).getValue();
		}
		
		return value;
	}
	
	/**
	 * Returns the total weight of inserted elements.
	 * 
	 * @return the total weight of inserted elements.
	 */
	public int getTotalWeight() {
		int weight = 0;
		
		for (int i=0; i<insertedElements.size(); i++) {
			weight += insertedElements.get(i).getWeight();
		}
		
		return weight;
	}
	
	/**
	 * Creates a new {@link Knapsack} object moving the {@link Element} object referenced by {@link #index} from not 
	 * inserted to inserted.
	 * 
	 * @param index The index referencing the {@link Element} object to be inserted.
	 * @return a new {@link Knapsack} object.
	 * @throws IndexOutOfBoundsException When the index is  
	 */
	public Knapsack insertElement(int index) throws IndexOutOfBoundsException {
		// Exception check
		if (index >= notInsertedElements.size()) {
			throw new IndexOutOfBoundsException();
		}
		
		// Creates new lists of elements 
		ArrayList<Element> nextInsertedElements = new ArrayList<Element>(insertedElements);
		ArrayList<Element> nextNotInsertedElements = new ArrayList<Element>(notInsertedElements);
		
		// Updates the new lists
		Element insertion = nextNotInsertedElements.remove(index); 
		nextInsertedElements.add(insertion);
		
		// Returns the new solution
		return new Knapsack(capacity, optimalValue, nextInsertedElements, nextNotInsertedElements);
	}
}
