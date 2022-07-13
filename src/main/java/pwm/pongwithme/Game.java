package pwm.pongwithme;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Game extends Application{
    //test
    public boolean isRunning;
    public int currentScore;
    public GameClock clock;
    public int ballSpeed;

    public void startGame(){};

    public void endGame(){};

    public void updateBallSpeed(){};

    public void movePlayer(){};

    public void moveBall(){};

    public void handleCollision(){};

    //TODO Placeholder for now
    public String checkCollision(InGameObject igo) {
        return "Collision";
    };

    public void redrawBoard(){};

    @Override
    public void start(Stage primaryStage) throws Exception{
        // Load the FXML data into loader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("game-view.fxml"));

        // Create a new scene from that FXML data
        Scene root = new Scene(loader.load());

        //Calling requestFocus allows the keyevents to be read when application is ran
        root.getRoot().requestFocus();

        // Set the scene and display the stage
        primaryStage.setTitle("Pong With Me");
        primaryStage.setScene(root);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


}
