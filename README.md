# Dots-and-Boxes
Java program to use adversarial search to solve dots and boxes game.
- MinMax search
- MinMax search with alpha-beta prunning

## Board
Board class is a state representation of the game board. It contains data and untility functions for MinMax class.
```
public class Board{
  public Board(int d)                         // Constructor take an integer as the board size.
  public int boardScore()                     // Compute the current board state score.
  public void switchTurn()                    // Switch turns with AI or PLAYER
  public void makeBoard()                     // makeBoard is a utility function used to create the double array to represent the board.
  public void linkEdge()                      // linkEdge is a utility function used to link each boxes object to their surrounding edge objects.
  public void evalBoard()                     // evalboard is a function for the AI to evaluate the game board by taking the difference of scores.
  public ArrayList<Board> possibleMove()      // possibleMove returns a list of Board object that took the next available possible moves.
  public ArrayList<Edge> availableEdge()      // availableEdge returns a list of available edge.
  public boolean isFinish()                   // isFinish return true of false checking if the game board is finished.
  public void takeEdge(int x, int y)          // takeEdge is used to take an edge by the players. It will add up the score if it is the last edge for the box.
  public String toString()                    // toString is used to print the representation of the board.
  public Board clone()                        // clone is a utility function for board creation.
 }
```

## MinMax
MinMax class is the MinMax algorithm use to play Dots and Boxes.
```
public class MinMax {
  public MinMax(Board b, int p)                    // Takes a board object and the number of ply the algorithm will search.
  public Board noPruning(Board curr, int depth)    // MinMax algorithm with out alpha-beta pruning.
  public Board alphaBetaPruning(Board curr, int depth, boolean maxLayer, int alpha, int beta)     //MinMax algorithm with alpha-beta pruning. 
 }
```
  
## Example Run
```
public static void main(String[] args) throws CloneNotSupportedException {
  Scanner sc = new Scanner(System.in);
  System.out.println("Enter board size:");
  int boardSize = sc.nextInt();
  Board b = new Board(boardSize);
  System.out.println("Enter number of ply:");
  int numPly = sc.nextInt();
  MinMax mm = new MinMax(b, numPly);

  while( !mm.getBoard().isFinish() ) {
    //Board move = mm.noPruning(mm.getBoard(), 0);
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
```
