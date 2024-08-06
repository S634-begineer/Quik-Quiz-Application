import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JavascriptLanguageQuiz extends JFrame implements ActionListener {
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

    JavascriptLanguageQuiz(String name) {
        this.name = name;
        setTitle("Javascript Language Quiz");
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
        questions[0] = new String[] { " What is the correct way to declare a variable in JavaScript?", "var myVar;",
                "variable myVar;", "int myVar;",
                "let myVar;" };
        questions[1] = new String[] { " Which operator is used for equality comparison in JavaScript?", "=",
                "==", "===", "!==" };
        questions[2] = new String[] { " How do you create a function in JavaScript?", "function myFunction() { }",
                "func myFunction() { }", "define myFunction() { }", "create myFunction() { }" };
        questions[3] = new String[] { " What is the output of console.log(typeof 42) in JavaScript?\r\n", "number",
                "integer", "undefined", "float" };
        questions[4] = new String[] {
                " Which method is used to convert a JavaScript string to a number?\r\n",
                "parseInt()", "toString()", "number()", "convert()" };
        questions[5] = new String[] { " What is the correct way to include JavaScript in an HTML document?",
                "<script src=\"script.js\"></script>",
                "<js src=\"script.js\"></js>", "<javascript src=\"script.js\"></javascript>",
                "<include src=\"script.js\"></include>" };
        questions[6] = new String[] { " What does NaN stand for in JavaScript?", "Not a Number",
                "Not available Number", "New and Numeric", " Null and Notable" };
        questions[7] = new String[] { " Which keyword is used to declare a constant variable in JavaScript?\r\n",
                "const", "let",
                "var", "constant" };
        questions[8] = new String[] { " Which JavaScript object method is used to convert an array to a string?",
                "toString()",
                "join()", "split()", "concat()" };
        questions[9] = new String[] { "What will alert(3 + 2 + \"7\") display?",
                "57", "32",
                "72", "5 + \"7\"" };

        answers[0][1] = "var myVar;";
        answers[1][1] = " == && === ";
        answers[2][1] = "function myFunction() { }";
        answers[3][1] = "number";
        answers[4][1] = "parseInt()";
        answers[5][1] = "<script src=\"script.js\"></script>";
        answers[6][1] = "Not a Number";
        answers[7][1] = "const";
        answers[8][1] = "join()";
        answers[9][1] = "57";
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
        opt1.setBounds(100, 40, 400, 25);
        opt1.setBackground(new Color(173, 216, 230));
        contentPanel.add(opt1);

        opt2 = new JRadioButton("");
        opt2.setBounds(100, 60, 400, 25);
        opt2.setBackground(new Color(173, 216, 230));
        contentPanel.add(opt2);

        opt3 = new JRadioButton("");
        opt3.setBounds(100, 80, 400, 25);
        opt3.setBackground(new Color(173, 216, 230));
        contentPanel.add(opt3);

        opt4 = new JRadioButton("");
        opt4.setBounds(100, 100, 400, 25);
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
        int score = 10;
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
                    case "var myVar;":
                        opt1.setSelected(true);
                        break;
                    case "variable myVar;":
                        opt2.setSelected(true);
                        break;
                    case "int myVar;":
                        opt3.setSelected(true);
                        break;
                    case "let myVar;":
                        opt4.setSelected(true);
                        break;
                }
            }

            // Restore answer for the 2nd question
            if (useranswer[count][0] != null && !useranswer[count][0].isEmpty()) {
                switch (useranswer[count][0]) {
                    case "=":
                        opt1.setSelected(true);
                        break;
                    case "==":
                        opt2.setSelected(true);
                        break;
                    case "===":
                        opt3.setSelected(true);
                        break;
                    case "!==":
                        opt4.setSelected(true);
                        break;
                }
            }

            // Restore answer for the 3rd question
            if (useranswer[count][0] != null && !useranswer[count][0].isEmpty()) {
                switch (useranswer[count][0]) {
                    case "function myFunction() { }":
                        opt1.setSelected(true);
                        break;
                    case "func myFunction() { }":
                        opt2.setSelected(true);
                        break;
                    case "define myFunction() { }":
                        opt3.setSelected(true);
                        break;
                    case "create myFunction() { }":
                        opt4.setSelected(true);
                        break;
                }
            }

            // Restore answer for the 4th question
            if (useranswer[count][0] != null && !useranswer[count][0].isEmpty()) {
                switch (useranswer[count][0]) {
                    case "number":
                        opt1.setSelected(true);
                        break;
                    case "integer":
                        opt2.setSelected(true);
                        break;
                    case "undefined":
                        opt3.setSelected(true);
                        break;
                    case "float":
                        opt4.setSelected(true);
                        break;
                }
            }

            // Restore answer for the 5th question
            if (useranswer[count][0] != null && !useranswer[count][0].isEmpty()) {
                switch (useranswer[count][0]) {
                    case "parseInt()":
                        opt1.setSelected(true);
                        break;
                    case "toString()":
                        opt2.setSelected(true);
                        break;
                    case "number()":
                        opt3.setSelected(true);
                        break;
                    case "convert()":
                        opt4.setSelected(true);
                        break;
                }
            }

            // Restore answer for the 6th question
            if (useranswer[count][0] != null && !useranswer[count][0].isEmpty()) {
                switch (useranswer[count][0]) {
                    case "<script src=\"script.js\"></script>":
                        opt1.setSelected(true);
                        break;
                    case "<js src=\"script.js\"></js>":
                        opt2.setSelected(true);
                        break;
                    case "<javascript src=\"script.js\"></javascript>":
                        opt3.setSelected(true);
                        break;
                    case "<include src=\"script.js\"></include>":
                        opt4.setSelected(true);
                        break;
                }
            }

            // Restore answer for the 7th question
            if (useranswer[count][0] != null && !useranswer[count][0].isEmpty()) {
                switch (useranswer[count][0]) {
                    case "Not a Number":
                        opt1.setSelected(true);
                        break;
                    case "Not available Number":
                        opt2.setSelected(true);
                        break;
                    case "New and Numeric":
                        opt3.setSelected(true);
                        break;
                    case "Null and Notable":
                        opt4.setSelected(true);
                        break;
                }
            }

            // Restore answer for the 8th question
            if (useranswer[count][0] != null && !useranswer[count][0].isEmpty()) {
                switch (useranswer[count][0]) {
                    case "const":
                        opt1.setSelected(true);
                        break;
                    case "let":
                        opt2.setSelected(true);
                        break;
                    case "var":
                        opt3.setSelected(true);
                        break;
                    case "constant":
                        opt4.setSelected(true);
                        break;
                }
            }

            // Restore answer for the 9th question
            if (useranswer[count][0] != null && !useranswer[count][0].isEmpty()) {
                switch (useranswer[count][0]) {
                    case "toString()":
                        opt1.setSelected(true);
                        break;
                    case "join()":
                        opt2.setSelected(true);
                        break;
                    case "split()":
                        opt3.setSelected(true);
                        break;
                    case "concat()":
                        opt4.setSelected(true);
                        break;
                }
            }

            // Restore answer for the 10th question
            if (useranswer[count][0] != null && !useranswer[count][0].isEmpty()) {
                switch (useranswer[count][0]) {
                    case "57":
                        opt1.setSelected(true);
                        break;
                    case "32":
                        opt2.setSelected(true);
                        break;
                    case "72":
                        opt3.setSelected(true);
                        break;
                    case "5 + \"7\"":
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
        new JavascriptLanguageQuiz("user");
    }
}
