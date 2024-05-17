import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameObject{

    private String name;
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected int speed; // in pixels per keypress
    protected JLabel entity;
    protected Color color;
    protected static ArrayList<GameObject> objects = new ArrayList<>();

    public GameObject(String name, int x, int y, int width, int height, int speed, Color color) {
        super();
        this.name = name;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.color = color;


        //entity als image darstellen damit es kein java swing ist
        entity = new JLabel();
        entity.setBackground(color);
        entity.setBounds(x, y, width, height);
        entity.setMinimumSize(new Dimension(width, height));
        entity.setPreferredSize(new Dimension(width, height));
        entity.setMaximumSize(new Dimension(width, height));
        entity.repaint();

        entity.setOpaque(true);

        objects.add(this);
    }

    public JLabel getEntity() {
        return entity;
    }


    public String getName() {
        return name;
    }


    public int getX() {
        return x;
    }


    public int getY() {
        return y;
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
}