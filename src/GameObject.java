import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameObject implements ImageObject{

    private String name;
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected int speed; // in pixels per keypress
    protected JLabel entity;
    protected Image image;
    protected static ArrayList<GameObject> objects = new ArrayList<>();
    //public static ArrayList<GameObject> enemys = new ArrayList<>();
    public static CopyOnWriteArrayList<GameObject> enemys = new CopyOnWriteArrayList<>();
    protected BufferedWriter bw;


    public GameObject(String name, int x, int y, int width, int height, int speed) {

        //Player und Enemy Seperieren

        super();
        this.name = name;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;

        ImageIcon Pimg = null;
        BufferedImage Eimg = null;
        try {
            Pimg = new ImageIcon("Prog2Spiel/Robo.gif");
            Eimg = ImageIO.read(new File("Prog2Spiel/´test3.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(name == "Player") {

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
        if(this.name == "Enemy"){
            enemys.add(this);
        }
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

    public void deleteObject(GameObject object) {
        GameFrame.game.remove(object.getEntity());
        GameFrame.game.repaint();
        objects.remove(object);
        enemys.remove(object);
    }

    public void setX(int location) {
        x = location;
    }

    public void setY(int location) {
        y = location;
    }

    public void OverlappingObstacle() throws IOException {

        for (GameObject elm : objects) {
            if (elm.getName().equals("Enemy")) {
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

    public void writeHighscore(int score) throws IOException {
        bw = new BufferedWriter(new FileWriter("Prog2Spiel/highscore.txt"));

        String scoreValue = String.valueOf(score);

        if(Integer.parseInt(Menu.readHighscore()) < Integer.parseInt(scoreValue)) {
            bw.write(scoreValue); //schreibt aktuell jeden score rein, nicht nur den höchsten //if score > read dann write
            bw.flush();
        }
    }
}