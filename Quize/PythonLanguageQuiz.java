import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PythonLanguageQuiz extends JFrame implements ActionListener {
    private String[][] questions = new String[10][5];//questions array
    private String[][] answers = new String[10][2];//answer array to store original answer
    private String[][] useranswer = new String[10][3];//useranswer array for comparing user answer with original answers. 
    private JLabel qno, question, timerLabel;
    private JRadioButton opt1, opt2, opt3, opt4;
    private ButtonGroup buttonGroup;
    private JButton next, previous, submit;
    private int timer = 60;
    private int count = 0;
    private String name;

    private Timer countdownTimer;

    PythonLanguageQuiz(String name) {
        this.name = name;
        setTitle("Python Language Quiz");
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
        questions[0] = new String[] {
                " Which of the following is the correct syntax to output \"Hello, World\" in Python?\r\n",
                "print(\"Hello, World\")", "echo \"Hello, World\"", "printf(\"Hello, World\")",
                "cout << \"Hello, World\"" };
        questions[1] = new String[] { " What is the correct way to create a list in Python?", "list = (1, 2, 3)",
                "list = [1, 2, 3]", "list = {1, 2, 3}", "list = <1, 2, 3>" };
        questions[2] = new String[] { " Which of the following is used to define a function in Python?",
                "function myFunction():", "def myFunction():", "func myFunction():", "define myFunction():" };
        questions[3] = new String[] { " How do you insert a comment in Python?", "<!-- comment -->", "/* comment */",
                "// comment", "# comment" };
        questions[4] = new String[] { " Which Python data type is used to store text?\r\n", "int", "float", "str",
                "list" };
        questions[5] = new String[] { " Which of the following keywords is used for defining a class in Python",
                "class", "def", "object", "struct" };
        questions[6] = new String[] { " How do you handle exceptions in Python?", "try and except", "catch and finally",
                "throw and catch", " error and recover" };
        questions[7] = new String[] { " Which operator is used for exponentiation in Python?", "^", "*", "++" };
        questions[8] = new String[] { " What is the purpose of the return statement in a Python function?\r\n",
                "To end the function", "To return a value from the function", "To start the function",
                "To create a new function" };
        questions[9] = new String[] { " Which of the following is a mutable data type in Python?", "tuple", "str",
                "list", "int" };

        answers[0][1] = "print(\"Hello, World\")";
        answers[1][1] = "list = [1, 2, 3]";
        answers[2][1] = "def myFunction():";
        answers[3][1] = "# comment";
        answers[4][1] = "str";
        answers[5][1] = "class";
        answers[6][1] = "try and except";
        answers[7][1] = "**";
        answers[8][1] = "To return a value from the function";
        answers[9][1] = "list";
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
                    case "print(\"Hello, World\")":
                        opt1.setSelected(true);
                        break;
                    case "echo \"Hello, World\"":
                        opt2.setSelected(true);
                        break;
                    case "printf(\"Hello, World\")":
                        opt3.setSelected(true);
                        break;
                    case "cout << \"Hello, World\"":
                        opt4.setSelected(true);
                        break;
                }
            }

            // Restore answer for the 2nd question
            if (useranswer[count][0] != null && !useranswer[count][0].isEmpty()) {
                switch (useranswer[count][0]) {
                    case "list = (1, 2, 3)":
                        opt1.setSelected(true);
                        break;
                    case "list = [1, 2, 3]":
                        opt2.setSelected(true);
                        break;
                    case "list = {1, 2, 3}":
                        opt3.setSelected(true);
                        break;
                    case "list = <1, 2, 3>":
                        opt4.setSelected(true);
                        break;
                }
            }

            // Restore answer for the 3rd question
            if (useranswer[count][0] != null && !useranswer[count][0].isEmpty()) {
                switch (useranswer[count][0]) {
                    case "function myFunction():":
                        opt1.setSelected(true);
                        break;
                    case "def myFunction():":
                        opt2.setSelected(true);
                        break;
                    case "func myFunction():":
                        opt3.setSelected(true);
                        break;
                    case "define myFunction():":
                        opt4.setSelected(true);
                        break;
                }
            }

            // Restore answer for the 4th question
            if (useranswer[count][0] != null && !useranswer[count][0].isEmpty()) {
                switch (useranswer[count][0]) {
                    case "<!-- comment -->":
                        opt1.setSelected(true);
                        break;
                    case "/* comment */":
                        opt2.setSelected(true);
                        break;
                    case "// comment":
                        opt3.setSelected(true);
                        break;
                    case "# comment":
                        opt4.setSelected(true);
                        break;
                }
            }

            // Restore answer for the 5th question
            if (useranswer[count][0] != null && !useranswer[count][0].isEmpty()) {
                switch (useranswer[count][0]) {
                    case "int":
                        opt1.setSelected(true);
                        break;
                    case "float":
                        opt2.setSelected(true);
                        break;
                    case "str":
                        opt3.setSelected(true);
                        break;
                    case "list":
                        opt4.setSelected(true);
                        break;
                }
            }

            // Restore answer for the 6th question
            if (useranswer[count][0] != null && !useranswer[count][0].isEmpty()) {
                switch (useranswer[count][0]) {
                    case "class":
                        opt1.setSelected(true);
                        break;
                    case "def":
                        opt2.setSelected(true);
                        break;
                    case "object":
                        opt3.setSelected(true);
                        break;
                    case "struct":
                        opt4.setSelected(true);
                        break;
                }
            }

            // Restore answer for the 7th question
            if (useranswer[count][0] != null && !useranswer[count][0].isEmpty()) {
                switch (useranswer[count][0]) {
                    case "try and except":
                        opt1.setSelected(true);
                        break;
                    case "catch and finally":
                        opt2.setSelected(true);
                        break;
                    case "throw and catch":
                        opt3.setSelected(true);
                        break;
                    case "error and recover":
                        opt4.setSelected(true);
                        break;
                }
            }

            // Restore answer for the 8th question
            if (useranswer[count][0] != null && !useranswer[count][0].isEmpty()) {
                switch (useranswer[count][0]) {
                    case "^":
                        opt1.setSelected(true);
                        break;
                    case "**":
                        opt2.setSelected(true);
                        break;
                    case "*":
                        opt3.setSelected(true);
                        break;
                    case "++":
                        opt4.setSelected(true);
                        break;
                }
            }

            // Restore answer for the 9th question
            if (useranswer[count][0] != null && !useranswer[count][0].isEmpty()) {
                switch (useranswer[count][0]) {
                    case "To end the function":
                        opt1.setSelected(true);
                        break;
                    case "To return a value from the function":
                        opt2.setSelected(true);
                        break;
                    case "To start the function":
                        opt3.setSelected(true);
                        break;
                    case "To create a new function":
                        opt4.setSelected(true);
                        break;
                }
            }

            // Restore answer for the 10th question
            if (useranswer[count][0] != null && !useranswer[count][0].isEmpty()) {
                switch (useranswer[count][0]) {
                    case "tuple":
                        opt1.setSelected(true);
                        break;
                    case "str":
                        opt2.setSelected(true);
                        break;
                    case "list":
                        opt3.setSelected(true);
                        break;
                    case "int":
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
        new PythonLanguageQuiz("user");
    }
}
