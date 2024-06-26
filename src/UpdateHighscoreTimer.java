import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateHighscoreTimer {
    private final Timer timer;
    private final Game game;

    public UpdateHighscoreTimer(Game game) {
        this.game = game;
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int currentScore = GameFrame.getScore();
                GameFrame.updateScore(currentScore + 1);
                switch (currentScore) {
                    case 25:
                        game.updateBackgroundImage("Prog2Spiel/MA.gif");
                        GameFrame.scoreText.setForeground(Color.BLUE);
                        GameFrame.scoreNumber.setForeground(Color.BLUE);
                        break;
                    case 50:
                        game.updateBackgroundImage("Prog2Spiel/Kriege.png");
                        GameFrame.scoreText.setForeground(Color.orange);
                        GameFrame.scoreNumber.setForeground(Color.orange);
                        break;
                    case 75:
                        game.updateBackgroundImage("Prog2Spiel/Stadt.png");
                        GameFrame.scoreText.setForeground(Color.red);
                        GameFrame.scoreNumber.setForeground(Color.red);
                        break;
                }
                if (currentScore > Integer.parseInt(Menu.readHighscore())) {
                    GameFrame.scorePanel.add(GameFrame.HighscoreLabel);
                }
            }
        });
    }

    public void startTimer() {
        int delay;
        switch (Menu.difficulty.getText()) {
            case "LEICHT":
                delay = 800;
                break;
            case "NORMAL":
                delay = 600;
                break;
            case "SCHWER":
                delay = 400;
                break;
            case "EXTREM":
                delay = 200;
                break;
            default:
                delay = 1000;
                break;
        }
        timer.setDelay(delay);
        timer.start();
    }

    public void stopTimer() {
        timer.stop();
    }
}
