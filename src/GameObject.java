import javax.swing.*;
import java.awt.*;

public class GameObject extends JLabel{

    private String name;
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected int speed; // in pixels per keypress
    protected JLabel entity;

    public GameObject(String name, int x, int y, int width, int height, int speed) {
        super();
        this.name = name;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;

        entity = new JLabel();
        entity.setBackground(Color.red);
        entity.setBounds(x, y, width, height);
        entity.setMinimumSize(new Dimension(width, height));
        entity.setPreferredSize(new Dimension(width, height));
        entity.setMaximumSize(new Dimension(width, height));
        entity.repaint();

        entity.setOpaque(true);
    }

    public JLabel getEntity() {
        return entity;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}