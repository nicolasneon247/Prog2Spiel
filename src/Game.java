import objects.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Game extends JFrame {

    private JPanel game;
    private Thread thread;
    private boolean running = false;

    //Game Objects
    public Player player = new Player(1000,500,64,64);

    public Game() throws IOException {

        BufferedImage player = ImageIO.read(new File("Prog2Spiel/bird.jpg"));
        game = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                //g.drawImage(player, 0, 0, null);
            }
        };
        game.setLayout(new BoxLayout(game, BoxLayout.LINE_AXIS));
        game.add(Box.createHorizontalGlue());

        BufferedImage bufferedImage = ImageIO.read(new File("Prog2Spiel/Background.jpg")); //Bild zu groß (beispielbild)
        JLabel background = new JLabel(new ImageIcon(bufferedImage));
        //game.add(background, BorderLayout.NORTH);

        game.setBackground(Color.darkGray);


        //Gamelogic





        launchGame(game);
    }
    public void start(){
        running = true;
        thread = new Thread(String.valueOf(this));
        thread.start();
    }
    public void stop(){
        running = false;
        try{
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    public void run(){
        while (running){
            System.out.println("test");
        }
    }


    public void tick(){
        player.tick();
    }

    public void Render(){

        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(2);
            bs = this.getBufferStrategy();
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.white);
        g.fillRect(0,0, this.getWidth(), this.getHeight());

        player.Render(g);


        bs.show();
        g.dispose();
    }



    public void launchGame(JPanel game){
        GameFrame launchGame = new GameFrame("Flappy Bird", game);

        run();
        launchGame.setVisible(true);
        launchGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        launchGame.setResizable(true); //vielleicht false setzen
        launchGame.setSize(new Dimension(1920,1080)); //richtige Fenstergröße muss noch gefunden werden
        //launchGame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        //tick();
        //Render();

    }

}
