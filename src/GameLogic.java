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

public class GameLogic{

    protected JPanel game;
    protected ArrayList<String> moveBtns = new ArrayList<>();
    protected static ArrayList<GameObject> elements = new ArrayList<>();

    //Game Objects
    GameObject playerObj = new GameObject("Player", 0, 0, 100, 100, 50, Color.GREEN);
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

    public GameLogic() throws IOException {

        for (int i = 0; i < 10; i++) {
            GameObject tmp;
            tmp = new GameObject("Enemy", i * 300, randomNumber(), 100, randomNumber(), 0, Color.RED);
            elements.add(tmp);
        }

        //Gamelogic
        MoveUP = new MoveUP();
        MoveDOWN = new MoveDOWN();
        MoveLEFT = new MoveLEFT();
        MoveRIGHT = new MoveRIGHT();

        InputMap im = player.getInputMap();
        ActionMap am = player.getActionMap();

        //nur space und dann schub nach oben
        //danach runter fallen

        //logic seperieren (bsp: Ocean App)

        im.put(KeyStroke.getKeyStroke("W"), "up"); //pressed / released /typed vor den buchstaben
        am.put("up", MoveUP);
        im.put(KeyStroke.getKeyStroke("S"), "down");
        am.put("down", MoveDOWN);
        im.put(KeyStroke.getKeyStroke("A"), "left");
        am.put("left", MoveLEFT);
        im.put(KeyStroke.getKeyStroke("D"), "right");
        am.put("right", MoveRIGHT);

        im.put(KeyStroke.getKeyStroke("T"), "t");
        am.put("t", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Idee: Alle gedrückten keys zur Liste hinzufügen, und beim loslassen wieder entfernen; anhand dessen player bewegen
                //Key pressed, Key released funktion notwendig
            }
        });


        //Entitys
        elements.add(playerObj);
        //elements.add(enemy);
        //start Game
        new Game(elements, String.valueOf(score));
    }

    public class MoveUP extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {

            player.setLocation(player.getX(), player.getY() - playerObj.getSpeed());
            if (isOverlappingObstacle()) {
                JOptionPane.showMessageDialog(game, "Game Over!");
                System.exit(0);
            }
        }
    }

    public class MoveDOWN extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            player.setLocation(player.getX(), player.getY() + playerObj.getSpeed());
            if (isOverlappingObstacle()) {
                JOptionPane.showMessageDialog(game, "Game Over!");
                System.exit(0);
            }
        }
    }

    public class MoveLEFT extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            player.setLocation(player.getX() - playerObj.getSpeed(), player.getY());
            if (isOverlappingObstacle()) {
                JOptionPane.showMessageDialog(game, "Game Over!");
                System.exit(0);
            }
            if (score > 0) score = player.getX() / 50;
            GameFrame.updateScore(String.valueOf(score));
        }
    }

    public class MoveRIGHT extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            player.setLocation(player.getX() + playerObj.getSpeed(), player.getY());
            if (isOverlappingObstacle()) {
                JOptionPane.showMessageDialog(game, "Game Over!");
                System.exit(0);
            }
            if (player.getX() >= 0) score = player.getX() / 50;
            GameFrame.updateScore(String.valueOf(score));
        }
    }

    public Boolean isOverlappingObstacle() {
        for (GameObject elm : elements) {
            if (elm.getName().equals("Enemy")) {
                boolean noOverlapX = player.getX() + player.getWidth() <= elm.getX() || player.getX() >= elm.getX() + elm.getWidth();
                boolean noOverlapY = player.getY() + player.getHeight() <= elm.getY() || player.getY() >= elm.getY() + elm.getHeight();
                if (!(noOverlapX || noOverlapY)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static int randomNumber() {
        int randomNum = ThreadLocalRandom.current().nextInt(50, 600 + 1);
        return randomNum;
    }
}