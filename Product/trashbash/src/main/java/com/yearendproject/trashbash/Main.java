package com.yearendproject.trashbash;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public static Stage stg;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("game.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Trash Bash");

        Game game = fxmlLoader.getController();

        /*

        //keyboard controls
        //used Bro Code's video on "JavaFX KeyEvent"
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                //System.out.println(keyEvent.getCode());

                if (keyEvent.getCode() == KeyCode.A) {
                    game.moveBlueLeft();
                } else if (keyEvent.getCode() == KeyCode.D) {
                    game.moveBlueRight();
                }

                if (keyEvent.getCode() == KeyCode.LEFT) {
                    game.moveRedLeft();
                } else if (keyEvent.getCode() == KeyCode.RIGHT) {
                    game.moveRedRight();
                }

                if (keyEvent.getCode() == KeyCode.LEFT && keyEvent.getCode() == KeyCode.A) {
                    game.moveBlueLeft();
                    game.moveRedLeft();
                }

            }
        });

         */

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.A) {
                    game.rLeft = true;
                }
                if (keyEvent.getCode() == KeyCode.D) {
                    game.rRight = true;
                }
                if (keyEvent.getCode() == KeyCode.LEFT) {
                    game.tLeft = true;
                }
                if (keyEvent.getCode() == KeyCode.RIGHT) {
                    game.tRight = true;
                }
            }
        });

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.A) {
                    game.rLeft = false;
                }
                if (keyEvent.getCode() == KeyCode.D) {
                    game.rRight = false;
                }
                if (keyEvent.getCode() == KeyCode.LEFT) {
                    game.tLeft = false;
                }
                if (keyEvent.getCode() == KeyCode.RIGHT) {
                    game.tRight = false;
                }
            }
        });

        stage.setScene(scene);
        stage.show();
        Game.main(null); //added second main method to keep the game main method separate/abstracted and in its corresponding controller class
    }



    public static void main(String[] args) {
        launch();
    }
}