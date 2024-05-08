import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Game extends JFrame {

    private JPanel game;

    public Game() throws IOException {
        game = new JPanel();
        game.setLayout(new BoxLayout(game, BoxLayout.LINE_AXIS));
        game.add(Box.createHorizontalGlue());

        BufferedImage bufferedImage = ImageIO.read(new File("Prog2Spiel/Background.jpg")); //Bild zu groß (beispielbild)
        JLabel background = new JLabel(new ImageIcon(bufferedImage));
        game.add(background, BorderLayout.NORTH);

        game.setBackground(Color.darkGray);
        //Gamelogic


        launchGame(game);
    }

    public void launchGame(JPanel game){
        GameFrame launchGame = new GameFrame("Flappy Bird", game);
        launchGame.setVisible(true);
        launchGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        launchGame.setResizable(true); //vielleicht false setzen
        //launchGame.setSize(new Dimension(2560,1664)); //richtige Fenstergröße muss noch gefunden werden
        launchGame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

}
