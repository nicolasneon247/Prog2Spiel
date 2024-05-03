import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class GameFrame extends JFrame {

    private JLabel score;

    public GameFrame(String title){
        super(title);

        Container contentPane = getContentPane();
        setResizable(false);

        score = new JLabel("Score:", SwingConstants.CENTER);
        //score.setPreferredSize(new Dimension(50, 50));
        //score.setSize(new Dimension(50, 50));
        score.setFont(new Font("Arial", Font.BOLD, 40));
        score.setForeground(Color.GREEN);
        score.setBackground(Color.DARK_GRAY);
        score.setOpaque(true);
        score.setToolTipText("Zeigt die Punktzahl an.");
        contentPane = getContentPane();

        contentPane.add(score, BorderLayout.NORTH);
    }
}
