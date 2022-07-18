package pwm.pongwithme;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Leaderboard  extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the FXML data into loader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("leaderboard-view.fxml"));

        // Create a new scene from that FXML data
        Scene root = new Scene(loader.load());

        //Calling requestFocus allows the keyevents to be read when application is ran
        root.getRoot().requestFocus();

        primaryStage.setTitle("Leaderboard");
        primaryStage.setScene(root);
        primaryStage.show();
    }

}
