import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class GameLogic {

    public static UpdateHighscoreTimer ust;
    protected static ArrayList<GameObject> elements = new ArrayList<>();
    //Game Objects
    Player playerObj = new Player(50, 50, 100, 100);
    //other Variables
    protected int score = playerObj.getX();
    //MoveActions
    Action MoveUP;

    public GameLogic() throws IOException, InterruptedException {

        PlayerFallThread fallThread = new PlayerFallThread(playerObj);
        fallThread.start();

        EnemyMoveThread enemyMoveThread = new EnemyMoveThread(playerObj);
        enemyMoveThread.start();

        //Gamelogic
        MoveUP = new MoveUP();

        InputMap im = playerObj.getEntity().getInputMap();
        ActionMap am = playerObj.getEntity().getActionMap();

        im.put(KeyStroke.getKeyStroke("SPACE"), "up");
        am.put("up", MoveUP);

        //Entitys
        elements.add(playerObj);

        //start Game
        Game game = new Game(elements, String.valueOf(score));

        ust = new UpdateHighscoreTimer(game);
        ust.startTimer();

        createEnemy();

    }

    public static void createEnemy() throws IOException {
        int gap = 300;
        int enemyWidth = 150;
        int enemyHeight = 1000;
        int maxY = 600;
        int minY = 300;

        int bottomY = randomNumber(minY, maxY);
        GameObject enemyBot = new Enemy(2000, bottomY, enemyWidth, enemyHeight, false);

        int topY = bottomY - (enemyHeight + gap);
        GameObject enemyTop = new Enemy(2000, topY, enemyWidth, enemyHeight, true);

        GameFrame.game.add(enemyBot.getEntity());
        GameFrame.game.add(enemyTop.getEntity());
    }

    public static int randomNumber(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public class MoveUP extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            PlayerJumpThread jump = new PlayerJumpThread(playerObj);
            jump.start();
        }
    }
}