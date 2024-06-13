import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class timer {
    protected GameObject player;
    protected int timer = 0;
    protected int enemyCreationInterval = 3000;
    protected Timer moveTimer;

    public timer(GameObject player) {
        this.player = player;
        int delay = getDelayBasedOnDifficulty(Menu.difficulty.getText());
        moveTimer = new Timer(delay, new MoveAction());
        moveTimer.start();
    }

    private int getDelayBasedOnDifficulty(String difficulty) {
        switch (difficulty) {
            case "LEICHT":
                return 10;
            case "NORMAL":
                return 5;
            case "SCHWER":
                return 3;
            case "EXTREM":
                return 2;
            default:
                return 5;
        }
    }

    private class MoveAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            timer += 5;


            for (GameObject enemy : GameObject.enemys) {
                enemy.entity.setLocation(enemy.getX() - 1, enemy.getY());
            }

            try {
                player.OverlappingObstacle();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            if (timer >= enemyCreationInterval) {
                try {
                    GameLogic.createEnemy("Enemy");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                timer = 0;
            }

            for (GameObject enemy : GameObject.enemys) {
                if (enemy.getX() == -100) {
                    enemy.deleteObject(enemy);
                }
            }
        }
    }
}
