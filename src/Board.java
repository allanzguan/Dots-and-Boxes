/*
 * 
 * Board class is a representation of the states of the game board.
 * Contains utility functions to help MinMax class to compute the right moves.
 * 
 * Board takes one argument as the size of the game board.
 * 
*/
import java.util.ArrayList;

public class Board implements Cloneable{
	int dimension = 0;
	int size = 0;
	Node[][] nodeList = null;
	int aiScore = 0;
	int playerScore = 0;
	boolean finish = false;
	Board parent = null;
	String turn = "AI";
	
	public Board(int d) {
		dimension = d;
		size = d*2+1;
		nodeList = new Node[size][size];
		makeBoard();
		linkEdge();
	}
	
	public void setParent(Board b) {
		parent = b;
	}
	
	public Board getParent() {
		return parent;
	}

	public String getTurn() {
		return turn;
	}
	
	public int getAIScore() {
		return aiScore;
	}
	
	public int getPlayerScore() {
		return playerScore;
	}
	
	public int boardScore() {
		return aiScore - playerScore;
	}
	
	public void switchTurn() {
		if (turn.equals("AI")) {
			turn = "PLAYER";
		}
		else if (turn.equals("PLAYER")) {
			turn = "AI";
		}
	}
	
	/*
	 * makeBoard is a utility function used to create the double array to represent the board.
	*/
	public void makeBoard() {
		for(int i = 0; i < size; i++) {
			for(int j = 0; j <size; j++) {
				if ( (i % 2 == 0 && j % 2 != 0) || ( i % 2 != 0 && j % 2 == 0 ) ) {
					nodeList[i][j] = new Edge(i,j);
				}
				else if ( i % 2 != 0 && j % 2 != 0) {
					nodeList[i][j] = new Box(i,j);
				}
				else {
					nodeList[i][j] = new Dot(i,j);
				}
			}
		}
	}
	
	/*
	 * linkEdge is a utility function used to link each boxes object to their surrounding edge objects.
	*/
	public void linkEdge() {
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				if(nodeList[i][j] instanceof Box) {
					Box temp = (Box)nodeList[i][j];
					temp.setUp((Edge)nodeList[i-1][j]);
					temp.setDown((Edge)nodeList[i+1][j]);
					temp.setLeft((Edge)nodeList[i][j-1]);
					temp.setRight((Edge)nodeList[i][j+1]);
				}
			}
		}
	}
	
	/*
	 * evalboard is a function for the AI to evaluate the game board by taking the difference of scores.
	*/
	public void evalBoard() {
		for(int i = 0; i < size; i++) {
			for(int j = 0; j< size; j++) {
				if(nodeList[i][j] instanceof Box) {
					Box b = (Box)nodeList[i][j];
					if(b.isTaken()) {
						if(b.getOwner().equals("AI")) {
							aiScore = aiScore + b.getValue();
						}
						if(b.getOwner().equals("PLAYER")) {
							playerScore = playerScore + b.getValue();
						}
					}
				}
			}
		}
	}

	/*
	 * possibleMove returns a list of Board object that took the next available possible moves.
	*/
	public ArrayList<Board> possibleMove() throws CloneNotSupportedException{
		ArrayList<Board> pm = new ArrayList<Board>();
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				if(nodeList[i][j] instanceof Edge) {
					Edge e = (Edge)nodeList[i][j];
					if( !e.isTaken() ) {
						Board nextBoard = this.clone();
						nextBoard.setParent(this);
						nextBoard.takeEdge(i, j);											
						pm.add(nextBoard);
					}
				}
			}
		}		
		return pm;
	}
	
	/*
	 * availableEdge returns a list of available edge.
	*/
	public ArrayList<Edge> availableEdge(){
		ArrayList<Edge> ae = new ArrayList<Edge>();
		
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				if(nodeList[i][j] instanceof Edge) {
					Edge e = (Edge)nodeList[i][j];
					if( !e.isTaken() ) {
						ae.add(e);
					}
				}
			}
		}
		return ae;
	}
	
	/*
	 * isFinish return true of false checking if the game board is finished.
	*/
	public boolean isFinish() {
		if(availableEdge().size() == 0) {
			finish = true;
		}
		return finish;
	}
	
	/*
	 * takeEdge is used to take an edge by the players. It will add up the score if it is the last edge for the box.
	*/
	public void takeEdge(int x, int y) {
		Edge e = (Edge)nodeList[x][y];
		e.setTaken();

		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				if(nodeList[i][j] instanceof Box) {
					Box b = (Box)nodeList[i][j];
					if (b.isTaken() && b.getOwner() == "") {
						b.setOwner(turn);
						if (turn == "AI") {
							aiScore = aiScore + b.getValue();
						}
						if (turn == "PLAYER") {
							playerScore = playerScore + b.getValue();
						}
					}
				}
			}
		}		
	}
	
	/*
	 * toString is used to print the representation of the board.
	*/
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				sb.append(nodeList[i][j].toString());
				sb.append(" ");
			}
			sb.append("\n");
		}
		return sb.toString();
	}
	
	/*
	 * clone is a utility function for board creation.
	*/
	public Board clone() throws CloneNotSupportedException {
		Board nb = new Board(this.dimension);
		nb.aiScore = this.aiScore;
		nb.playerScore = this.playerScore;
		Node [][] cloneList = new Node[size][size];
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				if ( (i % 2 == 0 && j % 2 != 0) || ( i % 2 != 0 && j % 2 == 0 ) ) {
					cloneList[i][j] = (Edge)nodeList[i][j].clone();
				}
				else if ( i % 2 != 0 && j % 2 != 0) {
					cloneList[i][j] = (Box)nodeList[i][j].clone();
				}
				else {
					cloneList[i][j] = (Dot)nodeList[i][j].clone();
				}
			}
		}
		nb.nodeList = cloneList;
		nb.parent = this.parent;
		nb.finish = this.finish;
		nb.turn = this.turn;
		nb.linkEdge();
		return nb;
	}
}
