/*
 * 
 * Edge class inherit from Node class.
 * Edge class represent the vertical and horizontal on the game board with "-" and "|".
 *  
*/

public class Edge extends Node implements Cloneable{
	boolean taken = false;

	public Edge(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void setTaken() {
		this.taken = true;
		if ( x % 2 == 0 && y % 2 != 0 ) {
			this.symbol = "-";
		}
		
		else if ( x % 2 != 0 && y % 2 == 0 ) {
			this.symbol = "|";
		}
	}
	
	public boolean isTaken() {
		return taken;
	}
	
	public Edge clone() throws CloneNotSupportedException {
		Edge ne = new Edge(this.x, this.y);
		ne.taken = this.taken;
		ne.symbol = this.symbol;

		return ne;
	}
}
