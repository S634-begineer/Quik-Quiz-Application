import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CLanguageQuiz extends JFrame implements ActionListener {
    private String[][] questions = new String[10][5];
    private String[][] answers = new String[10][2];
    private String[][] useranswer = new String[10][3];
    private JLabel qno, question, timerLabel;
    private JRadioButton opt1, opt2, opt3, opt4;
    private ButtonGroup buttonGroup;
    private JButton next, previous, submit;
    private int timer = 60;
    private int count = 0;
    private String name;

    private Timer countdownTimer;

    CLanguageQuiz(String name) {
        this.name = name;
        setTitle("C Language Quiz");
        setSize(1100, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(173, 216, 230));

        // Set image
        ImageIcon originalIcon = new ImageIcon(
                "C:\\Users\\del\\Desktop\\Quik Quiz Application\\Quize\\QuikQuizeImages\\quize.jpg");
        Image originalImage = originalIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(1100, 200, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        JLabel imageLabel = new JLabel(resizedIcon);
        add(imageLabel, BorderLayout.NORTH);

        // Questions and answers
        initializeQuestionsAndAnswers();

        // Create content panel
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(null);
        contentPanel.setBackground(new Color(173, 216, 230));
        add(contentPanel, BorderLayout.CENTER);

        // Initialize components
        initializeComponents(contentPanel);

        // Start the quiz
        start(count);

        // Initialize the countdown timer
        countdownTimer = new Timer(3000, e -> updateTimer());
        countdownTimer.start();

        setVisible(true);
    }

    private void initializeQuestionsAndAnswers() {
        questions[0] = new String[] { " Which of the following is the correct syntax for defining a function in C?",
                "function name() { // code }",
                "void function() { // code }", "def function() { // code }", "function() { // code }" };
        questions[1] = new String[] {
                " In C, what is the result of the expression 10 % 3?",
                "3",
                "1", "10", "0" };
        questions[2] = new String[] { " Which header file is required for input and output functions in C?", "stdlib.h",
                "stdio.h", "string.h", "math.h" };
        questions[3] = new String[] { " How do you declare an integer pointer in C?", "int *ptr;",
                "int ptr;", "pointer int;", "int ptr*;" };
        questions[4] = new String[] {
                " Which of the following is used to terminate a string in C?",
                "'\\n'", "'\\0'", "'.'", "' '" };
        questions[5] = new String[] { " Which of the following statements about the for loop in C is correct?",
                "The initialization, condition, and increment statements in a for loop are optional.",
                "The for loop cannot be nested within another for loop.",
                "The for loop must have exactly one statement within its body.",
                "The for loop is not suitable for iterating over arrays." };
        questions[6] = new String[] { " Which of the following operators has the highest precedence in C?", "*",
                "+", "=", "++" };
        questions[7] = new String[] { " What is the purpose of the return statement in a function?",
                "To end the program", "To return a value to the calling function",
                "To create a loop", "To pause the execution" };
        questions[8] = new String[] {
                " What is the correct way to include a user-defined header file named myheader.h?",
                "#include \"myheader.h\"",
                "#include <myheader.h>", "#import \"myheader.h", "#import <myheader.h>" };
        questions[9] = new String[] { " Which of the following statements is true about the switch statement in C?",
                "The switch statement can only be used with integer values.",
                "The switch statement requires case labels to be constant expressions.",
                "The switch statement can have multiple default labels.",
                "The switch statement evaluates all case labels regardless of the match." };

        answers[0][1] = "void function() { // code }";
        answers[1][1] = "1";
        answers[2][1] = "stdio.h";
        answers[3][1] = "int *ptr;";
        answers[4][1] = "\\0";
        answers[5][1] = "The initialization, condition, and increment statements in a for loop are optional.";
        answers[6][1] = "++";
        answers[7][1] = "To return a value to the calling function";
        answers[8][1] = "#include <myheader.h>";
        answers[9][1] = "The switch statement requires case labels to be constant expressions.";
    }

    private void initializeComponents(JPanel contentPanel) {
        // Initialize labels and buttons
        qno = new JLabel("  ");
        qno.setBounds(70, 10, 900, 30);
        qno.setFont(new Font("Times new Roman", Font.PLAIN, 22));
        contentPanel.add(qno);

        question = new JLabel("  ");
        question.setBounds(100, 10, 900, 30);
        question.setFont(new Font("Times new Roman", Font.PLAIN, 22));
        contentPanel.add(question);

        opt1 = new JRadioButton("");
        opt1.setBounds(100, 40, 500, 25);
        opt1.setBackground(new Color(173, 216, 230));
        contentPanel.add(opt1);

        opt2 = new JRadioButton("");
        opt2.setBounds(100, 60, 500, 25);
        opt2.setBackground(new Color(173, 216, 230));
        contentPanel.add(opt2);

        opt3 = new JRadioButton("");
        opt3.setBounds(100, 80, 500, 25);
        opt3.setBackground(new Color(173, 216, 230));
        contentPanel.add(opt3);

        opt4 = new JRadioButton("");
        opt4.setBounds(100, 100, 500, 25);
        opt4.setBackground(new Color(173, 216, 230));
        contentPanel.add(opt4);

        buttonGroup = new ButtonGroup();
        buttonGroup.add(opt1);
        buttonGroup.add(opt2);
        buttonGroup.add(opt3);
        buttonGroup.add(opt4);

        next = new JButton("Next");
        next.setBounds(700, 50, 150, 30);
        next.setBackground(Color.WHITE);
        next.setForeground(Color.BLACK);
        next.setFont(new Font("Arial", Font.BOLD, 18));
        next.addActionListener(this);
        contentPanel.add(next);

        previous = new JButton("Previous");
        previous.setBounds(700, 90, 150, 30);
        previous.setBackground(Color.WHITE);
        previous.setForeground(Color.BLACK);
        previous.setFont(new Font("Arial", Font.BOLD, 18));
        previous.setEnabled(false); // Initially disabled
        previous.addActionListener(this);
        contentPanel.add(previous);

        submit = new JButton("Submit");
        submit.setBounds(700, 130, 150, 30);
        submit.setBackground(Color.WHITE);
        submit.setForeground(Color.BLACK);
        submit.setFont(new Font("Arial", Font.BOLD, 18));
        submit.setEnabled(false);
        submit.addActionListener(this);
        contentPanel.add(submit);

        // Initialize timer label
        timerLabel = new JLabel("Time left: " + timer);
        timerLabel.setBounds(350, 200, 300, 35);
        timerLabel.setFont(new Font("Arial", Font.BOLD, 25));
        timerLabel.setForeground(Color.RED);
        contentPanel.add(timerLabel);
    }

    private void updateTimer() {
        if (timer > 0) {
            timer--;
            timerLabel.setText("Time left: " + timer);
        } else {
            handleTimeUp(); // Ensure this is called when time is up
        }
    }

    private void handleTimeUp() {
        // Stop the countdown timer
        countdownTimer.stop();
        // Update the timer label to show "Time's Up"
        timerLabel.setText("Time's Up!");

        // Disable all options and buttons
        opt1.setEnabled(false);
        opt2.setEnabled(false);
        opt3.setEnabled(false);
        opt4.setEnabled(false);
        next.setEnabled(false);
        previous.setEnabled(false);
        submit.setEnabled(false);

        // Submit the current answer and final score
        submitAnswer();
    }

    private void submitAnswer() {
        if (buttonGroup.getSelection() == null) {
            useranswer[count][0] = "";
        } else {
            useranswer[count][0] = buttonGroup.getSelection().getActionCommand();
        }

        // Calculate score
        int score = 0;
        for (int i = 0; i < useranswer.length; i++) {
            if (useranswer[i][0].equals(answers[i][1])) {
                score += 10; // Update score calculation based on correct answers
            }
        }

        setVisible(false);
        new Score(name, score);
    }

    public void start(int count) {
        // Check bounds for count
        if (count < 0 || count >= questions.length) {
            throw new IndexOutOfBoundsException("Question index out of bounds: " + count);
        }

        // Update question number and text
        qno.setText("" + (count + 1) + ".   ");
        question.setText(questions[count][0]);

        // Update options
        opt1.setText(questions[count][1]);
        opt1.setActionCommand(questions[count][1]);

        opt2.setText(questions[count][2]);
        opt2.setActionCommand(questions[count][2]);

        opt3.setText(questions[count][3]);
        opt3.setActionCommand(questions[count][3]);

        opt4.setText(questions[count][4]);
        opt4.setActionCommand(questions[count][4]);

        buttonGroup.clearSelection();

        // Restore previously selected answer
        if (count < useranswer.length) {
            // Restore answer for the 1st question
            if (useranswer[count][0] != null && !useranswer[count][0].isEmpty()) {
                switch (useranswer[count][0]) {
                    case "function name() { // code }":
                        opt1.setSelected(true);
                        break;
                    case "void function() { // code }":
                        opt2.setSelected(true);
                        break;
                    case "def function() { // code }":
                        opt3.setSelected(true);
                        break;
                    case "function() { // code }":
                        opt4.setSelected(true);
                        break;
                }
            }

            // Restore answer for the 2nd question
            if (useranswer[count][0] != null && !useranswer[count][0].isEmpty()) {
                switch (useranswer[count][0]) {
                    case "3":
                        opt1.setSelected(true);
                        break;
                    case "1":
                        opt2.setSelected(true);
                        break;
                    case "10":
                        opt3.setSelected(true);
                        break;
                    case "0":
                        opt4.setSelected(true);
                        break;
                }
            }

            // Restore answer for the 3rd question
            if (useranswer[count][0] != null && !useranswer[count][0].isEmpty()) {
                switch (useranswer[count][0]) {
                    case "stdlib.h":
                        opt1.setSelected(true);
                        break;
                    case "stdio.h":
                        opt2.setSelected(true);
                        break;
                    case "string.h":
                        opt3.setSelected(true);
                        break;
                    case "math.h":
                        opt4.setSelected(true);
                        break;
                }
            }

            // Restore answer for the 4th question
            if (useranswer[count][0] != null && !useranswer[count][0].isEmpty()) {
                switch (useranswer[count][0]) {
                    case "int *ptr;":
                        opt1.setSelected(true);
                        break;
                    case "int ptr;":
                        opt2.setSelected(true);
                        break;
                    case "pointer int;":
                        opt3.setSelected(true);
                        break;
                    case "int ptr*;":
                        opt4.setSelected(true);
                        break;
                }
            }

            // Restore answer for the 5th question
            if (useranswer[count][0] != null && !useranswer[count][0].isEmpty()) {
                switch (useranswer[count][0]) {
                    case "\\n":
                        opt1.setSelected(true);
                        break;
                    case "\\0":
                        opt2.setSelected(true);
                        break;
                    case ".":
                        opt3.setSelected(true);
                        break;
                    case "' '":
                        opt4.setSelected(true);
                        break;
                }
            }

            // Restore answer for the 6th question
            if (useranswer[count][0] != null && !useranswer[count][0].isEmpty()) {
                switch (useranswer[count][0]) {
                    case " The initialization, condition, and increment statements in a for loop are optional.":
                        opt1.setSelected(true);
                        break;
                    case "The for loop cannot be nested within another for loop.":
                        opt2.setSelected(true);
                        break;
                    case "The for loop must have exactly one statement within its body.":
                        opt3.setSelected(true);
                        break;
                    case "The for loop is not suitable for iterating over arrays.":
                        opt4.setSelected(true);
                        break;
                }
            }

            // Restore answer for the 7th question
            if (useranswer[count][0] != null && !useranswer[count][0].isEmpty()) {
                switch (useranswer[count][0]) {
                    case "*":
                        opt1.setSelected(true);
                        break;
                    case "+":
                        opt2.setSelected(true);
                        break;
                    case "=":
                        opt3.setSelected(true);
                        break;
                    case "++":
                        opt4.setSelected(true);
                        break;
                }
            }

            // Restore answer for the 8th question
            if (useranswer[count][0] != null && !useranswer[count][0].isEmpty()) {
                switch (useranswer[count][0]) {
                    case "To end the program":
                        opt1.setSelected(true);
                        break;
                    case "To return a value to the calling function":
                        opt2.setSelected(true);
                        break;
                    case "To create a loop":
                        opt3.setSelected(true);
                        break;
                    case "To pause the execution":
                        opt4.setSelected(true);
                        break;
                }
            }

            // Restore answer for the 9th question
            if (useranswer[count][0] != null && !useranswer[count][0].isEmpty()) {
                switch (useranswer[count][0]) {
                    case "#include \"myheader.h\"":
                        opt1.setSelected(true);
                        break;
                    case "#include <myheader.h>":
                        opt2.setSelected(true);
                        break;
                    case "#import \"myheader.h\"":
                        opt3.setSelected(true);
                        break;
                    case "#import <myheader.h>":
                        opt4.setSelected(true);
                        break;
                }
            }

            // Restore answer for the 10th question
            if (useranswer[count][0] != null && !useranswer[count][0].isEmpty()) {
                switch (useranswer[count][0]) {
                    case "The switch statement can only be used with integer values.":
                        opt1.setSelected(true);
                        break;
                    case "The switch statement requires case labels to be constant expressions.":
                        opt2.setSelected(true);
                        break;
                    case "The switch statement can have multiple default labels.":
                        opt3.setSelected(true);
                        break;
                    case "The switch statement evaluates all case labels regardless of the match.":
                        opt4.setSelected(true);
                        break;
                }
            }
        }

        // Enable/Disable buttons based on the current question
        previous.setEnabled(count > 0);
        next.setEnabled(count < questions.length - 1);
        submit.setEnabled(count == questions.length - 1);

        // Disable both Next and Previous buttons on the last question
        if (count == questions.length - 1) {
            next.setEnabled(false);
            previous.setEnabled(false);
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == next) {
            // Save the selected answer
            if (buttonGroup.getSelection() == null) {
                useranswer[count][0] = "";
            } else {
                useranswer[count][0] = buttonGroup.getSelection().getActionCommand();
            }

            if (count < questions.length - 1) {
                count++;
                start(count);
            }

        } else if (ae.getSource() == previous) {
            // Save the selected answer
            if (buttonGroup.getSelection() == null) {
                useranswer[count][0] = "";
            } else {
                useranswer[count][0] = buttonGroup.getSelection().getActionCommand();
            }

            if (count > 0) {
                count--;
                start(count);
            }
        } else if (ae.getSource() == submit) {
            submitAnswer();
        }
    }

    public static void main(String[] args) {
        new CLanguageQuiz("user");
    }
}
