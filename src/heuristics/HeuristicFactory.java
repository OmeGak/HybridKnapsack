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
		private static final int TEMPERATURE = 200;
		private static final int FREEZING_RATE = 5;
		
		public Heuristic create() {
			return new SimulatedAnnealing(TEMPERATURE,FREEZING_RATE);
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
