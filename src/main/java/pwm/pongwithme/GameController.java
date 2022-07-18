package pwm.pongwithme;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
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

    @FXML
    private Label gameMessage;


    GameClock clock = GameClock.getInstance();

    private double PADDLE_WIDTH = 150;
    private double PADDLE_HEIGHT = 20;

    private double PADDLE_XPOSITION;
    private double PADDLE_YPOSITION;

    private double BALL_STARTING_XPOSITION;
    private double BALL_STARTING_YPOSITION;

    private double BALL_SPEED = 2;

    private boolean GameIsRunning = false;

    private Bounds bounds;
    private boolean isBallOnRightBorder;
    private boolean isBallOnLeftBorder;
    private boolean isBallOnBottomBorder;
    private boolean isBallOnTopBorder;
    private boolean isBallTouchingPaddle_Y;
    private boolean isBallTouchingPaddle_X;
    private boolean isPaddleTouchingRightBorder;
    private boolean isPaddleTouchingLeftBorder;

    //1 Frame evey 10 millis, which means 100 FPS
    Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10), new EventHandler<>() {

        double deltaX = 2 * BALL_SPEED;
        double deltaY = 2 * BALL_SPEED;

        @Override
        public void handle(ActionEvent actionEvent) {
            if (GameIsRunning) {
                ball.setLayoutX(ball.getLayoutX() + deltaX);
                ball.setLayoutY(ball.getLayoutY() + deltaY);

                setBorders();

                if (isBallOnRightBorder || isBallOnLeftBorder) {
                    deltaX *= -1;
                    if(isBallOnTopBorder)
                    {
                        deltaY *= -1;
                    }
                }
                else if (isBallOnTopBorder)
                {
                    deltaY *= -1;
                }
                else if (isBallTouchingPaddle_Y && isBallTouchingPaddle_X)
                {
                    deltaY *= -1;
                } else if (isBallOnBottomBorder) {
                    GameIsRunning = false;
                    gameMessage.setText("Game over! Press Enter to play again.");
                    gameMessage.setVisible(true);

                    clock.resetClockAndSaveScore();

                    //Hide Paddle and Ball
                    ball.setVisible(false);
                    paddle.setVisible(false);


                }
            }
        }
    }));

    Timeline clockTimeline = new Timeline(new KeyFrame(Duration.millis(1), ae -> incrementTime()));

    private void setBorders()
    {
        bounds = scene.getBoundsInLocal();
        isBallOnRightBorder = ball.getLayoutX() >= (bounds.getMaxX() - ball.getRadius());
        isBallOnLeftBorder = ball.getLayoutX() <= (bounds.getMinX() + ball.getRadius());
        isBallOnBottomBorder = ball.getLayoutY() >= (bounds.getMaxY() - ball.getRadius());
        isBallOnTopBorder = ball.getLayoutY() <= (bounds.getMinY() + ball.getRadius());
        isBallTouchingPaddle_Y = ball.getLayoutY()  >= ((bounds.getMaxY() - (ball.getRadius() + PADDLE_HEIGHT - 4)));
        isBallTouchingPaddle_X = (ball.getLayoutX() - paddle.getLayoutX() < PADDLE_WIDTH && ball.getLayoutX() - paddle.getLayoutX() > -10);
        isPaddleTouchingRightBorder = PADDLE_XPOSITION >= bounds.getMaxX() - PADDLE_WIDTH;
        isPaddleTouchingLeftBorder = PADDLE_XPOSITION <= bounds.getMinX();
    }

    private void startClock()
    {
        clockTimeline.setCycleCount(Animation.INDEFINITE);
        clockTimeline.play();
    };

    private void incrementTime() {
        if(GameIsRunning)
        {
            clock.incrementClock(1000000);
            timerLabel.setText(clock.getFormattedTime());
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

        //Set the border values of the window
        setBorders();

        switch (event.getCode()) {
            case LEFT:

                if(!isPaddleTouchingLeftBorder && GameIsRunning)
                {
                    PADDLE_XPOSITION -= 50;

                    paddle.relocate(PADDLE_XPOSITION, PADDLE_YPOSITION);
                }

                break;
            case RIGHT:
                if(!isPaddleTouchingRightBorder && GameIsRunning)
                {
                    PADDLE_XPOSITION += 50;

                    paddle.relocate(PADDLE_XPOSITION, PADDLE_YPOSITION);
                }
                break;
            case ENTER:
                if(!GameIsRunning)
                {
                    GameIsRunning = true;

                    setBallAndPaddlePositions();

                    //Reset ball position
                    ball.relocate(BALL_STARTING_XPOSITION, BALL_STARTING_YPOSITION);

                    //Reset Paddle position
                    paddle.relocate(PADDLE_XPOSITION, PADDLE_YPOSITION);

                    startClock();

                    //Hiding the starting game message
                    gameMessage.setVisible(false);

                    //Show Paddle and Ball
                    ball.setVisible(true);
                    paddle.setVisible(true);
                }
            default:
                break;
        }

    }

    //Adjust Ball and Paddle positions based on the window size.
    private void setBallAndPaddlePositions()
    {
        setBorders();
        PADDLE_XPOSITION = bounds.getMaxX()/2 - 100;
        PADDLE_YPOSITION = bounds.getMaxY() - PADDLE_HEIGHT;

        BALL_STARTING_XPOSITION = bounds.getMaxX()/2 - 100;
        BALL_STARTING_YPOSITION = bounds.getMinY() + 200;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Positioning the game message label to top center of the screen
        gameMessage.setMaxWidth(Double.MAX_VALUE);
        AnchorPane.setLeftAnchor(gameMessage, 0.0);
        AnchorPane.setRightAnchor(gameMessage, 0.0);
        AnchorPane.setTopAnchor(gameMessage, 0.0);
        AnchorPane.setBottomAnchor(gameMessage, 200.0);
        gameMessage.setAlignment(Pos.CENTER);

        //Main game timeline is always running, but nothing is done unless GameIsRunning property is true
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        setBallAndPaddlePositions();

        //Add paddle to the window
        paddle = new Rectangle(PADDLE_XPOSITION,PADDLE_YPOSITION,PADDLE_WIDTH,PADDLE_HEIGHT);
        scene.getChildren().add(paddle);

        startClock();

        //Hide Ball and paddle until user starts game
        ball.setVisible(false);
        paddle.setVisible(false);
    }
}
