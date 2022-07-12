package pwm.pongwithme;

public class Game {

    public boolean isRunning;
    public int currentScore;
    public GameClock clock;
    public int ballSpeed;

    public void startGame(){};

    public void endGame(){};

    public void updateBallSpeed(){};

    public void movePlayer(){};

    public void moveBall(){};

    public void handleCollision(){};

    //TODO Placeholder for now
    public String checkCollision(InGameObject igo) {
        return "Collision";
    };

    public void redrawBoard(){};


}
