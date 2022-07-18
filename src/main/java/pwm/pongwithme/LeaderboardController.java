package pwm.pongwithme;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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

    ////https://gist.github.com/sharifulislam52/d17b4e1654a8214046d409b0a7d63c3b - Used this for implementing TableView for each score


    // Initialized all elements of GUI
    @FXML
    private TableView<Score> leaderboardTableView;
    @FXML
    private TableColumn<Score, String> playerScore;
    @FXML
    private TableColumn<Score, String> scorePlace;
    @FXML
    private TableColumn<Score, String> playerName;

    //Adapted from: https://stackoverflow.com/q/47425336
    static ArrayList<Score> scores = new ArrayList();
    static ArrayList<Float> iScores = new ArrayList();
    static ArrayList<Score> top5Scores = new ArrayList();

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
            int lineNumber = 0;
            for(String ln : Files.readAllLines(Paths.get("scores.txt"))){
                try {
                    lineNumber ++;

                    Score score = new Score(String.valueOf(lineNumber),ln.split(";")[1], ln.split(";")[0]);

                    scores.add(score);
                    iScores.add(durToFlt(score.getPlayerScore()));
                } catch (Exception e){
                    System.out.println("Player name missing!");
                }


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
        for(int i = 0; i < iScores.size(); i++){
            if(iScores.get(i) == Collections.max(iScores)){

                System.out.println(iScores.get(i));

                Score currentScore = new Score(String.valueOf(i + 1), scores.get(i).getPlayerName(), scores.get(i).getPlayerScore());

                top5Scores.add(currentScore);

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
    public static ArrayList<Score> getTop5Scores(){
        getScores();

        for(int i = 0; i < 5; i++){
            getAndRemoveHighestScore();
        }

        //Setting the score place for each player
        for(int i = 0; i < 5; i++){
            top5Scores.get(i).setScorePlace(String.valueOf(i + 1));
        }

        return top5Scores;
    }

    //https://gist.github.com/sharifulislam52/d17b4e1654a8214046d409b0a7d63c3b
    public ObservableList<Score> list;

    private void positionLeaderBoard() {
        //Positioning the game message label to top center of the screen
        leaderboardTableView.setMaxWidth(Double.MAX_VALUE);
        AnchorPane.setLeftAnchor(leaderboardTableView, 0.0);
        AnchorPane.setRightAnchor(leaderboardTableView, 0.0);
        AnchorPane.setTopAnchor(leaderboardTableView, 0.0);
        AnchorPane.setBottomAnchor(leaderboardTableView, 300.00);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {

        scores = getTop5Scores();

       list = FXCollections.observableArrayList(
            scores
            );

        //https://gist.github.com/sharifulislam52/d17b4e1654a8214046d409b0a7d63c3b
        scorePlace.setCellValueFactory(new PropertyValueFactory<Score, String>("scorePlace"));
        playerName.setCellValueFactory(new PropertyValueFactory<Score, String>("playerName"));
        playerScore.setCellValueFactory(new PropertyValueFactory<Score, String>("playerScore"));
        leaderboardTableView.setItems(list);

        leaderboardTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        leaderboardTableView.setId("my-table");
    }

}
