import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameFrame extends JFrame {

    public static JLabel scoreText;
    public static JLabel scoreNumber;
    public static JLabel HighscoreLabel;
    public static JPanel scorePanel;
    public static JPanel game;
    private static JButton menuButton;
    private static JPanel south;

    public GameFrame(String title, JPanel game) {
        super(title);
        GameFrame.game = game;

        Container contentPane = getContentPane();
        setResizable(false);
        initializeButtons();

        contentPane.add(south, BorderLayout.SOUTH);
        contentPane.add(scorePanel, BorderLayout.NORTH);
        contentPane.add(game, BorderLayout.CENTER);
    }

    public static void updateScore(int score) {
        scoreNumber.setText(Integer.toString(score));
    }

    public static int getScore() {
        if (scoreNumber != null) {
            return Integer.parseInt(scoreNumber.getText());
        }
        return 0;
    }

    public void initializeButtons() {
        menuButton = new JButton("Menu");
        menuButton.setFont(new Font("Arial", Font.BOLD, 40));
        menuButton.setForeground(Color.BLUE);
        menuButton.setBackground(Color.DARK_GRAY);
        menuButton.setOpaque(true);

        south = new JPanel();
        south.setLayout(new BoxLayout(south, BoxLayout.LINE_AXIS));
        south.add(menuButton);
        south.add(Box.createHorizontalGlue());

        scoreText = new JLabel("Score:");
        scoreText.setFont(new Font("Arial", Font.BOLD, 40));
        scoreText.setForeground(Color.GREEN);
        scoreText.setOpaque(true);
        scoreText.setToolTipText("Zeigt die Punktzahl an.");

        scoreNumber = new JLabel("0");
        scoreNumber.setFont(new Font("Arial", Font.BOLD, 40));
        scoreNumber.setForeground(Color.GREEN);
        scoreNumber.setOpaque(true);

        HighscoreLabel = new JLabel("NEUER HIGHSCORE!");
        HighscoreLabel.setFont(new Font("Arial", Font.BOLD, 40));
        HighscoreLabel.setForeground(Color.black);
        HighscoreLabel.setOpaque(true);

        scorePanel = new JPanel();
        scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.LINE_AXIS));
        scorePanel.add(scoreText);
        scorePanel.add(Box.createRigidArea(new Dimension(10, 50)));
        scorePanel.add(scoreNumber);
        scorePanel.add(Box.createHorizontalGlue());

        menuButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Player.checkForCollision = false;
                setVisible(false);
                dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                menuButton.setBackground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                menuButton.setBackground(Color.DARK_GRAY);
            }
        });
    }


}