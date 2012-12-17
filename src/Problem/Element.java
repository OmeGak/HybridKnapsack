package Problem;

/**
 * Class representing an element of the knapsack.
 * 
 * @author omegak
 */
public class Element {
	
	/** The value this element adds to the knapsack. */
	int value;
	
	/** The weight of the element. */
	int weight;
	
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
	
	/**
	 * Sets the value of the element.
	 * 
	 * @param _value The value of the element.
	 */
	public void setValue(int _value) {
		value = _value;
	}
	
	/**
	 * Sets the weight of the element.
	 * 
	 * @param _weight The weight of the element.
	 */
	public void setWeight(int _weight) {
		weight = _weight;
	}
}
