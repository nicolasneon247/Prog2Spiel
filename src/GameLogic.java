import org.w3c.dom.html.HTMLImageElement;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.security.Key;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.awt.Image;

public class GameLogic{

    protected JPanel game;
    protected ArrayList<String> moveBtns = new ArrayList<>();
    protected static ArrayList<GameObject> elements = new ArrayList<>();
    Image playerImg = loadImage("Prog2Spiel/bird.jpg");

    //Game Objects
    GameObject playerObj = new GameObject("Player", 50, 50, 100, 100, 50, Color.GREEN, playerImg);
    protected JLabel player = playerObj.getEntity();

    //GameObject enemyObj = new GameObject("Enemy", 500, 500, 100, 300, 0, Color.RED);
    //protected JLabel enemy = enemyObj.getEntity();

    //MoveActions
    Action MoveUP;
    Action MoveDOWN;
    Action MoveLEFT;
    Action MoveRIGHT;

    //other Variables

    protected int score = playerObj.getX();

    public GameLogic() throws IOException, InterruptedException {
        for (int i = 1; i < 10; i++) {
            GameObject tmp;
            tmp = new GameObject("Enemy", i * 300, randomNumber(), 100, 5000, 0, Color.RED, playerImg);
            elements.add(tmp);
        }
        PlayerFallThread fall = new PlayerFallThread(playerObj, game);
        fall.start();



        //Gamelogic
        MoveUP = new MoveUP();
        //MoveDOWN = new MoveDOWN();
        //MoveLEFT = new MoveLEFT();
        //MoveRIGHT = new MoveRIGHT();

        InputMap im = player.getInputMap();
        ActionMap am = player.getActionMap();

        //nur space und dann schub nach oben
        //danach runter fallen

        //logic seperieren (bsp: Ocean App)

        im.put(KeyStroke.getKeyStroke("SPACE"), "up"); //pressed / released /typed vor den buchstaben
        am.put("up", MoveUP);
        //im.put(KeyStroke.getKeyStroke("S"), "down");
        //am.put("down", MoveDOWN);
        //im.put(KeyStroke.getKeyStroke("A"), "left");
        //am.put("left", MoveLEFT);
        //im.put(KeyStroke.getKeyStroke("D"), "right");
        //am.put("right", MoveRIGHT);

        //Entitys
        elements.add(playerObj);
        //elements.add(enemy);
        //start Game
        new Game(elements, String.valueOf(score));
    }

    public class MoveUP extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            //playerObj.setX(200);
            //player.setLocation(playerObj.getX(), playerObj.getY() - playerObj.getSpeed());
            //if (playerObj.isOverlappingObstacle()) {
            //    JOptionPane.showMessageDialog(game, "Game Over!");
            //    System.exit(0);
            //}
            PlayerJumpThread jump = new PlayerJumpThread(playerObj, game);
            jump.start();
        }
    }

//    public class MoveDOWN extends AbstractAction {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            PlayerFallThread fall = new PlayerFallThread(playerObj);
//            fall.start();
//            //player.setLocation(player.getX(), player.getY() + playerObj.getSpeed());
//            if (isOverlappingObstacle()) {
//                JOptionPane.showMessageDialog(game, "Game Over!");
//                System.exit(0);
//            }
//        }
//    }
//
//    public class MoveLEFT extends AbstractAction {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            player.setLocation(player.getX() - playerObj.getSpeed(), player.getY());
//            if (isOverlappingObstacle()) {
//                JOptionPane.showMessageDialog(game, "Game Over!");
//                System.exit(0);
//            }
//            if (score > 0) score = player.getX() / 50;
//            GameFrame.updateScore(String.valueOf(score));
//        }
//    }
//
//    public class MoveRIGHT extends AbstractAction {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            player.setLocation(player.getX() + playerObj.getSpeed(), player.getY());
//            if (isOverlappingObstacle()) {
//                JOptionPane.showMessageDialog(game, "Game Over!");
//                System.exit(0);
//            }
//            if (player.getX() >= 0) score = player.getX() / 50;
//            GameFrame.updateScore(String.valueOf(score));
//        }
//    }

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