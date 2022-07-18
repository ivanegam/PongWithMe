package pwm.pongwithme;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {

    Leaderboard leaderboard = new Leaderboard();

    @FXML
    private Label welcomeText;

    @FXML
    private AnchorPane menuController;

    @FXML
    protected void onPlayButtonClick() {
        Game game = new Game();
        try {
            game.start(new Stage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    protected void onLeaderboardButtonClick() throws Exception {
        leaderboard.start(new Stage());
    }

    @FXML
    protected void onQuitButtonClick() {
        System.exit(0);
    }
}
