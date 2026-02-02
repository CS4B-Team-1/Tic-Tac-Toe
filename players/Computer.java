package players;
import java.util.ArrayList;

public class Computer {
    private ArrayList<Integer> boardPosition;
    private boolean isComputerMaximizer;

    private static final int MINIMIZER_VALUE = -1;
    private static final int MAXIMIZER_VALUE = 1;

    public Computer(ArrayList<Integer> boardPosition, boolean isComputerMaximizer) {
        this.boardPosition = boardPosition;
        this.isComputerMaximizer = isComputerMaximizer;
    }

    // Loops through the board list, and returns all indices where the
    // value at that index is 0 (an "empty" tile)
    private ArrayList<Integer> availableMoves() {
        ArrayList<Integer> moves = new ArrayList<>();
        for (int i = 0; i < this.boardPosition.size(); i++) {
            if (boardPosition.get(i) == 0)
                moves.add(i);
        }
        return moves;
    }

    public boolean isMaximizer() {
        return this.isComputerMaximizer;
    }

    public int getBestMove() {
        int bestScore = Integer.MIN_VALUE;
        int bestMove = Integer.MIN_VALUE;
        int moveValue;
        if (this.isComputerMaximizer)
            moveValue = MAXIMIZER_VALUE;
        else
            moveValue = MINIMIZER_VALUE;

        // for each available move, do the following:
            // make the move on the board
            // call minimax() with depth 0 and isMaximizer
            // undo the move
            // update the best score & move (if the evaluated move has a better score)
        for (Integer move: this.availableMoves()) {
            // set the move on the board
            this.boardPosition.set(move, moveValue);
            // call minimax with depth 0 and the maximizer/minimizer value of the Computer
            int evaluatedScore = minimax(0, this.isComputerMaximizer); // returns a score evaluation of the move
            // undo the move (reset the array representation of the move to "empty" -> 0)
            this.boardPosition.set(move, 0);
            // if the score is better than the best score, update the best move and best score
            if ((evaluatedScore * moveValue) > bestScore) {  // multiply the score by the maximizer/minimizer value here to always return a positive value for ease of evaluation
                bestScore = evaluatedScore;
                bestMove = move;
            }
        }
        // returns the index of the best move to make
        return bestMove;
    }

    public int minimax(int depth, boolean isMaximizer) {
        return 0;
    }
}
