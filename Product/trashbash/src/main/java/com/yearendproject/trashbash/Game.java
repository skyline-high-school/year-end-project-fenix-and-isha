package com.yearendproject.trashbash;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import javax.crypto.spec.PSource;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Game implements Initializable {

    /*
    @FXML
    public Rectangle blueBin;
    @FXML
    public Rectangle redBin;

     */
    @FXML
    public AnchorPane pane;

    public Bin trashBin;
    public Bin recycBin;

    public Game() {
        System.out.println("game start");

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Image image = new Image("RECYCLIN BIN FINAL.png");
        ImageView iv = new ImageView(image);
        iv.setPreserveRatio(true);
        iv.setFitHeight(50);
        pane.getChildren().add(iv);
        recycBin = new Bin(iv);
        recycBin.moveX(60);

        image = new Image("TRASH FINAL.png");
        iv = new ImageView(image);
        iv.setPreserveRatio(true);
        iv.setFitHeight(50);
        pane.getChildren().add(iv);
        trashBin = new Bin(iv);
        trashBin.moveX(500);
    }


    public void moveBlueLeft() {
        //moveX(blueBin, -8);
        moveX(recycBin, -8);
    }
    public void moveBlueRight() {
        //moveX(blueBin, 8);
        moveX(recycBin, 8);
    }

    public void moveRedLeft() {
        //moveX(redBin, -8);
        moveX(trashBin, -8);
    }

    public void moveRedRight() {
        //moveX(redBin, 8);
        moveX(trashBin, 8);
    }

    private void moveX(Rectangle bin, int x) {
        bin.setX(bin.getX() + x);
    }

    private void moveX(Bin bin, int x) {
        bin.moveX(x);
    }

}
