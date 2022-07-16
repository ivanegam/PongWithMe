package pwm.pongwithme;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Box;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable
{
    /*
    I referenced a couple of Github repositories and youtube videos to build the Game. My code looks very different but these resources were helpful to me:
     1. https://github.com/AbdelrahmanBayoumi/StopwatchFX
     2. https://github.com/Gaspared/pong
     3.https://gist.github.com/Da9el00/8141d962ae4d6a3670963181cb0f7c4e -- Ball Movement
     4. https://www.youtube.com/watch?v=HsQSqFuSTGE -- Pong Mechanics
     5. https://www.youtube.com/watch?v=caD6IZszqEk -- Stopwatch
     6. https://www.youtube.com/watch?v=x6NFmzQHvMU&list=LL&index=4&t=176s -- Ball Movement

     */

    @FXML
    private AnchorPane scene;


    private Ball ballObject = new Ball();
    private Paddle paddleObject = new Paddle();

    @FXML
    private Circle ball = ballObject.ballObject;

    @FXML
    private Rectangle paddle = paddleObject.paddleObject;

    @FXML
    private Label timerLabel;

    GameClock clock = GameClock.getInstance();


    private double PADDLE_XPOSITION = 10;
    private double PADDLE_YPOSITION = 380;
    private double PADDLE_WIDTH = 150;
    private double PADDLE_HEIGHT = 20;

    private double BALL_SPEED = 1;

    private boolean GameIsRunning = false;


    //1 Frame evey 10 millis, which means 100 FPS
    Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10), new EventHandler<>() {

        double deltaX = 2 * BALL_SPEED;
        double deltaY = 2 * BALL_SPEED;

        @Override
        public void handle(ActionEvent actionEvent) {
            if (GameIsRunning) {
                ball.setLayoutX(ball.getLayoutX() + deltaX);
                ball.setLayoutY(ball.getLayoutY() + deltaY);

                Bounds bounds = scene.getBoundsInLocal();
                boolean rightBorder = ball.getLayoutX() >= (bounds.getMaxX() - ball.getRadius());
                boolean leftBorder = ball.getLayoutX() <= (bounds.getMinX() + ball.getRadius());
                boolean bottomBorder = ball.getLayoutY() >= (bounds.getMaxY() - ball.getRadius());
                boolean topBorder = ball.getLayoutY() <= (bounds.getMinY() + ball.getRadius());
                boolean bottomBorder_WithPaddle = ball.getLayoutY()  >= ((bounds.getMaxY() - (ball.getRadius() + PADDLE_HEIGHT - 4)));

                if (rightBorder || leftBorder) {
                    deltaX *= -1;
                    if(topBorder)
                    {
                        deltaY *= -1;
                    }
                }
                else if (topBorder)
                {
                    deltaY *= -1;
                }
                else if (bottomBorder_WithPaddle && (ball.getLayoutX() - paddle.getLayoutX() < PADDLE_WIDTH && ball.getLayoutX() - paddle.getLayoutX() > -10))
                {
                    deltaY *= -1;
                } else if (bottomBorder) {
                    GameIsRunning = false;
                }
            }
        }
    }));

    Timeline clockTimeline = new Timeline(new KeyFrame(Duration.millis(1000), ae -> incrementTime()));

    public void startClock()
    {
        clockTimeline.setCycleCount(Animation.INDEFINITE);
        clockTimeline.play();
    };

    private void incrementTime() {
        if(GameIsRunning)
        {
            clock.time = clock.time.plusSeconds(1);
            timerLabel.setText(clock.time.format(clock.dtf));
        }
        else
        {
          clockTimeline.stop();
        }
    }

    @FXML
    private void handleOnKeyPressed(KeyEvent event)
    {
        System.out.println("Pressed key text: " + event.getText());
        System.out.println("Pressed key code: " + event.getCode());

        Bounds bounds = scene.getBoundsInLocal();
        //600 is max size of window
        boolean rightBorder = PADDLE_XPOSITION >= (450);
        boolean leftBorder = PADDLE_XPOSITION <= (bounds.getMinX());


        switch (event.getCode()) {
            case LEFT:

                if(!leftBorder && GameIsRunning)
                {
                    PADDLE_XPOSITION -= 50;

                    paddle.relocate(PADDLE_XPOSITION, PADDLE_YPOSITION);
                }

                break;
            case RIGHT:
                if(!rightBorder && GameIsRunning)
                {
                    PADDLE_XPOSITION += 50;

                    paddle.relocate(PADDLE_XPOSITION, PADDLE_YPOSITION);
                }
                break;
            case ENTER:
                GameIsRunning = true;
                ball.setLayoutX(100);
                ball.setLayoutY(200);
                startClock();
            default:
                break;
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        paddle = new Rectangle(PADDLE_XPOSITION,PADDLE_YPOSITION,PADDLE_WIDTH,PADDLE_HEIGHT);

        scene.getChildren().add(paddle);

        startClock();


    }
}
