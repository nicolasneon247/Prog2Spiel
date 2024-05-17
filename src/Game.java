import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Game extends JPanel{

    protected JPanel game;
    public Game(ArrayList<GameObject> elements, String score) throws IOException {
        game = new JPanel();
        game.setLayout(null);

        BufferedImage bufferedImage = ImageIO.read(new File("Prog2Spiel/Background.jpg")); //Bild zu groß (beispielbild)
        JLabel background = new JLabel(new ImageIcon(bufferedImage));
        background.repaint();
        background.setOpaque(true);
        game.add(background);

        game.setBackground(Color.darkGray);

        //Entitys
        for(GameObject elm : elements){
            game.add(elm.getEntity());
        }
        //start Game
        launchGame(game, score);
    }

    public void launchGame(JPanel game, String score){
        GameFrame launchGame = new GameFrame("Flappy Bird", game, score);
        launchGame.setVisible(true);
        launchGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        launchGame.setResizable(true); //vielleicht false setzen
        launchGame.setSize(new Dimension(1920,1080));//richtige Fenstergröße muss noch gefunden werden

        //launchGame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //launchGame.setUndecorated(true);

    }
}