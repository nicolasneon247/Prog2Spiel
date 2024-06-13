import java.io.IOException;

public class PlayerFallThread extends Thread{

    protected Player player;
    public volatile boolean running = true;

    public PlayerFallThread(Player player){
        this.player = player;
    }
    public void run(){
        while (running) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            player.entity.setLocation(player.getX(), player.getY() + 1);
            try {
                player.OverlappingObstacle();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
}