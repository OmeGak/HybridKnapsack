package metaheuristics;

/**
 * Enum that serves the purpose of factory for {@link Metaheuristic} subclasses.
 * 
 * @author omegak
 */
public enum MetaheuristicFactory {
	LOCAL_SEARCH {
		public Metaheuristic create() {
			return new LocalSearch();
		}
	},
	
	SIMULATED_ANNEALING {
		public Metaheuristic create() {
			return new SimulatedAnnealing();
		}
	},
	
	TABU_SEARCH {
		public Metaheuristic create() {
			return new TabuSearch();
		}
	};

	/**
	 * Creates a {@link Metaheuristic} instance of the corresponding type.
	 *  
	 * @return a {@link Metaheuristic} instance of the corresponding type.
	 */
	public abstract Metaheuristic create();
}
