import java.util.ArrayList;
import java.util.Collections;

/**
 * Node class implements the basic structure of a node in the tree which contains the configuration (state), the parent, the level the node is on and the cost to get to the Node
 * @author Vedant Chokshi
 */
public class Node implements Comparable<Node> {
	private int level, cost;
	private State state;
	private Node parent = null;
	
	/**
	 * When instantiating a Node, if just give a state, then assume that it is the root node of a tree and set parent to null , node level to 0 and the cost to 0
	 * @param state The state in the puzzle
	 */
	public Node(State state) {
		this.state = state;
		this.parent = null;
		this.level = 0;
		this.cost = 0;
	}
	
	/**
	 * Creates a Node with a state, parent and level
	 * @param state The state in the puzzle
	 * @param parent Parent of the Node
	 * @param level Level of the Node
	 * @param cost Cost to get the Node
	 */
	public Node(State state, Node parent, int level, int cost) {
		this.state = state;
		this.parent = parent;
		this.level = level;
		this.cost = level;
	}

	/**
	 * @return Current level of the Node
	 */
	public int getLevel() {
		return level;
	}
	
	/**
	 * @return Cost of the Node
	 */
	public int getCost() {
		return cost;
	}

	/**
	 * @return State of the puzzle in the Node
	 */
	public State getState() {
		return state;
	}

	/**
	 * Calculates the cost using the Manhattan Distance heuristic between the state of the Node and the final State and the current cost of the Node
	 * @param finalState Final State of the puzzle
	 */
	public void calculateCostUsingHeuristic(State finalState) {
		int distanceFromGoalA = Math.abs(state.getPositionA()[0] - finalState.getPositionA()[0]) + Math.abs(state.getPositionA()[1] - finalState.getPositionA()[1]);
		int distanceFromGoalB = Math.abs(state.getPositionB()[0] - finalState.getPositionB()[0]) + Math.abs(state.getPositionB()[1] - finalState.getPositionB()[1]);
		int distanceFromGoalC = Math.abs(state.getPositionC()[0] - finalState.getPositionC()[0]) + Math.abs(state.getPositionC()[1] - finalState.getPositionC()[1]);
		cost = cost + distanceFromGoalA + distanceFromGoalB + distanceFromGoalC;
	}

	/**
	 * Returns path from root to a given Node
	 * @param nodeToFindPath The Node you want to find the path from
	 * @return Path from the root to the Node entered as the argument
	 */
	public ArrayList<Node> sequence(Node nodeToFindPath) {
		ArrayList<Node> sequence = new ArrayList<>();
		Node node = nodeToFindPath;
		while (node.parent != null) {
			sequence.add(node);
			node = node.parent;
		}
		Collections.reverse(sequence);
		return sequence;
	}
	
	/**
	 * Compares the cost of 2 nodes (used by priority queue for A*)
	 */
	@Override
	public int compareTo(Node n) {
		int cost = this.cost - n.getCost();
		//Dealing with nodes that have the same heuristic
		if(cost == 0) {
			if(this.getLevel() > n.getLevel()) {
				return -1;
			} else if (this.getLevel() == n.getLevel()){
				return 0;
			} else {
				return 1;
			}
		} else {
			return cost;
		} 				
	}
}
