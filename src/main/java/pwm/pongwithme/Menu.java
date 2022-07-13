package pwm.pongwithme;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Menu extends Application {

    public String currentState;

    public void initiateAction(){};

    public void startGame(){};

    public void showLeaderBoard(){};

    public void Quit(){};

    @Override
    public void start(Stage primaryStage) throws Exception{
        // Load the FXML data into loader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("menu-view.fxml"));

        // Create a new scene from that FXML data
        Scene root = new Scene(loader.load());

        //Calling requestFocus allows the keyevents to be read when application is run
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
