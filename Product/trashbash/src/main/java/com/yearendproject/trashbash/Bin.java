package com.yearendproject.trashbash;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

public class Bin extends ImageView {

    @FXML
    public ImageView image;

    private void moveX(int x) {
        //setX(getX() + x);
    }

    public void moveLeft() {
        moveX(-8);
    }
    public void moveRight() {
        moveX(8);
    }
}
