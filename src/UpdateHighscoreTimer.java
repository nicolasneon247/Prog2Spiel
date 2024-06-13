import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateHighscoreTimer {
    private Timer timer;

    public UpdateHighscoreTimer() {
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    GameFrame.updateScore(GameFrame.getScore() + 1);
            }
        });
    }

    public void startTimer() {
        int delay;
        switch (Menu.difficulty.getText()) {
            case "LEICHT":
                delay = 1000;
                break;
            case "NORMAL":
                delay = 750;
                break;
            case "SCHWER":
                delay = 500;
                break;
            case "EXTREM":
                delay = 300;
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
        //Bei collision in PlayerObject
    }
}
