package pwm.pongwithme;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Paddle extends InGameObject
{
    public Rectangle paddleObject;

    Paddle() {
        this.paddleObject = new Rectangle();
    }
}
