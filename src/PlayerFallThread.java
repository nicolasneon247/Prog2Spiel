import javax.swing.*;
public class PlayerFallThread extends Thread{

    protected GameObject player;
    public volatile boolean running = true;
    protected JPanel game;


    public PlayerFallThread(GameObject player, JPanel game){
        this.player = player;
        this.game = game;
    }
    public void run(){
        while (running) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            player.entity.setLocation(player.getX(), player.getY() + 1);
            if (player.isOverlappingObstacle()) {
                JOptionPane.showMessageDialog(game, "Game Over!");
                System.exit(0);
            }
        }
    }
}
