import java.util.Arrays;

public class Move {
	
	public Node doMovement(Node n, int[] newAgentPosition) {
		Node x = null;
		if(Arrays.equals(n.getPositionA(), newAgentPosition)) {
			x = new Node(n.getPositionAgent(), n.getPositionB(), n.getPositionC(), newAgentPosition, n, n.getLevel() + 1);
		} else if(Arrays.equals(n.getPositionB(), newAgentPosition)) {
			x = new Node(n.getPositionA(), n.getPositionAgent(), n.getPositionC(), newAgentPosition, n, n.getLevel() + 1);
		} else if(Arrays.equals(n.getPositionC(), newAgentPosition)) {
			x = new Node(n.getPositionA(), n.getPositionB(), n.getPositionAgent(), newAgentPosition, n, n.getLevel() + 1);
		} else {
			x = new Node(n.getPositionA(), n.getPositionB(), n.getPositionC(), newAgentPosition, n, n.getLevel() + 1);
		}
		return x;
	}
	
	public Node moveUp(Node n) {
		if(n.getPositionAgent()[0] - 1 >= 0) {
			return doMovement(n, new int[] {n.getPositionAgent()[0] - 1, n.getPositionAgent()[1]});
		} else {
			return null;
		}		
	}
	
	public Node moveRight(Node n) {
		if(n.getPositionAgent()[1] + 1 <= 3) {
			return doMovement(n, new int[] {n.getPositionAgent()[0], n.getPositionAgent()[1] + 1});
		} else {
			return null;
		}		
	}
	
	public Node moveDown(Node n) {
		if(n.getPositionAgent()[0] + 1 <= 3) {
			return doMovement(n, new int[] {n.getPositionAgent()[0] + 1, n.getPositionAgent()[1]});
		} else {
			return null;
		}		
	}
	
	public Node moveLeft(Node n) {
		if(n.getPositionAgent()[1] - 1 >= 0) {
			return doMovement(n, new int[] {n.getPositionAgent()[0], n.getPositionAgent()[1] - 1});
		} else {
			return null;
		}		
	}
}
