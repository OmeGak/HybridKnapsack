package hybridation;

import heuristics.HeuristicFactory;

import java.util.ArrayList;

import problem.Knapsack;

/**
 * Enumeration of coordination modes.
 *  
 * @author omegak
 */
public enum CoordinatorMode {
	COOPERATIVE("-c") {
		public ArrayList<Agent> generateAgents(Knapsack knapsack) {
			ArrayList<Agent> agents = new ArrayList<Agent>();
			
			for (HeuristicFactory heuristicType : HeuristicFactory.values()) {
				agents.add(new Agent(heuristicType.create(), knapsack));
			}
			
			return agents;
		}
	},
	
	LOCAL_SEARCH("-l") {
		public ArrayList<Agent> generateAgents(Knapsack knapsack) {
			ArrayList<Agent> agents = new ArrayList<Agent>();
			agents.add(new Agent(HeuristicFactory.LOCAL_SEARCH.create(), knapsack));
			return agents;
		}
	},
	
	SIMULATED_ANNEALING("-s") {
		public ArrayList<Agent> generateAgents(Knapsack knapsack) {
			ArrayList<Agent> agents = new ArrayList<Agent>();
			agents.add(new Agent(HeuristicFactory.SIMULATED_ANNEALING.create(), knapsack));
			return agents;
		}
	},
	
	TABU_SEARCH("-t") {
		public ArrayList<Agent> generateAgents(Knapsack knapsack) {
			ArrayList<Agent> agents = new ArrayList<Agent>();
			agents.add(new Agent(HeuristicFactory.TABU_SEARCH.create(), knapsack));
			return agents;
		}
	};
	
	/** String identifier. */
	private final String stringId;
	
	private CoordinatorMode(String id) {
		this.stringId = id;
	}

	/**
	 * Parses a string to return a mode. Cooperative mode is returned as default.
	 * 
	 * @param input The string to be parsed.
	 * @return A coordinator mode. Cooperative as default.
	 */
	public static CoordinatorMode ParseCoordinatorMode(String input) {
		if (input.equalsIgnoreCase(LOCAL_SEARCH.stringId)) {
			return LOCAL_SEARCH;
		} else if (input.equalsIgnoreCase(SIMULATED_ANNEALING.stringId)) {
			return SIMULATED_ANNEALING;
		} else if (input.equalsIgnoreCase(TABU_SEARCH.stringId)) {
			return TABU_SEARCH;
		} else {
			return COOPERATIVE;
		}
	}
	
	/**
	 * Generates the agents for the coordinator.
	 * 
	 * @param knapsack The knapsack to be passed to the agents.
	 * @return A list of agents.
	 */
	public abstract ArrayList<Agent> generateAgents(Knapsack knapsack);
}
