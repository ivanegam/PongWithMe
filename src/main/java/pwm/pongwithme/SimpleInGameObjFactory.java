package pwm.pongwithme;

public class SimpleInGameObjFactory {

    public InGameObject createInGameObject(String type) {

        InGameObject igo = null;

        if (type == "player") {
            igo = new Player();
        } else if (type == "ball") {
            igo = new Ball();
        }

        return igo;

    }
}
