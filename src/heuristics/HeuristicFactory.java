package heuristics;

/**
 * Enum that serves the purpose of factory for {@link Heuristic} subclasses.
 * 
 * @author omegak
 */
public enum HeuristicFactory {
	LOCAL_SEARCH {
		public Heuristic create() {
			return new LocalSearch();
		}
	},
	
	SIMULATED_ANNEALING {
		// TODO add constants
		private static final int T = 10;
		
		public Heuristic create() {
			// TODO add variables to constructor
			return new SimulatedAnnealing();
		}
	},
	
	TABU_SEARCH {
		public Heuristic create() {
			return new TabuSearch();
		}
	};

	/**
	 * Creates a {@link Heuristic} instance of the corresponding type.
	 *  
	 * @return a {@link Heuristic} instance of the corresponding type.
	 */
	public abstract Heuristic create();
}
