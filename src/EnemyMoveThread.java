import java.io.IOException;

public class EnemyMoveThread extends Thread {

    public volatile boolean running = true;
    protected Player player;
    protected int timer = 0;
    protected int enemyCreationInterval = 3000;

    public EnemyMoveThread(Player player) {
        this.player = player;
    }

    public void run() {
        while (running) {
            timer += 5;
            try {
                switch (Menu.difficulty.getText()) {
                    case "LEICHT":
                        Thread.sleep(5);
                        break;
                    case "NORMAL":
                        Thread.sleep(3);
                        break;
                    case "SCHWER":
                        Thread.sleep(2);
                        break;
                    case "EXTREM":
                        Thread.sleep(1);
                        break;
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            for (GameObject enemy : Enemy.enemys) {
                enemy.entity.setLocation(enemy.getX() - 1, enemy.getY());
            }

            try {
                player.OverlappingObstacle();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            if (timer >= enemyCreationInterval) {
                try {
                    GameLogic.createEnemy();

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                timer = 0;
            }

            for (GameObject enemy : GameObject.enemys) {
                if (enemy.getX() <= -100) {
                    enemy.deleteObject(enemy);
                }
            }
        }
    }
}