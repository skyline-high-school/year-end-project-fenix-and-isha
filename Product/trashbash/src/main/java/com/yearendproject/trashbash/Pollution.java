package com.yearendproject.trashbash;

import javafx.scene.image.ImageView;

public class Pollution {
    //falling objects

    private ImageView iv;
    private String type;
    //3 types: trash, recyc, and not
    //which type it is affects its behavior (but not enough to require a separate subclass)


    public Pollution(ImageView iv, String type) {
        this.iv = iv;
        this.type = type;
    }

    public ImageView getIv() {
        return iv;
    }

    public void setIv(ImageView iv) {
        this.iv = iv;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
