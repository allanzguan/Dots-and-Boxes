/*
 * 
 * Dot class inherit from Node class.
 * Dot object represent the dots on the game board with "*".
 * 
*/
public class Dot extends Node implements Cloneable{
	
	public Dot(int x, int y) {
		this.x = x;
		this.y = y;
		this.symbol = "*";
	}

	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
