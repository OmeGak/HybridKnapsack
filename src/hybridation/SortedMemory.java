package hybridation;

import java.util.LinkedList;

/**
 * FIFO memory that stores sorted elements.
 * 
 * @param <E> A {@link Comparable} class.
 * @author omegak
 */
public class SortedMemory<E extends Comparable<E>> {
	
	/** Maximum size of memory. */
	private final int maxSize;
	
	/** FIFO list of elements. */
	private final LinkedList<E> queue;
	
	/** List of elements sorted by evaluation. */
	private final LinkedList<E> sortedList;
	
	/**
	 * Creates the internal structure of the memory.
	 */
	public SortedMemory(int size) {
		maxSize = size;
		queue = new LinkedList<E>();
		sortedList = new LinkedList<E>();
	}

	/**
	 * Adds a elements to the list. If the memory is full, the first input is removed.
	 * 
	 * @param e The element to be added.
	 * @return The percentile where the element has been inserted.
	 */
	public double add(E e) {
		int index;
		
		// Adds to the queue
		queue.offer(e);
		
		// Polls queue
		if (queue.size() > maxSize) {
			sortedList.remove(queue.poll());
		}
		
		// Adds to the sorted list
		index = addToSortedList(e);
		
		return calculateRatio(index);
	}

	/**
	 * Returns the best element stored in the memory.
	 * 
	 * @return the best element stored in the memory.
	 */
	public E getBest() {
		E best = sortedList.getLast();
		return best;
	}
	
	/**
	 * Adds an element to the sorted list.
	 * 
	 * @param e The element to be stored.
	 * @return The index in which the element has been stored.
	 */
	private int addToSortedList(E e) {
		int index = 0;
		
		for (E item : sortedList) {
			if (e.compareTo(item) > 0) {
				index++;
			} else {
				break;
			}
		}
		
		sortedList.add(index, e);
		return index;
	}
	
	/**
	 * Calculates the percentile of a given position.
	 * 
	 * @param position The position to be evaluated.
	 * @return The percentile of a given position between 0 and 1.
	 */
	private double calculateRatio(int position) {
		return 1 - (position+1) / sortedList.size();
	}
}
