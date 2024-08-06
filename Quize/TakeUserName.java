import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TakeUserName extends JFrame implements ActionListener {
    JButton submit, exit;
    JTextField takePlayerName;

    TakeUserName() {
        setTitle("Quik Quiz");
        setSize(900, 500);
        setLocationRelativeTo(null); // Center the frame on the screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null); // Use null layout for absolute positioning
        getContentPane().setBackground(Color.WHITE);

        // Load and resize the image
        ImageIcon originalIcon = new ImageIcon(
                "C:/Users/del/Desktop/Quik Quiz Application/Quize/QuikQuizeImages/usernameImage.png");
        Image originalImage = originalIcon.getImage();
        int width = 300; // Desired width
        int height = 300; // Desired height
        Image resizedImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);

        // Create a JLabel with the resized image
        JLabel imageLabel = new JLabel(resizedIcon);
        imageLabel.setBounds(0, 0, 300, 300); // Set bounds for image

        // Add the image label to the left side of the frame
        add(imageLabel);

        // Create a JPanel for content with absolute positioning
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(null); // Use null layout for absolute positioning
        contentPanel.setBounds(320, 0, 600, 500); // Set bounds for content panel
        contentPanel.setBackground(new Color(173, 216, 230)); // Light blue color

        // Hint for user using JLabel
        JLabel hintLabel = new JLabel("Enter Your Name");
        hintLabel.setFont(new Font("Times New Roman", Font.ITALIC, 25));
        hintLabel.setForeground(Color.BLACK);
        hintLabel.setBounds(190, 120, 200, 30); // Set bounds for JLabel
        contentPanel.add(hintLabel);

        // Create a JTextField with a proper size
        takePlayerName = new JTextField(); // No need to set columns
        takePlayerName.setPreferredSize(new Dimension(350, 30)); // Set preferred size
        takePlayerName.setBounds(100, 160, 350, 30); // Set bounds for JTextField
        takePlayerName.setFont(new Font("Arial", Font.ITALIC, 18));
        contentPanel.add(takePlayerName);

        // Create a start JButton with larger font
        submit = new JButton("Submit");
        submit.setBounds(125, 200, 100, 40); // Adjusted bounds for JButton
        submit.setBackground(Color.WHITE);
        submit.setForeground(Color.BLACK);
        submit.setFont(new Font("Arial", Font.BOLD, 16)); // Set font with size 16
        submit.addActionListener(this);
        contentPanel.add(submit);

        // Create an exit JButton with larger font
        exit = new JButton("Exit");
        exit.setBounds(330, 200, 100, 40); // Adjusted bounds for Exit button
        exit.setBackground(Color.WHITE);
        exit.setForeground(Color.BLACK);
        exit.setFont(new Font("Arial", Font.BOLD, 16)); // Set font with size 16
        exit.addActionListener(this);
        contentPanel.add(exit);

        // Add the content panel to the frame
        add(contentPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TakeUserName().setVisible(true));
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == submit) {
            String name = takePlayerName.getText(); // Correctly get the text from JTextField
            setVisible(false);
            new ChooseTopic(name); // Pass the name to the next frame
        } else if (event.getSource() == exit) {
            System.exit(0); // Exit the application
        }
    }
}
