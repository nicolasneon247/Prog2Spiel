import javax.swing.*;
import java.awt.*;
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

    public GameObject(String name, int x, int y, int width, int height, int speed, Color color, Image image) {
        super();
        this.name = name;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.color = color;
        this.image = image;


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

@Override
    public int getX() {
        //return x;
        return this.entity.getX();
    }


    public int getY() {
        //return y;
        return this.entity.getY();
    }

    @Override
    public Image getImage() {
        return null;
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

    public Boolean isOverlappingObstacle() {
        for (GameObject elm : objects) {
            if (elm.getName().equals("Enemy")) {
                boolean noOverlapX = this.getX() + this.getWidth() <= elm.getX() || this.getX() >= elm.getX() + elm.getWidth();
                boolean noOverlapY = this.getY() + this.getHeight() <= elm.getY() || this.getY() >= elm.getY() + elm.getHeight();
                if (!(noOverlapX || noOverlapY)) {
                    return true;
                }
            }
        }
        return false;
    }

}