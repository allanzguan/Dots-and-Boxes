/*
 * 
 * Node class is the parent class for Dot class, Edge class and Box class.
 * Node is used to model the entities on the Dots and Boxes board.
 * Node contains the location and the visual representation for each nodes on the board.
 * 
*/
public class Node implements Cloneable{
	String symbol = " ";
	int x = 0;
	int y = 0;
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setSymbol(String s) {
		this.symbol = s;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public String toString() {
		return symbol;
	}
	
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
