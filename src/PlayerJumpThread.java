import javax.swing.*;

public class PlayerJumpThread extends Thread{

    protected GameObject player;
    protected JPanel game;


    public PlayerJumpThread(GameObject player, JPanel game){
        this.player = player;
        this.game = game;
    }
    public void run(){

        for(int i = 0; i < 100; i++){
            player.entity.setLocation(player.getX(), player.getY() - 1);
            if (player.isOverlappingObstacle()) {
                JOptionPane.showMessageDialog(game, "Game Over!");
                System.exit(0);
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}