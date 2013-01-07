package heuristics;

/**
 * Abstract class to extends specific heuristics from it.
 * 
 * @author omegak
 */
public abstract class Heuristic {
	
	/**
	 * Executes one step of the heuristic producing a new solution.
	 */
	public abstract void execute();
}
