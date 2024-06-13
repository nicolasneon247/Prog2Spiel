import javax.swing.*;
import java.awt.*;
import java.io.IOException;

class Player extends GameObject {

    public Player(int x, int y, int width, int height) {
        super("Player", x, y, width, height);

        ImageIcon Pimg = null;
        try {
            Pimg = new ImageIcon("Prog2Spiel/Robo.gif");
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (Pimg != null) {
            Image scaledImage = Pimg.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);

            entity = new JLabel();
            entity.setIcon(scaledIcon);

            entity.setBounds(x, y, width, height);
            entity.setMinimumSize(new Dimension(width, height));
            entity.setPreferredSize(new Dimension(width, height));
            entity.setMaximumSize(new Dimension(width, height));
            entity.setOpaque(false);
            entity.repaint();
        }
    }
    public void OverlappingObstacle() throws IOException {
        for (GameObject elm : enemys) {
            boolean noOverlapX = this.getX() + this.getWidth() <= elm.getX() || this.getX() >= elm.getX() + elm.getWidth();
            boolean noOverlapY = this.getY() + this.getHeight() <= elm.getY() || this.getY() >= elm.getY() + elm.getHeight();
            boolean noHitGround = this.getY() < 700;
            if ((!noOverlapX && !noOverlapY) || !noHitGround) {
                UpdateScoreThread.running = false;
                JOptionPane.showMessageDialog(GameFrame.game, "Game Over!");
                writeHighscore(GameFrame.getScore());
                System.exit(0);
            }
        }
    }
}
