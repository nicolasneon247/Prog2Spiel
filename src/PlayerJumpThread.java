import javax.swing.*;

public class PlayerJumpThread extends Thread{

    protected GameObject player;


    public PlayerJumpThread(GameObject player){
        this.player = player;
    }
    public void run(){

        for(int i = 0; i < 100; i++){
            player.entity.setLocation(player.getX(), player.getY() - 1);
            player.OverlappingObstacle();
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}