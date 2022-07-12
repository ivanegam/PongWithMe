package pwm.pongwithme;
import java.util.Date;

public class GameClock {

    public Date startTime;
    public Date endTime;
    public int seconds;
    public static GameClock myInstance = new GameClock();

    private GameClock(){}

    public void startClock(){};

    public void stopClock(){};

    public static GameClock getInstance(){return myInstance;};

}
