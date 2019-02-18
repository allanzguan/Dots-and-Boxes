/*
 * 
 * Box class inherit from Node class.
 * Box object represent weighted box value on the board with randomly generated 1 to 5.
 * 
*/
import java.util.*;

public class Box extends Node implements Cloneable{
	int value = 0;
	Edge up = null;
	Edge down = null;
	Edge left = null;
	Edge right = null;
	boolean taken = false;
	String owner = "";
	
	public Box(int x, int y) {
		this.x = x;
		this.y = y;
		value = new Random().nextInt(5)+1;
		this.symbol = String.valueOf(value);
	}

	public void setUp(Edge e) {
		up = e;
	}
	
	public void setDown(Edge e) {
		down = e;
	}
	
	public void setLeft(Edge e) {
		left = e;
	}
	
	public void setRight(Edge e) {
		right = e;
	}
	
	public void setOwner(String s) {
		owner = s;
	}
	
	public Edge getUp() {
		return this.up;
	}
	
	public Edge getDown() {
		return down;
	}
	
	public Edge getLeft() {
		return left;
	}
	
	public Edge getRight() {
		return right;
	}
	
	public String getOwner() {
		return owner;
	}
	
	public int getValue() {
		return value;
	}
	
	public boolean isTaken() {
		if( up.isTaken() && down.isTaken() && left.isTaken() && right.isTaken()) {
			taken = true;
		}
		return taken;
	}

	public Box clone() throws CloneNotSupportedException {
		Box nb = new Box(this.x, this.y);
		nb.value = this.value;
		nb.owner = this.owner;
		nb.symbol = this.symbol;
		return nb;
	}
}
