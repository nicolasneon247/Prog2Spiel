import javax.swing.*;
import java.awt.*;

public class GameObject extends JPanel{

    private String name;
    protected double x;
    protected double y;
    protected double width;
    protected double height;

    public GameObject(String name, double x, double y, double width, double height) {
        super();
        this.name = name;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        g.drawOval(1000,500,100,100);
    }
}
