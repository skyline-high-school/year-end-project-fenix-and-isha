package com.yearendproject.trashbash;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.*;

public class Game implements Initializable {

    public boolean tLeft;
    public boolean tRight;
    public boolean rLeft;
    public boolean rRight;

    public Bin trashBin;
    public Bin recycBin;

    @FXML
    public AnchorPane pane;
    @FXML
    public VBox popupVbox;
    @FXML
    public Label popupLabel;
    @FXML
    public Text popupText;
    @FXML
    public Button playAgainButton;
    @FXML
    public Button quitButton;
    @FXML
    public Label scoreLabel;

    private int trashPoints;
    private int recycPoints;
    private Timer timer = new Timer();
    private int elapsedTime = 0;

    private boolean lost;
    private boolean firstRound;

    //private static double paneWidth;

    private ArrayList<Pollution> pollutionList = new ArrayList<>();

    public Game() {
        System.out.println("game start");
        trashPoints = 0;
        recycPoints = 0;
        elapsedTime = 0;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        firstRound = true;
        scoreLabel.setText("Trash: " + trashPoints + "\nRecycling: " + recycPoints + "\nElapsed time: " + elapsedTime);
        //scoreLabel.setText("Trash: 0\nRecycling: 0\nElapsed time: 0");

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

        //initial instruction popup
        popupLabel.setText("How to play:");
        popupText.setText("The beach is polluted with trash and recycling. Move the recycling bin with the 'a' and 'd' keys to catch the recycling (the soda cans), " +
                "and move the trash bin with the left and right arrow keys to catch the trash (the chip bags). Be careful not to get a living animal (the sea stars) in either of the bins though!");
        popupVbox.setVisible(true);

        /*

        //adding the pollution

        addPollution();
        addPollution();


        //falling objects animation

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    if (lost) {
                        timer.cancel();
                        cancel();
                        //System.out.println("You lost.");
                    }
                });
                elapsedTime += 80; //incrementing timer
                nextFallFrame();

            }
        };
        timer.scheduleAtFixedRate(timerTask, 1000, 80);

         */

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while(!lost) {
                        if (rLeft) {
                            moveBlueLeft();
                        } else if (rRight) {
                            moveBlueRight();
                        }
                        if (tLeft) {
                            moveRedLeft();
                        } else if(tRight) {
                            moveRedRight();
                        }
                        Thread.sleep(20);
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread.start();
    }

    private final int d = 10;
    public void moveBlueLeft() {
        //moveX(blueBin, -8);
        moveX(recycBin, -d);
    }
    public void moveBlueRight() {
        //moveX(blueBin, 8);
        moveX(recycBin, d);
    }

    public void moveRedLeft() {
        //moveX(redBin, -8);
        moveX(trashBin, -d);
    }

    public void moveRedRight() {
        //moveX(redBin, 8);
        moveX(trashBin, d);
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

            //moves every pollution object down
            iv.setY(iv.getY() + 5);

            //handles what happens when objects reach the bins or the bottom of the screen
            if ((!pollutionList.get(j).getType().equals("not")) && (iv.getY() > pane.getChildren().get(0).getBoundsInLocal().getHeight() - 10)) {
                System.out.println("You lose!");
                lost = true;
            } else if (iv.getY() > pane.getChildren().get(0).getBoundsInLocal().getHeight() - 100) {
                double px = pollutionList.get(j).getIv().getX();
                double rbx = recycBin.getImage().getX();
                double tbx = trashBin.getImage().getX();
                if (Math.abs(px+15 - rbx-40) < 55 && (pollutionList.get(j).getType().equals("recyc"))) {
                    respawnPollution(pollutionList.get(j));
                    System.out.println("recyc caught");
                    updateScore();
                } else if (Math.abs(px+15 - tbx-40) < 55 && (pollutionList.get(j).getType().equals("trash"))) {
                    //+15 and -40 to use the objects' centers as references
                    respawnPollution(pollutionList.get(j));
                    System.out.println("trash caught");
                    updateScore();
                } else if (((Math.abs(px+15 - rbx-40) < 55 || Math.abs(px+15 - tbx-40) < 55) && pollutionList.get(j).getType().equals("not")) && (iv.getY() > pane.getChildren().get(0).getBoundsInLocal().getHeight() - 50)) {
                    System.out.println("Woah, living sea creatures aren't trash! You lose.");
                    lost = true;
                } else if (pollutionList.get(j).getType().equals("not") && (iv.getY() > pane.getChildren().get(0).getBoundsInLocal().getHeight() - 30)) {
                    respawnPollution(pollutionList.get(j));
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
        pol.setType(type);
    }

    public void updateScore() {
        scoreLabel.setText("Trash: " + trashPoints + "\nRecycling: " + recycPoints + "\nElapsed time: " + elapsedTime);
    }

    public void onQuitButtonPressed(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void onPlayButtonPressed(ActionEvent actionEvent) {
        if (firstRound) {
            firstRound = false;
            //adding the pollution

            addPollution();
            addPollution();
        } else {
            //reset scores
            trashPoints = 0;
            recycPoints = 0;
            elapsedTime = 0;
            scoreLabel.setText("Trash: " + trashPoints + "\nRecycling: " + recycPoints + "\nElapsed time: " + elapsedTime);
            //scoreLabel.setText("Trash: 0\nRecycling: 0\nElapsed time: 0");

            //move the bins back to their original positions
            recycBin.moveX(60);
            trashBin.moveX(500);


            //Respawning all pollution objects
            for (int i = 0; i < pollutionList.size(); i++) {
                respawnPollution(pollutionList.get(i));
            }

        }

        popupVbox.setVisible(false);

        //falling objects animation
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    if (lost) {
                        timer.cancel();
                        cancel();
                        //System.out.println("You lost.");
                    }
                    elapsedTime += 80; //incrementing timer
                    nextFallFrame();
                });
            }
        };
        timer.scheduleAtFixedRate(timerTask, 1000, 80);
    }
}