import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ChooseTopic extends JFrame implements ItemListener {
    String name;
    JRadioButton c, c2, java, pythRadioButton, dsJRadioButton, htmlJRadioButton, phpJRadioButton, madJRadioButton,
            dbmsJRadioButton, jsJRadioButton;

    ChooseTopic(String name) {
        this.name = name;
        setTitle("Choose Topic");
        setSize(1100, 500); // Increased height to accommodate the image
        setLocationRelativeTo(null); // Center the frame on the screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Set the background color of the content pane
        getContentPane().setBackground(new Color(173, 216, 230)); // Light blue color

        // Setup additional components
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(null); // Use null layout for absolute positioning
        contentPanel.setBounds(0, 0, 1100, 500); // Full bounds of the top part of the frame
        contentPanel.setBackground(new Color(173, 216, 230)); // Light blue color

        // Hint for user using JLabel
        JLabel label = new JLabel("Welcome " + name + ", Please Select Topic You Want!");
        label.setFont(new Font("Viner Hand ITC", Font.BOLD, 26)); // Adjusted font name
        label.setForeground(Color.BLACK);
        label.setBounds(250, 30, 700, 40); // Adjusted bounds to fit the text
        contentPanel.add(label);

        // Adding topics using radio buttons
        // Left side radio buttons
        c = new JRadioButton("C");
        c.setBounds(50, 100, 100, 25);
        c.setBackground(new Color(173, 216, 230));
        c.setForeground(Color.BLACK);
        c.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        c.addItemListener(this);
        contentPanel.add(c);

        c2 = new JRadioButton("C++");
        c2.setBounds(50, 130, 100, 25);
        c2.setBackground(new Color(173, 216, 230));
        c2.setForeground(Color.BLACK);
        c2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        c2.addItemListener(this);
        contentPanel.add(c2);

        java = new JRadioButton("Java");
        java.setBounds(50, 160, 100, 25);
        java.setBackground(new Color(173, 216, 230));
        java.setForeground(Color.BLACK);
        java.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        java.addItemListener(this);
        contentPanel.add(java);

        pythRadioButton = new JRadioButton("Python");
        pythRadioButton.setBounds(50, 190, 100, 25);
        pythRadioButton.setBackground(new Color(173, 216, 230));
        pythRadioButton.setForeground(Color.BLACK);
        pythRadioButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        pythRadioButton.addItemListener(this);
        contentPanel.add(pythRadioButton);

        dsJRadioButton = new JRadioButton("Data Structures");
        dsJRadioButton.setBounds(50, 220, 150, 25);
        dsJRadioButton.setBackground(new Color(173, 216, 230));
        dsJRadioButton.setForeground(Color.BLACK);
        dsJRadioButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        dsJRadioButton.addItemListener(this);
        contentPanel.add(dsJRadioButton);

        // Right side radio buttons
        htmlJRadioButton = new JRadioButton("HTML");
        htmlJRadioButton.setBounds(700, 100, 100, 25);
        htmlJRadioButton.setBackground(new Color(173, 216, 230));
        htmlJRadioButton.setForeground(Color.BLACK);
        htmlJRadioButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        htmlJRadioButton.addItemListener(this);
        contentPanel.add(htmlJRadioButton);

        phpJRadioButton = new JRadioButton("PHP");
        phpJRadioButton.setBounds(700, 130, 100, 25);
        phpJRadioButton.setBackground(new Color(173, 216, 230));
        phpJRadioButton.setForeground(Color.BLACK);
        phpJRadioButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        phpJRadioButton.addItemListener(this);
        contentPanel.add(phpJRadioButton);

        madJRadioButton = new JRadioButton("Mobile App Development");
        madJRadioButton.setBounds(700, 160, 260, 25);
        madJRadioButton.setBackground(new Color(173, 216, 230));
        madJRadioButton.setForeground(Color.BLACK);
        madJRadioButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        madJRadioButton.addItemListener(this);
        contentPanel.add(madJRadioButton);

        dbmsJRadioButton = new JRadioButton("DBMS");
        dbmsJRadioButton.setBounds(700, 190, 100, 25);
        dbmsJRadioButton.setBackground(new Color(173, 216, 230));
        dbmsJRadioButton.setForeground(Color.BLACK);
        dbmsJRadioButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        dbmsJRadioButton.addItemListener(this);
        contentPanel.add(dbmsJRadioButton);

        // Adding JavaScript radio button
        jsJRadioButton = new JRadioButton("JavaScript");
        jsJRadioButton.setBounds(700, 220, 120, 25);
        jsJRadioButton.setBackground(new Color(173, 216, 230));
        jsJRadioButton.setForeground(Color.BLACK);
        jsJRadioButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        jsJRadioButton.addItemListener(this);
        contentPanel.add(jsJRadioButton);

        // Adding a button group to ensure only one radio button can be selected at a
        // time
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(c);
        buttonGroup.add(c2);
        buttonGroup.add(java);
        buttonGroup.add(pythRadioButton);
        buttonGroup.add(dsJRadioButton);
        buttonGroup.add(htmlJRadioButton);
        buttonGroup.add(phpJRadioButton);
        buttonGroup.add(madJRadioButton);
        buttonGroup.add(dbmsJRadioButton);
        buttonGroup.add(jsJRadioButton);

        // Load and resize the image
        ImageIcon originalIcon = new ImageIcon(
                "C:/Users/del/Desktop/Quik Quiz Application/Quize/QuikQuizeImages/choseTopic.jpg");
        Image originalImage = originalIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(1100, 200, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);

        // Create a JLabel with the resized image
        JLabel imageLabel = new JLabel(resizedIcon);
        imageLabel.setBounds(0, 300, 1100, 200); // Set bounds for image to fill the bottom part of the frame

        // Add the image label to the frame
        add(imageLabel);

        // Add the content panel to the frame
        add(contentPanel);

        setVisible(true);
    }

    @Override
    public void itemStateChanged(ItemEvent event) {
        if (event.getSource() == c) {
            setVisible(false);
            new CLanguageQuiz(name);
        } else if (event.getSource() == c2) {
            setVisible(false);
            new oopsLanguageQuiz(name);
        } else if (event.getSource() == java) {
            setVisible(false);
            new JavaLanguageQuiz(name);
        } else if (event.getSource() == pythRadioButton) {
            setVisible(false);
            new PythonLanguageQuiz(name);
        } else if (event.getSource() == dsJRadioButton) {
            setVisible(false);
            new DSALanguageQuiz(name);
        } else if (event.getSource() == htmlJRadioButton) {
            setVisible(false);
            new HTMLLanguageQuiz(name);
        } else if (event.getSource() == phpJRadioButton) {
            setVisible(false);
            new PHPLanguageQuiz(name);
        } else if (event.getSource() == madJRadioButton) {
            setVisible(false);
            new MADLanguageQuiz(name);
        } else if (event.getSource() == dbmsJRadioButton) {
            setVisible(false);
            new SQLLanguageQuiz(name);
        } else if (event.getSource() == jsJRadioButton) {
            setVisible(false);
            new JavascriptLanguageQuiz(name);
        }
        throw new UnsupportedOperationException("Unimplemented method 'itemStateChanged'");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ChooseTopic("user"));
    }

}
