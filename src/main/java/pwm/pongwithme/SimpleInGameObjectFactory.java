package pwm.pongwithme;
//Simple Factory pattern
public class SimpleInGameObjectFactory
{
    public InGameObject createInGameObject(String type)
    {
        if(type.equals("ball")) {
            return new Ball();
        }
        else if (type.equals("paddle")) {
            return new Paddle();
        }
        else {
            return null;
        }
    }
}
