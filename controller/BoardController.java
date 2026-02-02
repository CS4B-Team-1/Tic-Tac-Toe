package controller;
import players.Computer;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class BoardController {

    ArrayList<Integer> boardGrid;

    @FXML
    private Button topLeft;
    @FXML
    private Button topCenter;
    @FXML
    private Button topRight;
    @FXML
    private Button middleLeft;
    @FXML
    private Button middleCenter;
    @FXML
    private Button middleRight;
    @FXML
    private Button bottomLeft;
    @FXML
    private Button bottomCenter;
    @FXML
    private Button bottomRight;

    private int numActiveTiles;

    private final int GRID_SIZE = 9; 
    private final String PLAYER_X = "X";
    private final String PLAYER_O = "O"; 

    public void toggleBoardButton(MouseEvent event) {
        MouseButton button = event.getButton();
        Button boardButton = (Button)event.getSource();
        if (button.compareTo(MouseButton.PRIMARY) == 0) {
            if (boardButton.getText().isEmpty()) {
                numActiveTiles++;
            }
            boardButton.setText(PLAYER_X);
            boardButton.setTextFill(Color.RED);
        }
        else if (button.compareTo(MouseButton.SECONDARY) == 0){
            if (boardButton.getText().isEmpty()) {
                numActiveTiles++;
            }
            boardButton.setText(PLAYER_O);
            boardButton.setTextFill(Color.BLUE);
        } else {
            System.out.println("unknown button clicked");
        }

        String outcomeString = winnerCheck();
        if (outcomeString != null) {
            try { 
                // create new window for popup
                Stage outcomePopup = new Stage();
                // create FXML loader object to load
                FXMLLoader outcomeLoader = new FXMLLoader(getClass().getResource("..\\OutcomePopup.fxml"));
                // load FXML onto Scene
                outcomePopup.setScene(new Scene(outcomeLoader.load()));

                // grab Controller instance to modify Label text
                OutcomePopupController outcomePopupController = outcomeLoader.getController();
                // set Label text to outcome
                outcomePopupController.setWinner(outcomeString);
                // sets up popup to reset board when closed
                outcomePopup.setOnHidden(hiddenEvent -> resetBoard());
                // display popup
                outcomePopup.show();

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private String winnerCheck() {
        // check all rows
        String rowsResult = checkRows();
        // check all columns
        String columnsResult = checkColumns();
        // check both diagonals
        String diagonalsResult = checkDiagonals();

        String outcomeString = " wins";
        if (rowsResult != null)
            outcomeString = rowsResult + outcomeString;
        else if (columnsResult != null)
            outcomeString = columnsResult + outcomeString;
        else if (diagonalsResult != null)
            outcomeString = diagonalsResult + outcomeString;
        else if (numActiveTiles == (GRID_SIZE)) {
            outcomeString = "Tie game";
        } else 
            outcomeString = null;

        return outcomeString;
    } 

    private String checkRows() {
        // check first row
        String stringCheck = topLeft.getText();
        if (!stringCheck.isEmpty()) {
            if ((topCenter.getText().compareTo(stringCheck) == 0) && (topRight.getText().compareTo(stringCheck) == 0))
                return stringCheck;
        }
        // check middle row
        stringCheck = middleLeft.getText();
        if (!stringCheck.isEmpty()) {
            if ((middleCenter.getText().compareTo(stringCheck) == 0) && (middleRight.getText().compareTo(stringCheck) == 0))
                return stringCheck;
        }
        // check bottom row
        stringCheck = bottomLeft.getText();
        if (!stringCheck.isEmpty()) {
            if ((bottomCenter.getText().compareTo(stringCheck) == 0) && (bottomRight.getText().compareTo(stringCheck) == 0))
                return stringCheck;
        }
        // if all checks fail, return null
        return null;
    }

    private String checkColumns() {
        // check first column
        String stringCheck = topLeft.getText();
        if (!stringCheck.isEmpty()) {
            if ((middleLeft.getText().compareTo(stringCheck) == 0) && (bottomLeft.getText().compareTo(stringCheck) == 0))
                return stringCheck;
        }
        // check middle column
        stringCheck = topCenter.getText();
        if (!stringCheck.isEmpty()) {
            if ((middleCenter.getText().compareTo(stringCheck) == 0) && (bottomCenter.getText().compareTo(stringCheck) == 0))
                return stringCheck;
        }
        // check last column
        stringCheck = topRight.getText();
        if (!stringCheck.isEmpty()) {
            if ((middleRight.getText().compareTo(stringCheck) == 0) && (bottomRight.getText().compareTo(stringCheck) == 0))
                return stringCheck;
        }
        // if all checks fail, return null
        return null;
    }

    private String checkDiagonals() {
        // check top-left to bottom-right diagonal
        String stringCheck = topLeft.getText();
        if (!stringCheck.isEmpty()) {
            if ((middleCenter.getText().compareTo(stringCheck) == 0) && (bottomRight.getText().compareTo(stringCheck) == 0))
                return stringCheck;
        }
        // check bottom-left to top-right diagonal
        stringCheck = bottomLeft.getText();
        if (!stringCheck.isEmpty()) {
            if ((middleCenter.getText().compareTo(stringCheck) == 0) && (topRight.getText().compareTo(stringCheck) == 0))
                return stringCheck;
        }
        // if all checks fail, return null
        return null;
    }

    // resets the board by setting all button text to blank and number of active tiles to 0.
    private void resetBoard() {
        topLeft.setText("");
        topCenter.setText("");
        topRight.setText("");
        middleLeft.setText("");
        middleCenter.setText("");
        middleRight.setText("");
        bottomLeft.setText("");
        bottomCenter.setText("");
        bottomRight.setText("");
        numActiveTiles = 0;
    }

    public void computerTurn() {
        // create the computer
        Computer computer = new Computer(new ArrayList<>(this.boardGrid), false);
        int move = computer.getBestMove();
        if (move < 0)
            return; // TODO: if a negative value was returned, something went wrong (possibly a tie state?)

        // TODO: update the GUI board and boardGrid array
    }
}
