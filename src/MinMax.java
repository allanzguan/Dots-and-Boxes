
/*
 * MinMax class is the MinMax algorithm use to play Dots and Boxes.
 * MinMax contains two method, noPruning and alphaBetaPruning.
 * 
 * MinMax takes two arguments, it takes the board object and number of ply the algorithm will use.
 * 	
*/
import java.util.*;

public class MinMax {
	int ply = 0;
	
	Board board = null;
	
	public MinMax(Board b, int p) {
		ply = p;
		board = b;
	}
	
	public void setBoard(Board b) {
		board = b;
	}
	
	public Board getBoard() {
		return board;
	}
	
	/*
	 * MinMax algorithm with out alpha-beta pruning.
	*/
	public Board noPruning(Board curr, int depth) throws CloneNotSupportedException {
		Board rtBoard = curr;
		
		if(depth == ply || curr.isFinish()) {
			return curr;
		}
		else {
			PriorityQueue<Board> maxList = new PriorityQueue<Board> (10, (a,b) -> b.boardScore() - a.boardScore());
			ArrayList<Board> possibleMax = curr.possibleMove();
			for (Board pmax : possibleMax) {
				if(pmax.isFinish()) {
					return pmax;
				}
				PriorityQueue<Board> minList = new PriorityQueue<Board> (10, (a,b) -> a.boardScore() - b.boardScore());				
				pmax.switchTurn();
				ArrayList<Board> possibleMin = pmax.possibleMove();			
				for(Board pmin : possibleMin) {
					if(pmin.isFinish()) {
						return pmin.getParent();
					}
					pmin.switchTurn();
					Board nextPly = noPruning(pmin, depth+1);
					minList.add(nextPly.getParent());
				}
				Board min  = minList.poll();				
				maxList.add(min);
			}		
			Board max = maxList.poll();
			if(max.getParent() == board) {
				rtBoard = max;
			}
			else {
				rtBoard = max.getParent();
			}
		}		
		return rtBoard;
	}

	
	/*
	 * MinMax algorithm with alpha-beta pruning. 
	*/
	public Board alphaBetaPruning(Board curr, int depth, boolean maxLayer, int alpha, int beta) throws CloneNotSupportedException {

		if (depth == ply || curr.isFinish()) {
			return curr;
		}
		else {
			if(maxLayer) {
				Board rtBoard = curr;
				for(Board pmax: curr.possibleMove()) {
					pmax.switchTurn();
					Board child = alphaBetaPruning(pmax, depth+1, false, alpha, beta);
					if (child.boardScore() > alpha) {
						rtBoard = pmax;
						alpha = child.boardScore();
					}
					if(alpha >= beta) {
						break;
					}
				}
				return rtBoard;
			}
			else {
				Board rtBoard = curr;
				for(Board pmin: curr.possibleMove()) {
					pmin.switchTurn();
					Board child = alphaBetaPruning(pmin, depth+1, true, alpha, beta);
					if(child.boardScore() < beta) {
						rtBoard = pmin;
						beta = child.boardScore();
					}
					if(alpha >= beta) {
						break;
					}
				}
				return rtBoard;
			}
		}  
	} 

	public static void main(String[] args) throws CloneNotSupportedException {
	
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter board size:");
		int boardSize = sc.nextInt();
		Board b = new Board(boardSize);
		System.out.println("Enter number of ply:");
		int numPly = sc.nextInt();
		MinMax mm = new MinMax(b, numPly);
	
		while( !mm.getBoard().isFinish() ) {
//			Board move = mm.noPruning(mm.getBoard(), 0);
			Board move = mm.alphaBetaPruning(mm.getBoard(), 0, true, Integer.MIN_VALUE, Integer.MAX_VALUE);
			
			mm.setBoard(move);
			System.out.println("\n\nAI'S TURN\n");
			System.out.println(move);
			System.out.println("AI: " + move.getAIScore() + "  PLAYER: " + move.getPlayerScore());
			System.out.println("____________________________");
			//Scanner sc = new Scanner(System.in);
			System.out.println("\nPLAYER'S TURN");
			System.out.println("\nEnter X:");
			int inX = sc.nextInt();
			System.out.println("Enter Y:");
			int inY = sc.nextInt();
			Board move2 = mm.getBoard(); 
			move2.takeEdge(inX, inY);
			move2.switchTurn();
			mm.setBoard(move2);			
			System.out.println(move2);
			System.out.println("AI: " + move2.getAIScore() + "  PLAYER: " + move2.getPlayerScore());
			System.out.println("____________________________");			
		}
		
		if(mm.getBoard().getAIScore() > mm.getBoard().getPlayerScore()) {
			System.out.println("AI WON !!!");
		}
		else if (mm.getBoard().getAIScore() < mm.getBoard().getPlayerScore()) {
			System.out.println("PLAYER WON !!! ");
		}
		else {
			System.out.println("TIE !!!");
		}
	}
}