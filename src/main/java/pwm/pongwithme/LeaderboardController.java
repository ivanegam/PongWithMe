package pwm.pongwithme;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import static java.lang.Float.parseFloat;
import java.util.Collections;

public class LeaderboardController {

    static ArrayList<ArrayList<String>> scores = new ArrayList();
    static ArrayList<Float> iScores = new ArrayList();
    static ArrayList<ArrayList<String>> top5Scores = new ArrayList();

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
            for(String ln : Files.readAllLines(Paths.get("scores.txt"))){

                String score = ln.split(";")[0];
                String player = "Player";
                ArrayList<String> combo = new ArrayList();

                try {
                    player = ln.split(";")[1];
                } catch (Exception e){
                    System.out.println("Player name missing!");
                }

                combo.add(player);
                combo.add(score);

                scores.add(combo);
                iScores.add(durToFlt(score));

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Gets the highest player–score combo and removes it from the list
     */
    public static void getAndRemoveHighestScore(){
        ArrayList<String> combo = new ArrayList();

        for(int i = 0; i < iScores.size(); i++){
            if(iScores.get(i) == Collections.max(iScores)){

                System.out.println(iScores.get(i));

                combo.add(scores.get(i).get(0));
                combo.add(scores.get(i).get(1));

                top5Scores.add(combo);

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
    public static ArrayList<ArrayList<String>> getTop5Scores(){
        getScores();

        for(int i = 0; i < 5; i++){
            getAndRemoveHighestScore();
        }

        return top5Scores;
    }

}
