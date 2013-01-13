package problem;

import java.util.HashMap;
import java.util.NoSuchElementException;

/**
 * Class to store a instance of the knapsack problem. It handles both the inserted and not inserted elements as well as
 * the capacity of the knapsack and the optimal solution value for comparison purposes.
 * 
 * @author omegak
 */
public class Knapsack {
	
	/** Value for invalid solutions. */
	static private final int INVALID = -1;
	
	/** Total capacity of the knapsack. */
	private final int capacity;
	
	/** Best possible solution of the instance. */
	private final int optimalValue;
	
	/** List of inserted elements. */
	private final HashMap<Integer, Element> insertedElements;
	
	/** List of not inserted elements. */
	private final HashMap<Integer, Element> notInsertedElements;
	
	/**
	 * Constructor that copies a given {@link Knapsack} object and ensures inalterability. 
	 * 
	 * @param knapsack The given {@link Knapsack} object to be copied.
	 */
	public Knapsack(Knapsack knapsack) {
		capacity = knapsack.capacity;
		optimalValue = knapsack.optimalValue;
		
		// Creates the lists from copy
		insertedElements = new HashMap<Integer, Element>(knapsack.insertedElements);
		notInsertedElements = new HashMap<Integer, Element>(knapsack.notInsertedElements);
	}
	
	/**
	 * Constructor of the class that ensures inalterability.
	 * 
	 * @param _capacity Total capacity of the knapsack.
	 * @param _optimalValue Best possible solution of the instance.
	 * @param _notInsertedElements List of not inserted elements.
	 */
	public Knapsack(int _capacity, int _optimalValue, HashMap<Integer, Element> _notInsertedElements) {
		capacity = _capacity;
		optimalValue = _optimalValue;
		
		// Creates the lists
		insertedElements = new HashMap<Integer, Element>();
		notInsertedElements = new HashMap<Integer, Element>(_notInsertedElements);
	}
	
	/**
	 * Constructor of the class that ensures inalterability.
	 * 
	 * @param _capacity Total capacity of the knapsack.
	 * @param _optimalValue Best possible solution of the instance.
	 * @param _insertedElements List of inserted elements.
	 * @param _notInsertedElements List of not inserted elements.
	 */
	public Knapsack(int _capacity, int _optimalValue, HashMap<Integer, Element> _insertedElements, HashMap<Integer, Element> _notInsertedElements) {
		capacity = _capacity;
		optimalValue = _optimalValue;
		
		// Creates the lists
		insertedElements = new HashMap<Integer, Element>(_insertedElements);
		notInsertedElements = new HashMap<Integer, Element>(_notInsertedElements);
	}
	
	/**
	 * Returns a copy list of the elements that are inserted into the knapsack.
	 * 
	 * @return A copy list of the elements that are inserted into the knapsack.
	 */
	public HashMap<Integer, Element> copyInsertedElements() {
		return new HashMap<Integer, Element>(insertedElements);
	}
	
	/**
	 * Returns a copy list of the elements that are not inserted into the knapsack.
	 * 
	 * @return A copy list of the elements that are not inserted into the knapsack.
	 */
	public HashMap<Integer, Element> copyNotInsertedElements() {
		return new HashMap<Integer, Element>(notInsertedElements);
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
	 * Determines whether or not a given element fits in the knapsack or not.
	 * 
	 * @param element The element to be determined if fits.
	 * @return TRUE if the element fits, FALSE otherwise.
	 */
	public boolean fits(Element element) {
		return element.getWeight() <= getFreeSpace();
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
	 * Returns the free space available in the knapsack.
	 * 
	 * @return The free space available in the knapsack.
	 */
	public int getFreeSpace() {
		return capacity - getTotalWeight();
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
	 * Creates a new {@link Knapsack} object moving the given {@link Element} from not inserted to inserted.
	 * 
	 * @param element The element to be inserted from the not inserted list.
	 * @return a new {@link Knapsack} object.
	 * @throws NoSuchElementException When the element is not present in not inserted elements.  
	 */
	public Knapsack insertElement(Element element) throws NoSuchElementException {
		
		// Exception check
		if (!notInsertedElements.containsKey(element.getId())) {
			throw new NoSuchElementException();
		}
		
		// Creates new lists of elements 
		HashMap<Integer, Element> nextInsertedElements = new HashMap<Integer, Element>(insertedElements);
		HashMap<Integer, Element> nextNotInsertedElements = new HashMap<Integer, Element>(notInsertedElements);
		
		// Updates the new lists
		Element insertion = nextNotInsertedElements.remove(element.getId());
		nextInsertedElements.put(insertion.getId(), insertion);
		
		// Returns the new solution
		return new Knapsack(capacity, optimalValue, nextInsertedElements, nextNotInsertedElements);
	}
	
	/**
	 * Determines whether the knapsak is full or not.
	 * 
	 * @return TRUE if the knapsack is full, FALSE otherwise.
	 */
	public boolean isFull() {
		int emptySpace = getFreeSpace();
		
		for (Integer key : notInsertedElements.keySet()) {
			Element element = notInsertedElements.get(key);
			
			if (element.getWeight() < emptySpace) {
				return false;
			}			
		}
		
		return true;
	}
}
