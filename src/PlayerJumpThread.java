import javax.swing.*;
import java.io.IOException;

public class PlayerJumpThread extends Thread{

    protected GameObject player;


    public PlayerJumpThread(GameObject player){
        this.player = player;
    }
    public void run(){

        for(int i = 0; i < 100; i++){
            player.entity.setLocation(player.getX(), player.getY() - 1);
            try {
                player.OverlappingObstacle();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}