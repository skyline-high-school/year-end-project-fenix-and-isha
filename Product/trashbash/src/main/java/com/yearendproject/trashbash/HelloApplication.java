package com.yearendproject.trashbash;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    public static Stage stg;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("game.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Trash Bash");

        Game game = fxmlLoader.getController();

        //used Bro Code's video on "JavaFX KeyEvent"
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                //System.out.println(keyEvent.getCode());

                if (keyEvent.getCode() == KeyCode.A) {

                } else if (keyEvent.getCode() == KeyCode.D) {

                }

                /*
                if (keyEvent.getCode() == KeyCode.LEFT) {
                    game.moveRedLeft();
                } else if (keyEvent.getCode() == KeyCode.RIGHT) {
                    game.moveRedRight();
                }

                 */



            }
        });

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}