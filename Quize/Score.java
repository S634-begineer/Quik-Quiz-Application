import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Score extends JFrame implements ActionListener {
    private JButton exitButton;

    Score(String name, int score) {
        setTitle("Score");
        setSize(900, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        getContentPane().setBackground(new Color(173, 216, 230));

        // Set image
        ImageIcon originalIcon = new ImageIcon(
                "C:\\Users\\del\\Desktop\\Quik Quiz Application\\Quize\\QuikQuizeImages\\score.png");
        Image originalImage = originalIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(900, 200, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        JLabel imageLabel = new JLabel(resizedIcon);
        add(imageLabel, BorderLayout.NORTH);

        // Add content panel
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridBagLayout());
        contentPanel.setBackground(new Color(173, 216, 230));
        add(contentPanel, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel heading = new JLabel("Thank You " + name + " For Playing");
        heading.setFont(new Font("Algerian", Font.BOLD, 26));
        heading.setForeground(Color.BLACK);
        contentPanel.add(heading, gbc);

        gbc.gridy = 1;
        JLabel userScore = new JLabel("Your Score: " + score);
        userScore.setFont(new Font("Arial", Font.BOLD, 27));
        userScore.setForeground(Color.RED);
        contentPanel.add(userScore, gbc);

        gbc.gridy = 2;
        exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Arial", Font.BOLD, 25));
        exitButton.setForeground(Color.BLACK);
        exitButton.setBackground(Color.WHITE);
        exitButton.addActionListener(this);
        contentPanel.add(exitButton, gbc);

        setVisible(true);
    }

    public static void main(String[] args) {
        new Score("User", 0);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == exitButton) {
            dispose(); // Use dispose() instead of setVisible(false) to close the frame properly
        }
    }
}
