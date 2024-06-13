
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.awt.Image;

public class GameLogic{

    protected static ArrayList<GameObject> elements = new ArrayList<>();
    Image playerImg = loadImage("Prog2Spiel/testewnemy2.png");

    //Game Objects
    GameObject playerObj = new GameObject("Player", 50, 50, 100, 100, 50);

    //MoveActions
    Action MoveUP;

    //other Variables
    protected int score = playerObj.getX();

    public GameLogic() throws IOException, InterruptedException {
//        for (int i = 2; i < 4; i++) {
//            GameObject bottom;
//            bottom = new GameObject("Enemy", i * 500, randomNumber(), 100, 5000, 0, Color.RED);
//            elements.add(bottom);
//        }
//        for (int i = 1; i < 10; i++){
//            GameObject top;
//            top = new GameObject("Enemy", i * 300, 0, 100,100, 0, Color.RED);
//            elements.add(top);
//        }

        //Threads
        //ObjectsMoveThread ObjectsMoveThread = new ObjectsMoveThread(playerObj);
        //ObjectsMoveThread.start();

        timer timer = new timer(playerObj);

        PlayerFallThread fallThread = new PlayerFallThread(playerObj);
        fallThread.start();

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
        createEnemy("Enemy");
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
    public static void createEnemy(String name) throws IOException {
        int gap = 230;
        int enemyWidth = 150;
        int enemyHeight = 1000;
        int maxY = 650;
        int minY = 200;

        int bottomY = randomNumber(minY, maxY);
        GameObject enemyBot = new GameObject(name, 2000, bottomY, enemyWidth, enemyHeight, 0);

        int topY = bottomY - (enemyHeight + gap);
        GameObject enemyTop = new GameObject(name, 2000, topY, enemyWidth, enemyHeight, 0);

        GameFrame.game.add(enemyBot.getEntity());
        GameFrame.game.add(enemyTop.getEntity());
    }
    public static int randomNumber(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
}