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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
     7. https://stackoverflow.com/questions/32802664/setonkeypressed-event-not-working-properly - Fixing setOnKeyPressed

     */

    @FXML
    private AnchorPane scene;

    private SimpleInGameObjectFactory inGameObjectFactory = new SimpleInGameObjectFactory();

    private Ball ballObject = (Ball) inGameObjectFactory.createInGameObject("ball");
    private Paddle paddleObject = (Paddle) inGameObjectFactory.createInGameObject("paddle");

    @FXML
    private Circle ball = ballObject.ballObject;

    @FXML
    public Rectangle paddle = paddleObject.paddleObject;

    @FXML
    private Label timerLabel;

    @FXML
    private Label gameMessage;

    @FXML
    private TextField playerName;

    @FXML
    private Button savePlayerNameButton;


    GameClock clock = GameClock.getInstance();

    private double PADDLE_WIDTH = 200;
    private double PADDLE_HEIGHT = 20;

    public double PADDLE_XPOSITION;
    public double PADDLE_YPOSITION;

    private double BALL_STARTING_XPOSITION;
    private double BALL_STARTING_YPOSITION;

    private double BALL_SPEED_X = 2;
    private double BALL_SPEED_Y = 2;

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
    private boolean ballHeadingSouth = true;
    private boolean ballHeadingEast = true;

    //1 Frame evey 10 millis, which means 100 FPS
    Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10), new EventHandler<>() {

        @Override
        public void handle(ActionEvent actionEvent) {
            if (GameIsRunning) {
                ball.setLayoutX(ball.getLayoutX() + BALL_SPEED_X);
                ball.setLayoutY(ball.getLayoutY() + BALL_SPEED_Y);

                setBorders();

                if(isBallOnLeftBorder && !ballHeadingEast)
                {
                    BALL_SPEED_X *= -1;
                    ballHeadingEast = true;
                    if(isBallOnTopBorder)
                    {
                        BALL_SPEED_Y *= -1;
                        ballHeadingSouth = true;
                    }
                }
                else if (isBallOnRightBorder && ballHeadingEast)
                {
                    BALL_SPEED_X *= -1;
                    ballHeadingEast = false;
                    if(isBallOnTopBorder)
                    {
                        BALL_SPEED_Y *= -1;
                        ballHeadingSouth = true;
                    }
                }
                else if (isBallOnTopBorder)
                {
                    BALL_SPEED_Y *= -1;
                    ballHeadingSouth = true;
                }
                else if (isBallTouchingPaddle_Y && isBallTouchingPaddle_X && ballHeadingSouth)
                {
                    BALL_SPEED_Y *= -1;
                    ballHeadingSouth = false;
                } else if (isBallOnBottomBorder)
                {
                    endGame();
                }
            }
        }
    }));

    Timeline clockTimeline = new Timeline(new KeyFrame(Duration.millis(1), ae -> incrementTime()));

    //Increase ball speed every 5 seconds
    Timeline ballSpeedTimeline = new Timeline(new KeyFrame(Duration.seconds(5), actionEvent -> increaseBallSpeed()));

    private void setBorders()
    {
        bounds = scene.getBoundsInLocal();
        isBallOnRightBorder = ball.getLayoutX() >= (bounds.getMaxX() - ball.getRadius());
        isBallOnLeftBorder = ball.getLayoutX() <= (bounds.getMinX() + ball.getRadius());
        isBallOnBottomBorder = ball.getLayoutY() >= (bounds.getMaxY() - ball.getRadius());
        isBallOnTopBorder = ball.getLayoutY() <= (bounds.getMinY() + ball.getRadius());
        isBallTouchingPaddle_Y = ball.getLayoutY()  >= ((bounds.getMaxY() - (ball.getRadius() + PADDLE_HEIGHT - 4)));
        isBallTouchingPaddle_X = (ball.getLayoutX() - PADDLE_XPOSITION < PADDLE_WIDTH && ball.getLayoutX() - PADDLE_XPOSITION > -10);
        isPaddleTouchingRightBorder = PADDLE_XPOSITION >= bounds.getMaxX() - (PADDLE_WIDTH+25);
        isPaddleTouchingLeftBorder = PADDLE_XPOSITION <= (bounds.getMinX() + 25);
    }

    private void startClock()
    {
        clockTimeline.setCycleCount(Animation.INDEFINITE);
        clockTimeline.play();
    };

    private void startBallSpeedTimeline()
    {
        ballSpeedTimeline.setCycleCount(Animation.INDEFINITE);
        ballSpeedTimeline.play();
    };

    private void incrementTime() {
        if(GameIsRunning)
        {
            //I have to pass nanos. 1000000 = 1 millisecond
            clock.incrementClock(1000000);
            timerLabel.setText(clock.getFormattedTime());
        }
        else
        {
          clockTimeline.stop();
        }
    }

    private void increaseBallSpeed()
    {
        if (GameIsRunning)
        {
            //Increase ball speed by 20%
            BALL_SPEED_X *= 1.2;
            BALL_SPEED_Y *= 1.2;
        }
        else {
            BALL_SPEED_X = 2;
            BALL_SPEED_Y = 2;
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
                    LeftCommand leftCommand = new LeftCommand(this);
                    leftCommand.execute();
                }

                break;
            case RIGHT:
                if(!isPaddleTouchingRightBorder && GameIsRunning)
                {
                    RightCommand rightCommand = new RightCommand(this);
                    rightCommand.execute();
                }
                break;
            case ENTER:
                if(playerName.getText() ==  null || playerName.getText() == "")
                {
                    gameMessage.setText("Please provide a name.");
                }
                else
                {

                    startGame();

                }
            default:
                break;
        }

    }

    private void endGame()
    {
        GameIsRunning = false;
        gameMessage.setText("Game over! Press Enter to play again.");
        gameMessage.setVisible(true);

        clock.resetClockAndSaveScore(playerName.getText());

        ballSpeedTimeline.stop();

        //Reset Ball Speed
        BALL_SPEED_X = 2;
        BALL_SPEED_Y = 2;

        //Hide Paddle and Ball
        ball.setVisible(false);
        paddle.setVisible(false);
    }

    private Runnable nextSceneHandler;

    private void startGame()
    {
        if(!GameIsRunning) {
            GameIsRunning = true;

            playerName.setVisible(false);

            setBallAndPaddlePositions();

            //Reset ball position
            ball.relocate(BALL_STARTING_XPOSITION, BALL_STARTING_YPOSITION);

            //Reset Paddle position
            paddle.relocate(PADDLE_XPOSITION, PADDLE_YPOSITION);

            startClock();

            startBallSpeedTimeline();

            //Hiding the starting game message
            gameMessage.setVisible(false);

            //Hiding the playername prompt
            playerName.setVisible(false);

            //Show Paddle and Ball
            ball.setVisible(true);
            paddle.setVisible(true);

            scene.requestFocus();
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

    private void showGameStartMessage()
    {
        //Positioning the game message label to top center of the screen
        gameMessage.setVisible(true);
        gameMessage.setMaxWidth(Double.MAX_VALUE);
        AnchorPane.setLeftAnchor(gameMessage, 0.0);
        AnchorPane.setRightAnchor(gameMessage, 0.0);
        AnchorPane.setTopAnchor(gameMessage, 0.0);
        AnchorPane.setBottomAnchor(gameMessage, 300.00);
        gameMessage.setAlignment(Pos.CENTER);
    }

    private void showPlayerPrompt()
    {
        //Positioning the prompt for player name to top center of the screen
        AnchorPane.setLeftAnchor(playerName, 300.0);
        AnchorPane.setRightAnchor(playerName, 300.0);
        AnchorPane.setTopAnchor(playerName, 300.0);
        AnchorPane.setBottomAnchor(playerName, 300.0);
        playerName.setAlignment(Pos.CENTER);
    }

    @FXML
    protected void onSavePlayerNameButtonClick()
    {
        savePlayerNameButton.setVisible(false);
        playerName.setVisible(false);
        showGameStartMessage();
        //Setting this property is import so that the scene can then capture keyevents
        scene.requestFocus();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Main game timeline is always running, but nothing is done unless GameIsRunning property is true
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        setBallAndPaddlePositions();

        //Add paddle to the window
        paddle = new Rectangle(PADDLE_XPOSITION,PADDLE_YPOSITION,PADDLE_WIDTH,PADDLE_HEIGHT);
        scene.getChildren().add(paddle);

        startClock();

        //Hide the start game message
        gameMessage.setVisible(false);

        showPlayerPrompt();

        playerName.setPromptText("Enter your name");


        //Hide Ball and paddle until user starts game
        ball.setVisible(false);
        paddle.setVisible(false);
    }
}
