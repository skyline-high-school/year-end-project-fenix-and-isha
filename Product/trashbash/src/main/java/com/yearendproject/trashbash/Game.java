package com.yearendproject.trashbash;

import javafx.application.Platform;
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
import java.util.*;

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

    private int points;
    private Timer timer = new Timer();
    private static int i;

    //private static double paneWidth;

    private ArrayList<Pollution> pollutionList = new ArrayList<>();

    public Game() {
        System.out.println("game start");
        points = 0;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //adding the bins

        Image image = new Image("RECYCLIN BIN FINAL.png");
        ImageView iv = new ImageView(image);
        iv.setPreserveRatio(false);
        iv.setFitHeight(50);
        iv.setFitWidth(80);
        pane.getChildren().add(iv);
        recycBin = new Bin(iv);
        recycBin.moveX(60);

        image = new Image("TRASH FINAL.png");
        iv = new ImageView(image);
        iv.setPreserveRatio(false);
        iv.setFitHeight(50);
        iv.setFitWidth(80);
        pane.getChildren().add(iv);
        trashBin = new Bin(iv);
        trashBin.moveX(500);


        //adding the pollution

        addPollution();
        addPollution();



        //falling objects animation

        i = 0;
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    if (points > 3) {
                        cancel();
                        timer.cancel();
                        System.out.println("You lost.");
                    }
                });
                i += 80; //incrementing timer
                //TODO use the total time to display a high score
                nextFallFrame();

            }
        };
        timer.scheduleAtFixedRate(timerTask, 1000, 80);
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

    private void moveX(Bin bin, int x) {
        bin.moveX(x);
    }

    //GAME MAIN METHOD
    public static void main(String[] args) {
        System.out.println("MAIN GAME START!");

    }

    public void nextFallFrame() {

        for (int j = 0; j < pollutionList.size(); j++) {
            ImageView iv = pollutionList.get(j).getIv();
            iv.setY(iv.getY() + 5);

            if ((!pollutionList.get(j).getType().equals("not")) && (iv.getY() > pane.getChildren().get(0).getBoundsInLocal().getHeight() - 10)) {
                System.out.println("You lose!");
                System.exit(0);
            } else if (iv.getY() > pane.getChildren().get(0).getBoundsInLocal().getHeight() - 100) {

                double px = pollutionList.get(j).getIv().getX();
                double rbx = recycBin.getImage().getX();
                double tbx = trashBin.getImage().getX();
                if (Math.abs(px - rbx) < 80 && (pollutionList.get(j).getType().equals("recyc"))) {
                    respawnPollution(pollutionList.get(j));
                    System.out.println("recyc caught");
                } else if (Math.abs(px - tbx) < 80 && (pollutionList.get(j).getType().equals("trash"))) {
                    respawnPollution(pollutionList.get(j));
                    System.out.println("trash caught");
                } else if (((Math.abs(px - rbx) < 80 || Math.abs(px - tbx) < 80) && pollutionList.get(j).getType().equals("not")) && (iv.getY() > pane.getChildren().get(0).getBoundsInLocal().getHeight() - 50)) {
                    System.out.println("Woah, living sea creatures aren't trash! You lose.");
                    System.exit(0);
                }
            }
        }

    }

    public void addPollution(String imageName, String type) {
        //spawns a new specified type of pollution

        final double paneWidth = pane.getChildren().get(0).getBoundsInLocal().getWidth();
        Image image = new Image(imageName);
        ImageView iv = new ImageView(image);
        iv.setPreserveRatio(true);
        iv.setFitWidth(30);
        pane.getChildren().add(iv);
        pollutionList.add(new Pollution(iv, type));
        Random random = new Random();
        iv.setX(random.nextDouble(paneWidth - iv.getFitWidth())); //places pollution at random x position on stage
    }

    public void addPollution() {
        //spawns a new random type of pollution

        final double paneWidth = pane.getChildren().get(0).getBoundsInLocal().getWidth();
        String[] imageNamePool = {"CHIPS FINAL.png", "CHIPS FINAL.png", "SODA FINAL.png", "SODA FINAL.png", "STARFISH FINAL.png"};
        Random rand = new Random();
        String name = imageNamePool[rand.nextInt(imageNamePool.length)];
        Image image = new Image(name);
        ImageView iv = new ImageView(image);
        iv.setPreserveRatio(true);
        iv.setFitWidth(30);
        pane.getChildren().add((pane.getChildren().size()-3), iv);
        // -1 for the dif between size and index, -2 to keep the two bins at the very front

        String type;
        if (name.equals("CHIPS FINAL.png")) {
            type = "trash";
        } else if (name.equals("SODA FINAL.png")) {
            type = "recyc";
        } else {
            type = "not";
        }
        pollutionList.add(new Pollution(iv, type));
        iv.setX(rand.nextDouble(paneWidth - iv.getFitWidth())); //places pollution at random x position on stage
    }

    public void respawnPollution(Pollution pol) {
        //REspawns a random type of pollution -- reuses the existing pollution objects

        final double paneWidth = pane.getChildren().get(0).getBoundsInLocal().getWidth();
        String[] imageNamePool = {"CHIPS FINAL.png", "CHIPS FINAL.png", "SODA FINAL.png", "SODA FINAL.png", "STARFISH FINAL.png"};
        Random rand = new Random();
        String name = imageNamePool[rand.nextInt(imageNamePool.length)];
        Image image = new Image(name);
        pol.getIv().setImage(image);
        //ImageView iv = pol.getIv();
        //iv.setPreserveRatio(true);
        //iv.setFitWidth(30);

        String type;
        if (name.equals("CHIPS FINAL.png")) {
            type = "trash";
        } else if (name.equals("SODA FINAL.png")) {
            type = "recyc";
        } else {
            type = "not";
        }

        pol.getIv().setX(rand.nextDouble(paneWidth - pol.getIv().getFitWidth())); //places pollution at random x position on stage
        pol.getIv().setY(0);
        //pol.setIv(iv);
        pol.setType(type);
    }
}
