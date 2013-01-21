package hybridation;

import java.util.LinkedList;

import problem.Knapsack;

/**
 * A FIFO list of knapsacks sorted by their quality. 
 * 
 * @author omegak
 */
public class KnapsackMemory {

	/** Maximum size of memory. */
	private static final int MAX_SIZE = 30;
	
	/** FIFO list of knapsacks. */
	private final LinkedList<KnapsackMemoryPair> queue;
	
	/** List of knapsacks sorted by evaluation. */
	private final LinkedList<KnapsackMemoryPair> sortedList;
	
	/**
	 * Creates the internal structure of the KnapsackMemory.
	 */
	public KnapsackMemory() {
		queue = new LinkedList<KnapsackMemoryPair>();
		sortedList = new LinkedList<KnapsackMemoryPair>();
	}

	/**
	 * Adds a knapsack to the list. If the memory is full, the first input knapsack is removed.
	 * 
	 * @param k The knapsack to be added.
	 * @return The percentile where the knapsack has been inserted.
	 */
	public double add(Knapsack k) {
		KnapsackMemoryPair memory = new KnapsackMemoryPair(k);
		int index;
		
		// Adds to the queue
		queue.offer(memory);
		
		// Polls queue
		if (queue.size() > MAX_SIZE) {
			sortedList.remove(queue.poll());
		}
		
		// Adds to the sorted list
		index = addToSortedList(memory);
		
		return calculateRatio(index);
	}

	/**
	 * Returns the best knapsack stored in the memory.
	 * 
	 * @return the best knapsack stored in the memory.
	 */
	public Knapsack getBest() {
		Knapsack best = sortedList.getLast().getKnapsack();
		return new Knapsack(best);
	}
	
	/**
	 * Adds a knapsack to the sorted list.
	 * 
	 * @param k The knapsack to be stored.
	 * @return The index in which the knapsack has been stored.
	 */
	private int addToSortedList(KnapsackMemoryPair k) {
		int index = 0;
		
		for (KnapsackMemoryPair item : sortedList) {
			if (k.compareTo(item) > 0) {
				index++;
			} else {
				break;
			}
		}
		
		sortedList.add(index, k);
		return index;
	}
	
	/**
	 * Calculates the position ratio of a given position.
	 * 
	 * @param position The position to be evaluated.
	 * @return The position ratio of a given position between 0 and 1.
	 */
	private double calculateRatio(int position) {
		return 1 - (position+1) / sortedList.size();
	}
}
