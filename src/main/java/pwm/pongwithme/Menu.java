package pwm.pongwithme;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Menu extends Application
{

    @Override
    public void start(Stage primaryStage) throws Exception{
        // Load the FXML data into loader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Menu.class.getResource("menu-view.fxml"));

        // Create a new scene from that FXML data
        Scene root = new Scene(loader.load());

        //Calling requestFocus allows the keyevents to be read when application is run
        root.getRoot().requestFocus();

        //Adding CSS file to this scene
        //https://www.section.io/engineering-education/add-an-external-css-file-to-a-javafx-application/#:~:text=Adding%20it%20via%20the%20SceneBuilder%20or%20Hardcoding%20it&text=In%20the%20SceneBuilder%20view%2C%20click,CSS%20file%2C%20and%20that's%20it.
        root.getStylesheets().add(Leaderboard.class.getResource("menuStyle.css").toExternalForm());

        // Set the scene and display the stage
        primaryStage.setTitle("Pong With Me");
        primaryStage.setScene(root);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
