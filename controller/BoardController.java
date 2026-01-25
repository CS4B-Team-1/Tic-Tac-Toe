package controller;

import javafx.event.Event;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class BoardController {
    public void toggleBoardButton(MouseEvent event) {
        MouseButton button = event.getButton();
        if (button.compareTo(MouseButton.PRIMARY) == 0) {
            System.out.println("left button clicked");
        }
        else if (button.compareTo(MouseButton.SECONDARY) == 0){
            System.out.println("right button clicked");
        } else {
            System.out.println("unknown button clicked");
        }
    }
}
