import java.util.ArrayList;

public class Node implements Comparable<Node> {
	private int[] positionA, positionB, positionC, positionAgent;
	private int level, cost;
	private Node parent = null;
	
	public Node(int[] positionA, int[] positionB, int[] positionC, int[] positionAgent, Node parent, int level) {
		this.positionA = positionA;
		this.positionB = positionB;
		this.positionC = positionC;
		this.positionAgent = positionAgent;
		this.parent = parent;
		this.level = level;
	}

	public int[] getPositionA() {
		return positionA;
	}

	public int[] getPositionB() {
		return positionB;
	}

	public int[] getPositionC() {
		return positionC;
	}

	public int[] getPositionAgent() {
		return positionAgent;
	}
	
	public int getLevel() {
		return level;
	}
	
	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public ArrayList<Node> sequence(Node nodeToFindPath) {
		ArrayList<Node> sequence = new ArrayList<>();
		Node node = nodeToFindPath;
		while (node.parent != null) {
			sequence.add(node);
			node = node.parent;
		}
		return sequence;
	}
	
	public String toString() {
		String[][] grid = {{"-","-","-","-"}, 
				{"-","-","-","-"}, 
				{"-","-","-","-"}, 
				{"-","-","-","-"}};
		grid[positionA[0]][positionA[1]] = "A";
		grid[positionB[0]][positionB[1]] = "B";
		grid[positionC[0]][positionC[1]] = "C";
		grid[positionAgent[0]][positionAgent[1]] = "#";
		
		String output = "";
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                output += " " + grid[x][y];
            }
            output += "\n";
        } 
        return output;
	}

	@Override
	public int compareTo(Node n) {
		return this.getCost() - n.getCost();
	}
	
}
