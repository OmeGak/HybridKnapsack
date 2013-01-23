package problem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * Class to store a instance of the knapsack problem. It handles both the inserted and not inserted elements as well as
 * the capacity of the knapsack and the optimal solution value for comparison purposes.
 * 
 * @author omegak
 */
public class Knapsack {
	
	/** Total capacity of the knapsack. */
	private final int capacity;
	
	/** Best possible solution of the instance. */
	private final int optimalValue;
	
	/** List of inserted elements. */
	private final HashMap<Integer, Element> insertedElements;
	
	/** List of not inserted elements. */
	private final HashMap<Integer, Element> notInsertedElements;
	
	/** Total value of inserted elements. */
	private int totalValue = 0;
	
	/** Total weight of inserted elements. */
	private int totalWeight = 0;
	
	/**
	 * Constructor that copies a given {@link Knapsack} object and ensures inalterability. 
	 * 
	 * @param knapsack The given {@link Knapsack} object to be copied.
	 */
	public Knapsack(Knapsack knapsack) {
		capacity = knapsack.capacity;
		optimalValue = knapsack.optimalValue;
		totalValue = knapsack.totalValue;
		totalWeight = knapsack.totalWeight;
		
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
	 * Returns the free space available in the knapsack.
	 * 
	 * @return The free space available in the knapsack.
	 */
	public int calculateFreeSpace() {
		return capacity - getTotalWeight();
	}
	
	/**
	 * Compares this knapsack with a given one.
	 * 
	 * @param k the knapsack to be compared with.
	 * @return A ratio of improvement. 1 for equals, above for better and under for worse. 
	 */
	public double compareWith(Knapsack k) {
		return (double) evaluate()/k.evaluate();
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
	 * Determines whether a given knapsack has the same composition as this or not.
	 * 
	 * @param k The knapsack to be compared with.
	 * @return TRUE if the given knapsack is equal, FALSE otherwise.
	 */
	public boolean equals(Knapsack k) {
		for (Integer key : insertedElements.keySet()) {
			if (!k.insertedElements.containsKey(key)) {
				return false;
			}
		}
		
		for (Integer key : notInsertedElements.keySet()) {
			if (!k.notInsertedElements.containsKey(key)) {
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Evaluates this knapsack using the {@link Evaluator} class.
	 * 
	 * @return The evaluation of this knapsack.
	 */
	public int evaluate() {
		return Evaluator.evaluate(this);
	}
	
	/**
	 * Determines whether or not a given element fits in the knapsack or not.
	 * 
	 * @param element The element to be determined if fits.
	 * @return TRUE if the element fits, FALSE otherwise.
	 */
	public boolean fits(Element element) {
		return element.getWeight() <= calculateFreeSpace();
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
	 * Returns a random element from the inserted elements list.
	 * 
	 * @return A random element from the inserted elements list.
	 * @throws Exception When there are no inserted elements.
	 */
	public Element getRandomInsertedElement() throws Exception {
		Random random = new Random();
		
		if (insertedElements.size() == 0) {
			throw new Exception("No elements inserted");
		}
		
		List<Integer> keys = new ArrayList<Integer>(insertedElements.keySet());
		int randomKey = keys.get(random.nextInt(keys.size()));
		
		return insertedElements.get(randomKey);
	}
	
	/**
	 * Returns a random element from the not inserted elements list.
	 * 
	 * @return A random element from the not inserted elements list.
	 * @throws Exception When there are no not inserted elements.
	 */
	public Element getRandomNotInsertedElement() throws Exception {
		Random random = new Random();
		
		if (notInsertedElements.size() == 0) {
			throw new Exception("No elements not inserted");
		}
		
		List<Integer> keys = new ArrayList<Integer>(notInsertedElements.keySet());
		int randomKey = keys.get(random.nextInt(keys.size()));
		
		return notInsertedElements.get(randomKey);
	}
	
	/**
	 * Returns the total value of inserted elements.
	 * 
	 * @return the total value of inserted elements.
	 */
	public int getTotalValue() {
		return totalValue;
	}
	
	/**
	 * Returns the total weight of inserted elements.
	 * 
	 * @return the total weight of inserted elements.
	 */
	public int getTotalWeight() {
		return totalWeight;
	}
	
	/**
	 * Tries to move the given {@link Element} from not inserted to inserted.
	 * 
	 * @param element The element to be inserted from the not inserted list.
	 * @throws NoSuchElementException When the element is not present in not inserted elements.  
	 */
	public void insertElement(Element element) throws NoSuchElementException {
		if (!notInsertedElements.containsKey(element.getId())) {
			throw new NoSuchElementException();
		}
		
		Element insertedElement = notInsertedElements.remove(element.getId());
		insertedElements.put(insertedElement.getId(), insertedElement);
		updateTotalsOnInsertion(insertedElement);
	}
	
	/**
	 * Determines whether the knapsak is full or not.
	 * 
	 * @return TRUE if the knapsack is full, FALSE otherwise.
	 */
	public boolean isFull() {
		int emptySpace = calculateFreeSpace();
		
		for (Integer key : notInsertedElements.keySet()) {
			Element element = notInsertedElements.get(key);
			
			if (element.getWeight() < emptySpace) {
				return false;
			}			
		}
		
		return true;
	}
	
	/**
	 * Determines whether the knapsak problem has inserted elements or not.
	 * 
	 * @return TRUE if it has inserted elements, FALSE otherwise.
	 */
	public boolean hasInsertedElements() {
		return insertedElements.size() != 0;
	}
	
	/**
	 * Determines whether the knapsak problem still has not inserted elements left or not.
	 * 
	 * @return TRUE if not inserted elements are left, FALSE otherwise.
	 */
	public boolean hasNotInsertedElements() {
		return notInsertedElements.size() != 0;
	}
	
	/**
	 * Tries to move the given {@link Element} from inserted to not inserted.
	 * 
	 * @param element The element to be removed from the inserted list.
	 * @throws NoSuchElementException When the element is not present in inserted elements.  
	 */
	public void removeElement(Element element) throws NoSuchElementException {
		if (!insertedElements.containsKey(element.getId())) {
			throw new NoSuchElementException();
		}
				
		Element removedElement = insertedElements.remove(element.getId());
		notInsertedElements.put(removedElement.getId(), removedElement);
		updateTotalsOnRemoval(removedElement);		
	}
	
	/**
	 * Updates the totals for an inserted element.
	 * 
	 * @param e The inserted element.
	 */
	private void updateTotalsOnInsertion(Element e) {
		totalValue += e.getValue();
		totalWeight += e.getWeight();
	}
	
	/**
	 * Updates the totals for a removed element.
	 * 
	 * @param e The removed element.
	 */
	private void updateTotalsOnRemoval(Element e) {
		totalValue =- e.getValue();
		totalWeight =- e.getWeight();
	}
}
