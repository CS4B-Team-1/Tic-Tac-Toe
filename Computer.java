import java.util.ArrayList;

public class Computer {
    private ArrayList<Integer> boardPosition;
    private boolean isMaximizer;

    public Computer(ArrayList<Integer> boardPosition, boolean isMaximizer) {
        
        this.boardPosition = boardPosition;
        this.isMaximizer = isMaximizer;
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

    public void getBestMove() {
        int bestScore = Integer.MIN_VALUE;
        int bestMove;
        int moveValue;
        if (this.isMaximizer)
            moveValue = 1;
        else
            moveValue = -1;

        // for each available move, do the following:
            // make the move on the board
            // call minimax() with depth 0 and isMaximizer
            // undo the move
            // update the best score & move (if the evaluated move has a better score)
        for (Integer move: this.availableMoves()) {
            // set the move on the board
            this.boardPosition.set(move, moveValue);
            // call minimax
            int evaluatedScore = minimax();
            this.boardPosition.set(move, 0);
        }
    }

    public int minimax() {
        return 0;
    }
}
