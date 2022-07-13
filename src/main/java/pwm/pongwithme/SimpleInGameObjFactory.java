package pwm.pongwithme;

public class SimpleInGameObjFactory {

    public InGameObject createInGameObject(String type) {

        InGameObject igo = null;

        if (type == "paddle") {
            igo = new Paddle();
        } else if (type == "ball") {
            igo = new Ball();
        }

        return igo;

    }
}
