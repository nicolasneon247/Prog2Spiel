import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.swing.*;
import java.awt.*;
import java.awt.font.GraphicAttribute;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class GameObject implements ImageObject{

    private String name;
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected int speed; // in pixels per keypress
    protected JLabel entity;
    protected Image image;
    protected Color color;
    protected static ArrayList<GameObject> objects = new ArrayList<>();

    public GameObject(String name, int x, int y, int width, int height, int speed, Color color, Image image) throws IOException {
        super();
        this.name = name;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.color = color;
        this.image = image;

        BufferedImage Pimg = null;
        BufferedImage Eimg = null;
        try {
            Pimg = ImageIO.read(new File("Prog2Spiel/PikPng.com_dragon-tail-png_5611935.png"));
            Eimg = ImageIO.read(new File("Prog2Spiel/Unbenannt-1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(name == "Player") {
            assert Pimg != null;
            Image dimg = Pimg.getScaledInstance(width, height, BufferedImage.TYPE_INT_ARGB);

            ImageIcon icon = new ImageIcon(dimg);

            entity = new JLabel();
            entity.setIcon(icon);

            entity.setBounds(x, y, width, height);

            entity.setMinimumSize(new Dimension(width, height));
            entity.setPreferredSize(new Dimension(width, height));
            entity.setMaximumSize(new Dimension(width, height));

            entity.setOpaque(false);
            entity.repaint();

        }else {
            assert Eimg != null;
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
        objects.add(this);
    }

    public JLabel getEntity() {
        return this.entity;
    }


    public String getName() {
        return name;
    }

@Override
    public int getX() {
        return this.entity.getX();
    }


    public int getY() {
        return this.entity.getY();
    }

    @Override
    public Image getImage() {
        return image;
    }


    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void deleteObject(GameObject object){
        objects.remove(object);
        object = null;
    }

    public void setX(int location) {
        x = location;
    }

    public void setY(int location) {
        y = location;
    }

    public void OverlappingObstacle() {
        for (GameObject elm : objects) {
            if (elm.getName().equals("Enemy")) {
                boolean noOverlapX = this.getX() + this.getWidth() <= elm.getX() || this.getX() >= elm.getX() + elm.getWidth();
                boolean noOverlapY = this.getY() + this.getHeight() <= elm.getY() || this.getY() >= elm.getY() + elm.getHeight();
                if (!(noOverlapX || noOverlapY)) {
                    JOptionPane.showMessageDialog(GameLogic.game, "Game Over!");
                    System.exit(0);
                }
            }
        }
    }

}