package pwm.pongwithme;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MenuController {
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
    protected void onLeaderboardButtonClick() {
    }

    @FXML
    protected void onQuitButtonClick() {
        System.exit(0);
    }
}
