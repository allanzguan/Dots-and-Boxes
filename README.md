# Dots-and-Boxes
##### CSC480 Artificial Intelligence
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
  
