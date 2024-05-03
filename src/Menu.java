import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame {
    private JButton start;
    private JButton difficulty;
    private JLabel whiteBoxNorth;
    private JLabel whiteBoxSouth;

    public Menu(String title){
        super(title);

        Container contentPane = getContentPane();
        setResizable(false);

        start = new JButton("Start");
        start.setFont(new Font("Arial", Font.BOLD, 40));
        start.setForeground(Color.GREEN);
        start.setBackground(Color.DARK_GRAY);
        start.setOpaque(true);
        start.setPreferredSize(new Dimension(200, 200));

        difficulty = new JButton("Difficulty");
        difficulty.setFont(new Font("Arial", Font.BOLD, 40));
        difficulty.setForeground(Color.ORANGE);
        difficulty.setBackground(Color.DARK_GRAY);
        difficulty.setOpaque(true);
        difficulty.setPreferredSize(new Dimension(300, 100));

        whiteBoxNorth = new JLabel("Highscore: ");
        whiteBoxNorth.setForeground(Color.CYAN);
        whiteBoxNorth.setBackground(Color.WHITE);
        whiteBoxNorth.setOpaque(true);
        whiteBoxNorth.setFont(new Font("Arial", Font.BOLD, 40));
        whiteBoxNorth.setPreferredSize(new Dimension(200, 50));

        whiteBoxSouth = new JLabel("");
        whiteBoxSouth.setForeground(Color.WHITE);
        whiteBoxSouth.setBackground(Color.WHITE);
        whiteBoxSouth.setOpaque(true);
        whiteBoxSouth.setPreferredSize(new Dimension(200, 50));

        contentPane.add(start, BorderLayout.CENTER);
        contentPane.add(difficulty, BorderLayout.EAST);
        contentPane.add(whiteBoxNorth, BorderLayout.NORTH);
        contentPane.add(whiteBoxSouth, BorderLayout.SOUTH);

        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameFrame game = new GameFrame("Flappy Bird");
                game.setVisible(true);
                game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                game.setSize(new Dimension(1920,1080));
            }
        });
    }

    public static void main(String[] args) {
        Menu menu = new Menu("Flappy Bird Menu");
        menu.setVisible(true);
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.setSize(new Dimension(700,500));
    }
}
