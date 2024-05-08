import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Menu extends JFrame {
    private JButton start;
    private JButton difficulty;
    private JButton exit;
    private final String[] difficultys = {"LEICHT", "NORMAL", "SCHWER", "EXTREM"};
    private final Color[] colors = {Color.green, Color.orange, Color.RED, Color.BLUE};
    private int currentDifficulty = 0;
    private JPanel buttonPanel;

    public Menu(String title) throws IOException {
        super(title);

        Container contentPane = getContentPane();
        setResizable(false);

        initializeButtons();
        contentPane.add(buttonPanel, BorderLayout.CENTER);


        BufferedImage bufferedImage = ImageIO.read(new File("Prog2Spiel/Flappy_Logo.png")); //Hier eigenes Logo einfügen
        JLabel header = new JLabel(new ImageIcon(bufferedImage));
        contentPane.add(header, BorderLayout.NORTH);

    }

    public void initializeButtons(){
        start = new JButton("Start");
        start.setForeground(Color.GREEN);
        start.setBackground(Color.DARK_GRAY);

        exit = new JButton("Exit");
        exit.setForeground(Color.RED);
        exit.setBackground(Color.DARK_GRAY);

        difficulty = new JButton(difficultys[currentDifficulty]);
        difficulty.setForeground(Color.GREEN);
        difficulty.setBackground(Color.DARK_GRAY);

        final JButton[] buttons = {start,exit,difficulty};

        for(JButton btn: buttons){
            btn.setFont(new Font("Arial", Font.BOLD, 40));
            btn.setOpaque(true);
            btn.setMinimumSize(new Dimension(250, 100));
            btn.setMaximumSize(new Dimension(250, 100));
            btn.setPreferredSize(new Dimension(250, 100));
        }

        start.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Game game = new Game();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                start.setBackground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                start.setBackground(Color.DARK_GRAY);
            }
        });
        exit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                exit.setBackground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                exit.setBackground(Color.DARK_GRAY);
            }
        });
        difficulty.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(currentDifficulty == 3) currentDifficulty = 0;
                else currentDifficulty += 1;
                difficulty.setText(difficultys[currentDifficulty]);
                difficulty.setForeground(colors[currentDifficulty]);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                difficulty.setBackground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                difficulty.setBackground(Color.DARK_GRAY);
            }
        });

        buttonPanel = new JPanel();
        buttonPanel.add(Box.createHorizontalGlue());
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
        buttonPanel.add(Box.createRigidArea(new Dimension(25, 50)));
        buttonPanel.add(start);
        buttonPanel.add(Box.createRigidArea(new Dimension(25, 50)));
        buttonPanel.add(difficulty);
        buttonPanel.add(Box.createRigidArea(new Dimension(25, 50)));
        buttonPanel.add(exit);
        buttonPanel.add(Box.createRigidArea(new Dimension(25, 50)));
        buttonPanel.add(Box.createHorizontalGlue());
    }
    public static void main(String[] args) throws IOException {
        Menu menu = new Menu("Flappy Bird Menu");
        menu.setVisible(true);
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.setSize(new Dimension(900,700));
    }
}
