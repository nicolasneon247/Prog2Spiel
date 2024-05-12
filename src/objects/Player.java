package objects;

import java.awt.*;

public class Player {
    public int x, y, width, height;
    public Player(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void tick(){

    }
    public void Render(Graphics g){
        g.setColor(Color.red);
        g.fillRect(x, y, width, height);
    }
}
