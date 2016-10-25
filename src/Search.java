import java.util.*;

public class Search {	
	public static void main(String[] args) {
		Node node = new Node(new int[] {3,0}, new int[] {3,1}, new int[] {3, 2}, new int[] {3, 3}, null, 0);
		Search searcn = new Search();
		searcn.iterativeDeepening(node, new int[] {1,1}, new int[] {2,1}, new int[] {3, 1});
	}
	
	public void dfs(Node root, int[] goalA, int[] goalB, int[] goalC) {
		Stack<Node> stack = new Stack<>();
		Move m = new Move();
		
		System.out.println("------------------------------\nStarting DFS on:\n" + root);
		stack.add(root);		
		
		while(!stack.isEmpty()) {
			ArrayList<Node> successors = new ArrayList<>();
			Node n = stack.pop();
			if(Arrays.equals(goalA, n.getPositionA()) && Arrays.equals(goalB, n.getPositionB()) && Arrays.equals(goalC, n.getPositionC())) {
				ArrayList<Node> steps = n.sequence(n);
				System.out.println("Finished DFS with depth - " + steps.size() + "\n" + n + "\n------------------------------\nSteps:\n");
				Collections.reverse(steps);
				for(Node step : steps) {
					System.out.println(step);
				}
				break;
			}
			successors.add(m.moveUp(n));
			successors.add(m.moveDown(n));
			successors.add(m.moveLeft(n));
			successors.add(m.moveRight(n));
			
			Collections.shuffle(successors);
			for(Node child : successors) {
				if(child != null) {
					stack.add(child);
				}				
			}
		}
	}
	
	public void bfs(Node root, int[] goalA, int[] goalB, int[] goalC) {
		Queue<Node> queue = new LinkedList<Node>();
		Move m = new Move();
		
		System.out.println("------------------------------\nStarting BFS on:\n" + root);
		queue.add(root);		
		while(!queue.isEmpty()) {
			ArrayList<Node> successors = new ArrayList<>();
			Node n = queue.remove();
			if(Arrays.equals(goalA, n.getPositionA()) && Arrays.equals(goalB, n.getPositionB()) && Arrays.equals(goalC, n.getPositionC())) {
				ArrayList<Node> steps = n.sequence(n);
				System.out.println("Finished BFS with depth - " + steps.size() + "\n" + n + "\n------------------------------\nSteps:\n");
				Collections.reverse(steps);
				for(Node step : steps) {
					System.out.println(step);
				}
				break;
			}
			
			successors.add(m.moveUp(n));
			successors.add(m.moveDown(n));
			successors.add(m.moveLeft(n));
			successors.add(m.moveRight(n));
			
			for(Node child : successors) {
				if(child != null) {
					queue.add(child);
				}				
			}
		}
	}
	
	public void iterativeDeepening(Node root, int[] goalA, int[] goalB, int[] goalC) {		
		Stack<Node> stack = new Stack<>();
		Move m = new Move();
		int i = 0;
		
		System.out.println("------------------------------\nStarting Iterative Deepening on:\n" + root);
		stack.add(root);		
		
		while(!stack.isEmpty()) {
			ArrayList<Node> successors = new ArrayList<>();
			Node n = stack.pop();
			
			if(Arrays.equals(goalA, n.getPositionA()) && Arrays.equals(goalB, n.getPositionB()) && Arrays.equals(goalC, n.getPositionC())) {
				ArrayList<Node> steps = n.sequence(n);
				System.out.println("Finished Iterative Deepening with depth - " + steps.size() + "\n" + n + "\n------------------------------\nSteps:\n");
				
				Collections.reverse(steps);
				for(Node step : steps) {
					System.out.println(step);
				}
				break;
			} else if(n.getLevel() != i) {
				successors.add(m.moveUp(n));
				successors.add(m.moveDown(n));
				successors.add(m.moveLeft(n));
				successors.add(m.moveRight(n));
				
				for(Node child : successors) {
					if(child != null) {
						stack.add(child);
					}				
				}
			}
			if(stack.size() == 0) {
				stack.push(root);
				i++;
			}
		}
	}
	
	public void aStarSearch(Node root, int[] goalA, int[] goalB, int[] goalC) {
		PriorityQueue<Node> queue = new PriorityQueue<>();
		Move m = new Move();
		
		System.out.println("------------------------------\nStarting A* Search on:\n" + root);
		queue.add(root);
		while(!queue.isEmpty()) {
			ArrayList<Node> successors = new ArrayList<>();
			Node n = queue.poll();
			
			if(Arrays.equals(goalA, n.getPositionA()) && Arrays.equals(goalB, n.getPositionB()) && Arrays.equals(goalC, n.getPositionC())) {
				ArrayList<Node> steps = n.sequence(n);
				System.out.println("Finished A* with depth - " + steps.size() + "\n" + n + "\n------------------------------\nSteps:\n");				
				Collections.reverse(steps);
				for(Node step : steps) {
					System.out.println(step);
				}
				break;
			}
			successors.add(m.moveUp(n));
			successors.add(m.moveDown(n));
			successors.add(m.moveLeft(n));
			successors.add(m.moveRight(n));
			
			for(Node child : successors) {
				if(child != null) {
					calculateCost(child, goalA, goalB, goalC);
					queue.add(child);
				}				
			}
		}
	}
	
	private int calculateCost(Node current,  int[] goalA, int[] goalB, int[] goalC) {
		int distanceFromGoalA = Math.abs(current.getPositionA()[0] - goalA[0]) + Math.abs(current.getPositionA()[1] - goalA[1]);
		int distanceFromGoalB = Math.abs(current.getPositionB()[0] - goalB[0]) + Math.abs(current.getPositionB()[1] - goalB[1]);
		int distanceFromGoalC = Math.abs(current.getPositionC()[0] - goalC[0]) + Math.abs(current.getPositionC()[1] - goalC[1]);
		int cost = current.getLevel() + distanceFromGoalA + distanceFromGoalB + distanceFromGoalC;
		current.setCost(cost);
		
		return cost;
	}
}
