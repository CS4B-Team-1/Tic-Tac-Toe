package controller;

import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class BoardController {
    public void toggleBoardButton(MouseEvent event) {
        MouseButton button = event.getButton();
        Button boardButton = (Button)event.getSource();
        if (button.compareTo(MouseButton.PRIMARY) == 0) {
            boardButton.setText("X");
            boardButton.setTextFill(Color.RED);
        }
        else if (button.compareTo(MouseButton.SECONDARY) == 0){
            boardButton.setText("O");
            boardButton.setTextFill(Color.BLUE);
        } else {
            System.out.println("unknown button clicked");
        }
    }
}
