package pwm.pongwithme;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Leaderboard  extends Application {
//Adapted from: https://stackoverflow.com/q/47425336

    //TODO Read (and sort) from scorefile, using sample scores now
    public int P1_Score = 1000;
    public int P2_Score = 900;
    public int P3_Score = 800;
    public int P4_Score = 400;
    public int P5_Score = 200;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Load the FXML data into loader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("leaderboard-view.fxml"));

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
        Label player1 = new Label("Gold");
        player1.setAlignment(Pos.TOP_LEFT);
        player1.setPadding(new Insets(5, 5, 5, 5));
        player1.setStyle("-fx-font: 16 arial;");

        // player2 name
        Label player2 = new Label("Silver");
        player2.setAlignment(Pos.TOP_LEFT);
        player2.setPadding(new Insets(5, 5, 5, 5));
        player2.setStyle("-fx-font: 16 arial;");

        // player3 name
        Label player3 = new Label("Bronze");
        player3.setAlignment(Pos.TOP_LEFT);
        player3.setPadding(new Insets(5, 5, 5, 5));
        player3.setStyle("-fx-font: 16 arial;");

        // player4 name
        Label player4 = new Label("Runner-up");
        player4.setAlignment(Pos.TOP_LEFT);
        player4.setPadding(new Insets(5, 5, 5, 5));
        player4.setStyle("-fx-font: 16 arial;");

        // player5 name
        Label player5 = new Label("Consolation prize");
        player5.setAlignment(Pos.TOP_LEFT);
        player5.setPadding(new Insets(5, 5, 5, 5));
        player5.setStyle("-fx-font: 16 arial;");

        // player1 score
        Label text1 = new Label();
        text1.setAlignment(Pos.CENTER_LEFT);
        text1.setText(Integer.toString(P1_Score));
        text1.setPadding(new Insets(5, 5, 5, 5));
        text1.setStyle("-fx-font: 16 arial;");

        // player2 score
        Label text2 = new Label();
        text2.setAlignment(Pos.CENTER_RIGHT);
        text2.setText(Integer.toString(P2_Score));
        text2.setPadding(new Insets(5, 5, 5, 5));
        text2.setStyle("-fx-font: 16 arial;");

        // player3 score
        Label text3 = new Label();
        text3.setAlignment(Pos.CENTER_RIGHT);
        text3.setText(Integer.toString(P3_Score));
        text3.setPadding(new Insets(5, 5, 5, 5));
        text3.setStyle("-fx-font: 16 arial;");

        // player4 score
        Label text4 = new Label();
        text4.setAlignment(Pos.CENTER_RIGHT);
        text4.setText(Integer.toString(P4_Score));
        text4.setPadding(new Insets(5, 5, 5, 5));
        text4.setStyle("-fx-font: 16 arial;");

        // player5 score
        Label text5 = new Label();
        text5.setAlignment(Pos.CENTER_RIGHT);
        text5.setText(Integer.toString(P5_Score));
        text5.setPadding(new Insets(5, 5, 5, 5));
        text5.setStyle("-fx-font: 16 arial;");

        PlayerName.getChildren().addAll(player1, player2, player3, player4, player5);
        Score.getChildren().addAll(text1, text2, text3, text4, text5);

        hBox.getChildren().addAll(PlayerName, Score);
        primaryStage.setTitle("Leaderboard");

        Scene scene = new Scene(hBox);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


}
