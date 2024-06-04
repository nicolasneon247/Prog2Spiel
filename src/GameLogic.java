
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

    public static JPanel game;
    protected ArrayList<String> moveBtns = new ArrayList<>();
    protected static ArrayList<GameObject> elements = new ArrayList<>();
    Image playerImg = loadImage("Prog2Spiel/Sprite-0003.png");

    //Game Objects
    GameObject playerObj = new GameObject("Player", 50, 50, 100, 100, 50, Color.GREEN);
    protected JLabel player = playerObj.getEntity();


    //MoveActions
    Action MoveUP;

    //other Variables

    protected int score = playerObj.getX();

    public GameLogic() throws IOException, InterruptedException {
        for (int i = 0; i < 10; i++) {
            GameObject tmp;
            tmp = new GameObject("Enemy", i * 300, randomNumber(), 100, 5000, 0, Color.RED);
            elements.add(tmp);
        }

        //Threads
        PlayerFallThread fallThread = new PlayerFallThread(playerObj);
        fallThread.start();

        UpdateScoreThread scoreThread = new UpdateScoreThread();
        scoreThread.start();

        //Gamelogic
        MoveUP = new MoveUP();

        InputMap im = player.getInputMap();
        ActionMap am = player.getActionMap();

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
    }

    public class MoveUP extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            PlayerJumpThread jump = new PlayerJumpThread(playerObj);
            jump.start();
        }
    }

    public static int randomNumber() {
        return ThreadLocalRandom.current().nextInt(50, 600 + 1);
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
}