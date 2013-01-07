package metaheuristics;

/**
 * Abstract class to extends specific metaheuristics from it.
 * 
 * @author omegak
 */
public abstract class Metaheuristic {
	
	/**
	 * Executes one step of the metaheuristic producing a new solution.
	 */
	public abstract void execute();
}
