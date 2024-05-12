import objects.Player;

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

public class Game extends JPanel implements KeyListener {

    protected JPanel game;
    protected ArrayList<String> moveBtns = new ArrayList<>();

    //Game Objects
    GameObject playerObj = new GameObject("Player",0,0,100,100,50);
    protected JLabel player = playerObj.getEntity();
    //MoveActions
    Action MoveUP;
    Action MoveDOWN;
    Action MoveLEFT;
    Action MoveRIGHT;

    public Game() throws IOException {

        //BufferedImage player = ImageIO.read(new File("Prog2Spiel/bird.jpg"));
        game = new JPanel();
        game.setLayout(null);

        BufferedImage bufferedImage = ImageIO.read(new File("Prog2Spiel/Background.jpg")); //Bild zu groß (beispielbild)
        JLabel background = new JLabel(new ImageIcon(bufferedImage));
        background.repaint();
        background.setOpaque(true);
        game.add(background);

        //game.setBackground(Color.darkGray);


        //Gamelogic
        MoveUP = new MoveUP();
        MoveDOWN = new MoveDOWN();
        MoveLEFT = new MoveLEFT();
        MoveRIGHT = new MoveRIGHT();

        InputMap im = player.getInputMap();
        ActionMap am = player.getActionMap();

        im.put(KeyStroke.getKeyStroke("W"), "up");
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
            }
        });

        //Entitys
        game.add(player);
        //start Game
        launchGame(game);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyCode());
        System.out.println(e.getKeyChar());

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public class MoveUP extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            player.setLocation(player.getX(), player.getY() - playerObj.getSpeed());
        }
    }
    public class MoveDOWN extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            player.setLocation(player.getX(), player.getY() + playerObj.getSpeed());
            System.out.println("down");
        }
    }
    public class MoveLEFT extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            player.setLocation(player.getX() - playerObj.getSpeed(), player.getY());
        }
    }
    public class MoveRIGHT extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            player.setLocation(player.getX() + playerObj.getSpeed(), player.getY());
            System.out.println("right");
        }
    }

    @Override
    public synchronized void addKeyListener(KeyListener l) {

    }

    public void launchGame(JPanel game){
        GameFrame launchGame = new GameFrame("Flappy Bird", game);
        launchGame.setVisible(true);
        launchGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        launchGame.setResizable(true); //vielleicht false setzen
        launchGame.setSize(new Dimension(1920,1080));//richtige Fenstergröße muss noch gefunden werden
    }
}