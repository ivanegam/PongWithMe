package pwm.pongwithme;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class MenuController {
    @FXML
    private Label welcomeText;

    @FXML
    private AnchorPane menuController;

    @FXML
    protected void onPlayButtonClick() {
    }

    @FXML
    protected void onLeaderboardButtonClick() {
    }

    @FXML
    protected void onQuitButtonClick() {
        System.exit(0);
    }
}
