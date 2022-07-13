package pwm.pongwithme;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class GameClock extends Thread {


    private static GameClock myInstance;


    Timeline timeline;
    LocalTime time = LocalTime.parse("00:00:00");
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");

    @FXML
    private Label timerLabel;

    private GameClock()
    {

    }

    public void stopClock()
    {

    };

    public void setTime(long time) {

    }

    public void getTime() {

    }



    public static GameClock getInstance(){
        if(myInstance == null){
            myInstance = new GameClock();
        }
        return myInstance;

    };


}
