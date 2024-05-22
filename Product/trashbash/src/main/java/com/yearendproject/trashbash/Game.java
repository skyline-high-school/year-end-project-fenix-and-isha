package com.yearendproject.trashbash;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import javax.crypto.spec.PSource;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Game implements Initializable {

    @FXML
    public Rectangle blueBin;
    @FXML
    public Rectangle redBin;
    @FXML
    public AnchorPane pane;

    public Bin trashBin;
    public Bin recycBin;

    public Game() {
        System.out.println("game start");

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            pane.getChildren().add(FXMLLoader.load(getClass().getResource("bin.fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //ImageView iv = (ImageView) pane.getChildren().get(0);
        //System.out.println(pane.getChildren().get(0).toString());
        //trashBin = (Bin) iv;
        /*
        System.out.println(trashBin.getX());
        trashBin.setX(15);
        System.out.println(trashBin.getX());

         */
    }

    /*
    public void moveBlueLeft() {
        moveX(blueBin, -8);
    }
    public void moveBlueRight() {
        moveX(blueBin, 8);
    }

    public void moveRedLeft() {
        moveX(redBin, -8);
    }

    public void moveRedRight() {
        moveX(redBin, 8);
    }

    private void moveX(Rectangle bin, int x) {
        bin.setX(bin.getX() + x);
    }
    */
}
