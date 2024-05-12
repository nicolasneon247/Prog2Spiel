import javax.swing.*;
import java.awt.*;

public class GameObject extends JLabel{

    private String name;
    protected double x;
    protected double y;
    protected double width;
    protected double height;
    protected double speed;
    protected JLabel entity;

    public GameObject(String name, double x, double y, double width, double height, double speed) {
        super();
        this.name = name;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;

        entity = new JLabel();
        entity.setBackground(Color.red);
        entity.setBounds((int) x, (int) y, (int) width, (int) height);
        entity.setMinimumSize(new Dimension((int) width, (int) height));
        entity.setPreferredSize(new Dimension((int) width, (int) height));
        entity.setMaximumSize(new Dimension((int) width, (int) height));
        entity.repaint();

        entity.setOpaque(true);
    }

    public JLabel getEntity() {
        return entity;
    }
}