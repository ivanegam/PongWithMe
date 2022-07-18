package pwm.pongwithme;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import static java.lang.Float.parseFloat;
import java.util.Collections;
import java.util.ResourceBundle;

public class LeaderboardController implements Initializable {

    @FXML
    private AnchorPane leaderboardScene;

    //Adapted from: https://stackoverflow.com/q/47425336
    static ArrayList<ArrayList<String>> scores = new ArrayList();
    static ArrayList<Float> iScores = new ArrayList();
    static ArrayList<ArrayList<String>> top5Scores = new ArrayList();

    /**
     * Converts a duration string to duration in seconds, millisecond precision
     *
     * @param dur   Duration string
     * @return      Duration in seconds, millisecond precision
     */
    public static float durToFlt(String dur){
        float durDbl;
        String[] splitlist = dur.split(":|\\.");

        durDbl = parseFloat(splitlist[0]) * 3600 + parseFloat(splitlist[1]) * 60 + parseFloat(splitlist[2]) * 1 + parseFloat(splitlist[3]) / 1000;

        return durDbl;
    }

    /**
     * Gets all scores from the scorefile
     */
    public static void getScores() {

        try {
            for(String ln : Files.readAllLines(Paths.get("scores.txt"))){

                String score = ln.split(";")[0];
                String player = "Player";
                ArrayList<String> combo = new ArrayList();

                try {
                    player = ln.split(";")[1];
                } catch (Exception e){
                    System.out.println("Player name missing!");
                }

                combo.add(player);
                combo.add(score);

                scores.add(combo);
                iScores.add(durToFlt(score));

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Gets the highest player–score combo and removes it from the list
     */
    public static void getAndRemoveHighestScore()
    {
        ArrayList<String> combo = new ArrayList();

        for(int i = 0; i < iScores.size(); i++){
            if(iScores.get(i) == Collections.max(iScores)){

                System.out.println(iScores.get(i));

                combo.add(scores.get(i).get(0));
                combo.add(scores.get(i).get(1));

                top5Scores.add(combo);

                scores.remove(i);
                iScores.remove(i);

                return;
            }
        }
    }

    /**
     * Returns the top5 highest-performing players
     *
     * @return ArrayList of top5 players
     */
    public static ArrayList<ArrayList<String>> getTop5Scores(){
        getScores();

        for(int i = 0; i < 5; i++){
            getAndRemoveHighestScore();
        }

        return top5Scores;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {

        scores = getTop5Scores();

        //// vertical box
        HBox hBox = new HBox();
        hBox.setPrefSize(1100, 700);
        hBox.setAlignment(Pos.CENTER);

        // vertical box for the players
        VBox PlayerName = new VBox();
        PlayerName.setAlignment(Pos.CENTER_LEFT);
        PlayerName.setPadding(new Insets(25, 25, 25, 25));

        // vertical box for the scores
        VBox Score = new VBox();
        Score.setAlignment(Pos.CENTER_RIGHT);
        Score.setPadding(new Insets(25, 25, 25, 25));

        // player1 name
        Label player1 = new Label(scores.get(0).get(0));
        player1.setAlignment(Pos.TOP_LEFT);
        player1.setPadding(new Insets(5, 5, 5, 5));
        player1.setStyle("-fx-font: 16 arial;");

        // player2 name
        Label player2 = new Label(scores.get(1).get(0));
        player2.setAlignment(Pos.TOP_LEFT);
        player2.setPadding(new Insets(5, 5, 5, 5));
        player2.setStyle("-fx-font: 16 arial;");

        // player3 name
        Label player3 = new Label(scores.get(2).get(0));
        player3.setAlignment(Pos.TOP_LEFT);
        player3.setPadding(new Insets(5, 5, 5, 5));
        player3.setStyle("-fx-font: 16 arial;");

        // player4 name
        Label player4 = new Label(scores.get(3).get(0));
        player4.setAlignment(Pos.TOP_LEFT);
        player4.setPadding(new Insets(5, 5, 5, 5));
        player4.setStyle("-fx-font: 16 arial;");

        // player5 name
        Label player5 = new Label(scores.get(4).get(0));
        player5.setAlignment(Pos.TOP_LEFT);
        player5.setPadding(new Insets(5, 5, 5, 5));
        player5.setStyle("-fx-font: 16 arial;");

        // player1 score
        Label text1 = new Label();
        text1.setAlignment(Pos.CENTER_LEFT);
        text1.setText(scores.get(0).get(1));
        text1.setPadding(new Insets(5, 5, 5, 5));
        text1.setStyle("-fx-font: 16 arial;");

        // player2 score
        Label text2 = new Label();
        text2.setAlignment(Pos.CENTER_RIGHT);
        text2.setText(scores.get(1).get(1));
        text2.setPadding(new Insets(5, 5, 5, 5));
        text2.setStyle("-fx-font: 16 arial;");

        // player3 score
        Label text3 = new Label();
        text3.setAlignment(Pos.CENTER_RIGHT);
        text3.setText(scores.get(2).get(1));
        text3.setPadding(new Insets(5, 5, 5, 5));
        text3.setStyle("-fx-font: 16 arial;");

        // player4 score
        Label text4 = new Label();
        text4.setAlignment(Pos.CENTER_RIGHT);
        text4.setText(scores.get(3).get(1));
        text4.setPadding(new Insets(5, 5, 5, 5));
        text4.setStyle("-fx-font: 16 arial;");

        // player5 score
        Label text5 = new Label();
        text5.setAlignment(Pos.CENTER_RIGHT);
        text5.setText(scores.get(4).get(1));
        text5.setPadding(new Insets(5, 5, 5, 5));
        text5.setStyle("-fx-font: 16 arial;");

        PlayerName.getChildren().addAll(player1, player2, player3, player4, player5);
        Score.getChildren().addAll(text1, text2, text3, text4, text5);

        hBox.getChildren().addAll(PlayerName, Score);

        leaderboardScene.getChildren().add(hBox);

    }

}
