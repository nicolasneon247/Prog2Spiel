import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class Enemy extends GameObject {

    public Enemy(int x, int y, int width, int height, boolean isTop) {
        super("Enemy", x, y, width, height);

        BufferedImage Eimg = null;
        try {
            if (isTop) {
                Eimg = ImageIO.read(new File("Prog2Spiel/Sprite-Baum_TOP.png"));
            } else {
                Eimg = ImageIO.read(new File("Prog2Spiel/Sprite-Baum.png"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (Eimg != null) {
            Image dimg = Eimg.getScaledInstance(width, height, BufferedImage.TYPE_INT_ARGB);
            ImageIcon icon = new ImageIcon(dimg);

            entity = new JLabel();
            entity.setIcon(icon);

            entity.setBounds(x, y, width, height);
            entity.setMinimumSize(new Dimension(width, height));
            entity.setPreferredSize(new Dimension(width, height));
            entity.setMaximumSize(new Dimension(width, height));
            entity.setOpaque(false);
            entity.repaint();
        }

        enemys.add(this);
    }
}