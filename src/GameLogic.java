import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.awt.Image;

public class GameLogic{

    protected static ArrayList<GameObject> elements = new ArrayList<>();

    //Game Objects
    Player playerObj = new Player(50, 50, 100, 100);

    //MoveActions
    Action MoveUP;

    //other Variables
    protected int score = playerObj.getX();

    public GameLogic() throws IOException, InterruptedException {

        //Motion
        new EnemyMoveTimer(playerObj);
        PlayerFallTimer pft = new PlayerFallTimer(playerObj); //ruckelt
        pft.start();

        //PlayerFallThread fallThread = new PlayerFallThread(playerObj);
        //fallThread.start();

        UpdateScoreThread scoreThread = new UpdateScoreThread();
        scoreThread.start();

        //Gamelogic
        MoveUP = new MoveUP();

        InputMap im = playerObj.getEntity().getInputMap();
        ActionMap am = playerObj.getEntity().getActionMap();

        //nur space und dann schub nach oben
        //danach runter fallen

        //logic seperieren (bsp: Ocean App)

        im.put(KeyStroke.getKeyStroke("SPACE"), "up"); //pressed / released /typed vor den buchstaben
        am.put("up", MoveUP);

        //Entitys
        elements.add(playerObj);
        //elements.add(enemy);
        //start Game
        new Game(elements, String.valueOf(score));
        createEnemy();

    }

    public class MoveUP extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            PlayerJumpThread jump = new PlayerJumpThread(playerObj);
            jump.start();
        }
    }
    private static Image loadImage(String path) {

        Image result = null;
        try {
            result = ImageIO.read(new File(path));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
    public static void createEnemy() throws IOException {
        int gap = 300;
        int enemyWidth = 150;
        int enemyHeight = 1000;
        int maxY = 650;
        int minY = 200;

        int bottomY = randomNumber(minY, maxY);
        GameObject enemyBot = new Enemy(1200, bottomY, enemyWidth, enemyHeight);

        int topY = bottomY - (enemyHeight + gap);
        GameObject enemyTop = new Enemy(1200, topY, enemyWidth, enemyHeight);

        GameFrame.game.add(enemyBot.getEntity());
        GameFrame.game.add(enemyTop.getEntity());
    }
    public static int randomNumber(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
}