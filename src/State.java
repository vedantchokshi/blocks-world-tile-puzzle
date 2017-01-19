/**
 * State Class implements the basic structure of a State of the Puzzle
 * @author Vedant Chokshi
 */
public class State {
	private int[] gridSize, positionA, positionB, positionC, positionAgent;
	private int[][] blockedTiles;
	
	/**
	 * Create a State without blocked tiles
	 * @param gridSize Grid Size
	 * @param positionA Position of A
	 * @param positionB Position of B
	 * @param positionC Position of C
	 * @param positionAgent Position Agent
	 */
	public State(int[] gridSize, int[] positionA, int[] positionB, int[] positionC, int[] positionAgent) {
		this.gridSize = gridSize;
		this.positionA = positionA;
		this.positionB = positionB;
		this.positionC = positionC;
		this.positionAgent = positionAgent;
	}
	
	/**
	 * Create a State without blocked tiles
	 * @param gridSize Grid Size
	 * @param positionA Position of A
	 * @param positionB Position of B
	 * @param positionC Position of C
	 * @param positionAgent Position Agent
	 * @param blockedTiles Array of blocked tiles
	 */
	public State(int[] gridSize, int[] positionA, int[] positionB, int[] positionC, int[] positionAgent, int[]...blockedTiles) {
		this.gridSize = gridSize;
		this.positionA = positionA;
		this.positionB = positionB;
		this.positionC = positionC;
		this.positionAgent = positionAgent;
		this.blockedTiles = blockedTiles;
	}
	
	/**
	 * @return Grid Size
	 */
	public int[] getGridSize() {
		return gridSize;
	}

	/**
	 * @return Return position A
	 */
	public int[] getPositionA() {
		return positionA;
	}

	/**
	 * @return Return position B
	 */
	public int[] getPositionB() {
		return positionB;
	}

	/**
	 * @return Return position C
	 */
	public int[] getPositionC() {
		return positionC;
	}

	/**
	 * @return Return position Agent
	 */
	public int[] getPositionAgent() {
		return positionAgent;
	}
	
	/**
	 * @return Return all the blocked tiles
	 */
	public int[][] getBlockedTiles() {
		return blockedTiles;
	}

	/**
	 * Print out the node in a set format
	 */
	public String toString() {		
		String output = "";
		
        for (int y = 1; y <= gridSize[0]; y++) {
            for (int x = 1; x <= gridSize[1]; x++) {
                if(x == positionA[0] && y == positionA[1]) {
                	output += "[A] ";
                } else if(x == positionB[0] && y == positionB[1]) {
                	output += "[B] ";
                } else if(x == positionC[0] && y == positionC[1]) {
                	output += "[C] ";
                } else if(x == positionAgent[0] && y == positionAgent[1]) {
                	output += "[#] ";
                } else {
                	boolean flag = false;
                	if(blockedTiles != null) {
                		for(int[] coord : blockedTiles) {
                    		if(coord[0] == x && coord[1] == y) {
                    			output += "[-] ";
                    			flag = true;
                    			break;
                    		}
                    	}
                	}
                	if(!flag) {
                		output += "[ ] ";
                	}                	
                }            	
            }
            output += "\n";
        } 
        return output;
	}
}
