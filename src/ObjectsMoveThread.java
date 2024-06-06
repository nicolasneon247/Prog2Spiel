import java.io.IOException;

public class ObjectsMoveThread extends Thread{

    protected GameObject player;
    public volatile boolean running = true;
    protected int timer = 0;
    protected int enemyCreationInterval = 3000;

    public ObjectsMoveThread(GameObject player){
        this.player = player;
    }

    public void run(){
        while (running) {
            timer += 5;
            try {
                switch(Menu.difficulty.getText()){
                    case "LEICHT":
                        Thread.sleep(10);
                        break;
                    case "NORMAL":
                        Thread.sleep(5);
                        break;
                    case "SCHWER":
                        Thread.sleep(3);
                        break;
                    case "EXTREM":
                        Thread.sleep(2);
                        break;
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            for (GameObject enemy : GameObject.enemys) {
                enemy.entity.setLocation(enemy.getX() - 1, enemy.getY());
            }

            try {
                player.OverlappingObstacle();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            if (timer >= enemyCreationInterval) {
                try {
                    GameLogic.createEnemy("Enemy");
                } catch (IOException e) {
                    throw new RuntimeException(e);
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
