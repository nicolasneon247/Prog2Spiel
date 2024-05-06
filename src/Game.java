import javax.swing.*;
import java.awt.*;

public class Game extends JFrame {

    private JPanel game;

    public Game(){
        game = new JPanel();
        game.setLayout(new BoxLayout(game, BoxLayout.LINE_AXIS));
        game.add(Box.createHorizontalGlue());

        game.setBackground(Color.darkGray);
        //Gamelogic


        launchGame(game);
    }

    public void launchGame(JPanel game){
        GameFrame launchGame = new GameFrame("Flappy Bird", game);
        launchGame.setVisible(true);
        launchGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        launchGame.setSize(new Dimension(900,700));
    }

}
