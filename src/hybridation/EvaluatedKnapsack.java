package hybridation;

import problem.Knapsack;

/**
 * A pair for storing a knapsack along with its evaluation.
 * 
 * @author omegak
 */
public class EvaluatedKnapsack implements Comparable<EvaluatedKnapsack> {
    private final int evaluation;
    private final Knapsack knapsack;
    
    /**
     * Constructor of the class. Evaluates a given knapsack and stores it along with its evaluation.
     * 
     * @param k The given knapsack to be stored along with its evaluation.
     */
    public EvaluatedKnapsack(Knapsack k){
        evaluation = k.evaluate();
        knapsack = k;
    }
    
    /**
     * Determines whether this pair is worse than, equal to or better than another evaluated knapsack.
     * 
     * @param k The pair to be compared with.
     * @return -1 if this is worse than, 0 if this is equal to, or 1 if this is better than.
     */
    public int compareTo(EvaluatedKnapsack k) {
    	if (evaluation < k.evaluation) {
    		return -1;
    	} else if (evaluation > k.evaluation) {
    		return 1;
    	} else {
    		return 0;
    	}
    }
    
    /**
     * Returns the evaluation stored.
     * 
     * @return The evaluation stored.
     */
    public int getEvaluation(){ 
    	return evaluation; 
    }
    
    /**
     * Returns a copy of the knapsack stored.
     * 
     * @return A copy of the knapsack stored.
     */
    public Knapsack getKnapsack(){ 
    	return new Knapsack(knapsack); 
    }
}
