import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameObject extends JLabel{

    private String name;
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected int speed; // in pixels per keypress
    protected JLabel entity;
    protected Color color;
    protected static ArrayList<GameObject> objects = new ArrayList<>();
    //enemys zur arraylist hinzufügen um kollidierung mit player zu überprüfen
    protected static ArrayList<JLabel> enemys = new ArrayList<>();

    public GameObject(String name, int x, int y, int width, int height, int speed, Color color) {
        super();
        this.name = name;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.color = color;

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

    public void deleteObject(GameObject object){
        objects.remove(object);
        object = null;
    }
}