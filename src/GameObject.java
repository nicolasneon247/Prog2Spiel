import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameObject implements ImageObject {

    public static CopyOnWriteArrayList<GameObject> enemys = new CopyOnWriteArrayList<>();
    protected static ArrayList<GameObject> objects = new ArrayList<>();
    protected String name;
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected JLabel entity;
    protected Image image;

    public GameObject(String name, int x, int y, int width, int height) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        objects.add(this);
    }

    public JLabel getEntity() {
        return this.entity;
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

    public void deleteObject(GameObject object) {
        GameFrame.game.remove(object.getEntity());
        GameFrame.game.repaint();
        objects.remove(object);
        enemys.remove(object);
    }

    public void writeHighscore(int score) throws IOException {
        int currentHighscore = Integer.parseInt(Menu.readHighscore());
        if (score > currentHighscore) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("Prog2Spiel/highscore.txt"))) {
                bw.write(String.valueOf(score));
            }
        }
    }
}