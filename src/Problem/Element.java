package Problem;

/**
 * Class representing an element of the knapsack.
 * 
 * @author omegak
 */
public class Element {
	
	/** The value this element adds to the knapsack. */
	private final int value;
	
	/** The weight of the element. */
	private final int weight;
	
	/**
	 * Constructor for the class. It sets the parameters.
	 * 
	 * @param value The value of the element.
	 * @param weight The weight of the element.
	 */
	public Element(int value, int weight) {
		this.value = value;
		this.weight = weight;
	}
	
	/**
	 * Returns the value of the element.
	 * 
	 * @return the value of the element.
	 */
	public int getValue() {
		return value;
	}
	
	/**
	 * Returns the weight of the element.
	 * 
	 * @return the weight of the element.
	 */
	public int getWeight() {
		return weight;
	}
}
