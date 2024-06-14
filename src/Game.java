import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Game extends JPanel {

    public static String currentScore;
    protected static JPanel game;
    private final BackgroundImagePanel backgroundImagePanel;

    public Game(ArrayList<GameObject> elements, String score) {
        backgroundImagePanel = new BackgroundImagePanel("Prog2Spiel/BG.png");
        game = backgroundImagePanel;
        game.setLayout(null);

        currentScore = score;

        // Entities
        for (GameObject elm : elements) {
            game.add(elm.getEntity());
        }

        // start Game
        launchGame(game);
    }

    public void updateBackgroundImage(String newImagePath) {
        backgroundImagePanel.setBackgroundImage(newImagePath);
        backgroundImagePanel.repaint();
    }

    public void launchGame(JPanel game) {
        GameFrame launchGame = new GameFrame("Flappy Bird", game);
        launchGame.setVisible(true);
        launchGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        launchGame.setResizable(true); // vielleicht false setzen
        launchGame.setSize(new Dimension(1920, 1080)); // richtige Fenstergröße muss noch gefunden werden

        // launchGame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        // launchGame.setUndecorated(true);
    }

    private class BackgroundImagePanel extends JPanel {
        private BufferedImage backgroundImage;

        public BackgroundImagePanel(String imgPath) {
            setBackgroundImage(imgPath);
        }

        public void setBackgroundImage(String imgPath) {
            try {
                backgroundImage = ImageIO.read(new File(imgPath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }
}
