package problem;

/**
 * Class representing an element of the knapsack.
 * 
 * @author omegak
 */
public class Element {
	
	/** Unique identifier. */
	private final int id;
	
	/** The value this element adds to the knapsack. */
	private final int value;
	
	/** The weight of the element. */
	private final int weight;
	
	/**
	 * Constructor for the class. It sets the parameters.
	 * 
	 * @param id The unique identifier.
	 * @param value The value of the element.
	 * @param weight The weight of the element.
	 */
	public Element(int id, int value, int weight) {
		this.id = id;
		this.value = value;
		this.weight = weight;
	}
	
	/**
	 * Returns the unique identifier of the element.
	 * 
	 * @return The unique identifier of the element.
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Returns the ratio value/weight. It will be above 1.0 if the value is higher than the weight, under 1.0 otherwise.
	 * 
	 * @return The ratio value/weight
	 */
	public double getRatio() {
		return (double)value/(double)weight;
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
