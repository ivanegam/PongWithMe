package pwm.pongwithme;

public class Score
{
    private String scorePlace;
    private String playerName;
    private String playerScore;

    Score(String scorePlace, String playerName, String playerScore)
    {
        super();
        this.scorePlace = scorePlace;
        this.playerName = playerName;
        this.playerScore = playerScore;
    }

    public String getScorePlace() {
        return scorePlace;
    }
    public void setScorePlace(String scorePlace)
    {
        this.scorePlace = scorePlace;
    }
    public String getPlayerName() {
        return playerName;
    }
    public String getPlayerScore() {
        return playerScore;
    }

}
