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
		private static final int TABU_LIST_SIZE = 12;
		
		public Heuristic create() {
			return new TabuSearch(TABU_LIST_SIZE);
		}
	};

	/**
	 * Creates a {@link Heuristic} instance of the corresponding type.
	 *  
	 * @return a {@link Heuristic} instance of the corresponding type.
	 */
	public abstract Heuristic create();
}
