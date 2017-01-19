import java.util.*;
/**
 * Search Class contains all the the search method with a main method to run the methods
 * @author Vedant Chokshi
 */
public class Search {
	
	/**
	 * Runs search methods
	 */
	public static void main(String[] args) {
		State start = new State(new int[] {4,4}, new int[] {1,2}, new int[] {1,3}, new int[] {4, 3}, new int[] {2, 1});
		State goal = new State(null, new int[] {2,2}, new int[] {2,3}, new int[] {2, 4}, null);
		Node node = new Node(start);
		Search search = new Search();
//		Runs search methods
//		search.BFS(node, goal);
//		search.DFS(node, goal);
//		search.IDS(node, goal);
		search.aStar(node, goal);
	}
	
	/**
	 * Depth-First Search
	 * @param root Root Node of the tree which contains the initial state of the problem
	 * @param goal Final state of the problem (solution)
	 */
	public void DFS(Node root, State goal) {
		int nodesExpanded = 0;
		Stack<Node> stack = new Stack<>();
		Move m = new Move();
		
		System.out.println("Starting Depth-First Search on:\n" + root.getState());
		stack.add(root);		
		while(!stack.isEmpty()) {
			ArrayList<Node> successors = new ArrayList<>();
			//Pop from top of the stack
			Node current = stack.pop();
			//Check if the solution is found
			if(checkGoal(current.getState(), goal)) {
				ArrayList<Node> steps = current.sequence(current);
				System.out.println("Finished Depth-First Search with depth - " + current.getCost() + " and nodes expanded - " + nodesExpanded + "\n" + current.getState() + "\nSteps:\n");
				for(Node step : steps) {
					System.out.println(step.getState());
				}
				break;
			}
			//Expands Node if solution not found
			nodesExpanded++;
			successors.add(m.moveUp(current));
			successors.add(m.moveDown(current));
			successors.add(m.moveLeft(current));
			successors.add(m.moveRight(current));
			//Add successors to the stack (randomly for DFS)
			Collections.shuffle(successors);
			for(Node child : successors) {
				if(child != null) {
					stack.add(child);
				}				
			}
		}
	}
	
	/**
	 * Breadth-First Search
	 * @param root Root Node of the tree which contains the initial state of the problem
	 * @param goal Final state of the problem (solution)
	 */
	public void BFS(Node root, State goal) {
		int nodesExpanded = 0;
		Queue<Node> queue = new LinkedList<Node>();
		Move m = new Move();
		
		System.out.println("Starting Breadth-First Search on:\n" + root.getState());
		queue.add(root);
		while(!queue.isEmpty()) {
			ArrayList<Node> successors = new ArrayList<>();
			//Remove queue head
			Node current = queue.remove();
			//Check if the solution is found
			if(checkGoal(current.getState(), goal)) {
				ArrayList<Node> steps = current.sequence(current);
				System.out.println("Finished Breadth-First Search with depth - " + current.getCost() + " and nodes expanded - " + nodesExpanded + "\n" + current.getState() + "\nSteps:\n");
				for(Node step : steps) {
					System.out.println(step.getState());
				}
				break;
			}
			//Expands Node if solution not found
			nodesExpanded++;			
			successors.add(m.moveUp(current));
			successors.add(m.moveDown(current));
			successors.add(m.moveLeft(current));
			successors.add(m.moveRight(current));
			//Add successors to the queue
			for(Node child : successors) {
				if(child != null) {
					queue.add(child);
				}				
			}
		}
	}
	
	/**
	 * Iterative Deepening Search
	 * @param root Root Node of the tree which contains the initial state of the problem
	 * @param goal Final state of the problem (solution)
	 */
	public void IDS(Node root, State goal) {
		//Initially set maxDepth to 0
		int maxDepth = 0, nodesExpanded = 0;
		Stack<Node> stack = new Stack<>();
		Move m = new Move();
		
		System.out.println("Starting Iterative Deepening Search on:\n" + root.getState());
		stack.add(root);		
		while(!stack.isEmpty()) {
			ArrayList<Node> successors = new ArrayList<>();
			//Pop from top of the stack
			Node current = stack.pop();
			//Checks if the popped Node is a solution
			if(checkGoal(current.getState(), goal)) {
				ArrayList<Node> steps = current.sequence(current);
				System.out.println("Finished Iterative Deepening Search with depth - " + current.getCost() + " and nodes expanded - " + nodesExpanded + "\n" + current.getState() + "\nSteps:");
				for(Node step : steps) {
					System.out.println(step.getState());
				}
				break;
			//Expands Node if the depth of the Node is less than the maxDepth
			} else if(current.getLevel() < maxDepth) {
				nodesExpanded++;
				successors.add(m.moveUp(current));
				successors.add(m.moveDown(current));
				successors.add(m.moveLeft(current));
				successors.add(m.moveRight(current));
				for(Node child : successors) {
					if(child != null) {
						stack.add(child);
					}				
				}
			}
			//If stack size = 0, meaning that there is no solution for the current maxDepth, increase value of maxDepth by 1 and adds the root to the stack so the search can be started again with a higher maximum depth
			if(stack.size() == 0) {
				stack.push(root);
				maxDepth++;
			}	
			
		}		
	}
	
	/**
	 * A* Search
	 * @param root Root Node of the tree which contains the initial state of the problem
	 * @param goal Final state of the problem (solution)
	 */
	public void aStar(Node root, State goal) {
		int nodesExpanded = 0;
		PriorityQueue<Node> queue = new PriorityQueue<>();
		Move m = new Move();
		
		System.out.println("Starting A* Search on:\n" + root.getState());
		queue.add(root);
		while(!queue.isEmpty()) {
			ArrayList<Node> successors = new ArrayList<>();
			//Remove queue head
			Node current = queue.poll();
			//Check if head of the queue is the solution
			if(checkGoal(current.getState(), goal)) {
				ArrayList<Node> steps = current.sequence(current);
				System.out.println("Finished A* Search with depth - " + current.getCost() + " and nodes expanded - " + nodesExpanded + "\n" + current.getState() + "\nSteps:");				
				for(Node step : steps) {
					System.out.println(step.getState());
				}
				break;
			}
			//Expands Node if solution not found
			nodesExpanded++;
			successors.add(m.moveUp(current));
			successors.add(m.moveDown(current));
			successors.add(m.moveLeft(current));
			successors.add(m.moveRight(current));
			//Add successors to the queue
			for(Node child : successors) {
				if(child != null) {
					child.calculateCostUsingHeuristic(goal);
					queue.add(child);
				}				
			}
		}
	}
	
	/**
	 * Method used to check if final state is achieved
	 * @param current State wished to be check
	 * @param goal State wished to be check with
	 * @return True if current and goal are equal, else return false
	 */
	private boolean checkGoal(State current, State goal) {
		if(Arrays.equals(current.getPositionA(), goal.getPositionA()) && Arrays.equals(current.getPositionB(), goal.getPositionB()) && Arrays.equals(current.getPositionC(), goal.getPositionC())) {
			return true;
		} else {
			return false;
		}
	}
}