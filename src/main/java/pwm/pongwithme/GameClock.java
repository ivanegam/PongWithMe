package pwm.pongwithme;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class GameClock extends Thread {


    private static GameClock myInstance;

    private LocalTime time = LocalTime.parse("00:00:00.00");
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");

    //Following Singleton pattern
    private GameClock()
    {

    }

    public void setTime(LocalTime paramTime)
    {
        time = paramTime;
    }

    public void incrementClock(long nanos) {
        //There is no plus millis. So I have to add 1 million nanoseconds for every millisecond.
        time = time.plusNanos(1000000);

    }

    public String getFormattedTime()
    {
        return time.format(dtf);
    }

    public void resetClockAndSaveScore(String playerName) {
        String filePath = "scores.txt";

        try {
            String score = getTime().toString() + ";" + playerName + "\n";

            File myObj = new File(filePath);
            myObj.createNewFile();

            FileWriter myWriter = new FileWriter(filePath, true);
            myWriter.write(score);
            myWriter.close();
        } catch (IOException e) {
            System.out.println(filePath);
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        finally
        {
            time = LocalTime.parse("00:00:00.00");
        }

    }

    public LocalTime getTime() {
        return time;
    }

    public static GameClock getInstance(){
        if(myInstance == null){
            myInstance = new GameClock();
        }
        return myInstance;

    };


}
