package com.yearendproject.trashbash;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

public class Bin {

    private ImageView image;

    public Bin(ImageView image) {
        this.image = image;
        image.setY(350);
    }

    public void moveX(int x) {
        image.setX(image.getX() + x);
    }

    public void moveLeft() {
        moveX(-8);
    }
    public void moveRight() {
        moveX(8);
    }


    public void setImage(ImageView image) {
        this.image = image;
    }

    public ImageView getImage() {
        return image;
    }
}
