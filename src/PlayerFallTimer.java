import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class PlayerFallTimer {

    private static final int DELAY = 5;
    protected Player player;
    private final Timer timer;

    public PlayerFallTimer(Player player) {
        this.player = player;
        this.timer = new Timer(DELAY, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.entity.setLocation(player.getX(), player.getY() + 3);
                try {
                    player.OverlappingObstacle();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    public void start() {
        timer.start();
    }

    public void stop() {
        timer.stop();
    }
}
