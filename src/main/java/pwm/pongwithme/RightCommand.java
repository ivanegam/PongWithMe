package pwm.pongwithme;
//Command pattern
public class RightCommand implements Command {

    GameController gameController;

    public RightCommand(GameController gameController){
        this.gameController = gameController;
    }

    public void execute(){
        gameController.PADDLE_XPOSITION += 75;
        gameController.paddle.relocate(gameController.PADDLE_XPOSITION, gameController.PADDLE_YPOSITION);
    }


}
