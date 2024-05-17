package com.yearendproject.trashbash;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import javax.crypto.spec.PSource;

public class Game {

    @FXML
    public Rectangle blueBin;
    @FXML
    public Rectangle redBin;

    public Game() {
        System.out.println("game start");
    }


}
