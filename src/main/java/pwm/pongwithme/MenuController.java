package pwm.pongwithme;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    Leaderboard leaderboard = new Leaderboard();
    Game game = new Game();

    @FXML
    private Label menuTitle;

    @FXML
    protected void onPlayButtonClick() {
        try {
            game.start(new Stage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    protected void onLeaderboardButtonClick() {
        try {
            leaderboard.start(new Stage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    protected void onQuitButtonClick() {
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        menuTitle.setId("menuTitle");
    }
}
