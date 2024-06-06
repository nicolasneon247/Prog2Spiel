import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.Struct;
import java.util.Objects;

public class GameFrame extends JFrame {

    private static JLabel scoreText;
    private static JLabel scoreNumber;
    private static JButton menuButton;
    private static JPanel south;
    private static JPanel scorePanel;
    public static JPanel game;

    public GameFrame(String title, JPanel game, String score){
        super(title);
        this.game = game;

        Container contentPane = getContentPane();
        setResizable(false);
        initializeButtons();

        contentPane.add(south, BorderLayout.SOUTH);
        contentPane.add(scorePanel, BorderLayout.NORTH);
        contentPane.add(game, BorderLayout.CENTER);
    }
    public void initializeButtons(){
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

        scorePanel = new JPanel();
        scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.LINE_AXIS));
        scorePanel.add(scoreText);
        scorePanel.add(Box.createRigidArea(new Dimension(10, 50)));
        scorePanel.add(scoreNumber);
        scorePanel.add(Box.createHorizontalGlue());

        menuButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
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
    public static void updateScore(int score){
        scoreNumber.setText(Integer.toString(score));
    }
    public static int getScore(){
        if (scoreNumber != null){
            return Integer.parseInt(scoreNumber.getText());
        }
        return 0;
    }


}