package pwm.pongwithme;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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

public class Controller implements Initializable {

    @FXML
    private AnchorPane scene;

    @FXML
    private Circle ball;

    @FXML
    Rectangle paddle;

    private int PADDLE_XPOSITION = 10;
    private int PADDLE_YPOSITION = 380;
    private int PADDLE_WIDTH = 150;
    private int PADDLE_HEIGHT = 20;




    //1 Frame evey 10 millis, which means 100 FPS
    Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10), new EventHandler<ActionEvent>() {

        double deltaX = 2;
        double deltaY = 2;

        @Override
        public void handle(ActionEvent actionEvent) {
            ball.setLayoutX(ball.getLayoutX() + deltaX);
            ball.setLayoutY(ball.getLayoutY() + deltaY);

            Bounds bounds = scene.getBoundsInLocal();
            boolean rightBorder = ball.getLayoutX() >= (bounds.getMaxX() - ball.getRadius());
            boolean leftBorder = ball.getLayoutX() <= (bounds.getMinX() + ball.getRadius());
            boolean bottomBorder = ball.getLayoutY() >= (bounds.getMaxY() - ball.getRadius());
            boolean topBorder = ball.getLayoutY() <= (bounds.getMinY() + ball.getRadius());

            if (rightBorder || leftBorder) {
                deltaX *= -1;
            }
            if (bottomBorder || topBorder) {
                deltaY *= -1;
            }

            paddle = new Rectangle(PADDLE_XPOSITION,PADDLE_YPOSITION,PADDLE_WIDTH,PADDLE_HEIGHT);

            scene.getChildren().add(paddle);
        }
    }));

    @FXML
    private void handleOnKeyPressed(KeyEvent event)
    {
        System.out.println("Pressed key text: " + event.getText());
        System.out.println("Pressed key code: " + event.getCode());
        System.out.println(ball.getRadius());

        switch (event.getCode()) {
            case LEFT:
                PADDLE_XPOSITION -= 1;
                System.out.println("I pressed LEFT!");
                paddle.setX(PADDLE_XPOSITION);
                paddle.setWidth(PADDLE_WIDTH);
                break;
            case RIGHT:
                PADDLE_XPOSITION += 1;
                System.out.println("I pressed RIGHT!");
                paddle.setX(PADDLE_XPOSITION);
                paddle.setWidth(PADDLE_WIDTH);
                break;
            default:
                break;
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();



    }
}