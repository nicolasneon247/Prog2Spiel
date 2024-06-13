import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.*;

public class Menu extends JFrame {
    private JButton start;
    public static JButton difficulty;
    private JButton exit;
    public static final String[] difficultys = {"LEICHT", "NORMAL", "SCHWER", "EXTREM"};
    private final Color[] colors = {Color.green, Color.orange, Color.RED, Color.BLUE};
    private int currentDifficulty = 0;
    private JPanel buttonPanel;
    private JLabel highScore;
    private final JLabel highScoreText;
    private JPanel highScorePanel;

    public Menu(String title) throws IOException {
        super(title);

        Container contentPane = getContentPane();
        setResizable(false);


        initializeButtons();
        contentPane.add(buttonPanel, BorderLayout.CENTER);


        BufferedImage bufferedImage = ImageIO.read(new File("Prog2Spiel/LOGONEU.png")); //Hier eigenes Logo einfügen
        JLabel header = new JLabel(new ImageIcon(bufferedImage));

        highScoreText = new JLabel("Highscore: ");
        highScoreText.setFont(new Font("Arial", Font.BOLD, 40));
        highScoreText.setForeground(Color.BLUE);
        highScoreText.setOpaque(true);

        highScore = new JLabel(readHighscore());
        highScore.setFont(new Font("Arial", Font.BOLD, 40));
        highScore.setForeground(Color.BLUE);
        highScore.setOpaque(true);

        highScorePanel = new JPanel();
        highScorePanel.setLayout(new BoxLayout(highScorePanel, BoxLayout.LINE_AXIS));
        highScorePanel.add(Box.createRigidArea(new Dimension(15, 50)));
        highScorePanel.add(highScoreText);
        highScorePanel.add(Box.createRigidArea(new Dimension(5, 50)));
        highScorePanel.add(highScore);

        contentPane.add(highScorePanel, BorderLayout.SOUTH);


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
                    new GameLogic();
                } catch (IOException | InterruptedException ex) {
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
    public static String readHighscore() {
        File file = new File("Prog2Spiel/highscore.txt");
        if (!file.exists()) {
            return "0";
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine();
            return (line != null) ? line : "0";
        } catch (IOException e) {
            e.printStackTrace();
            return "0";
        }
    }
}
