import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MADLanguageQuiz extends JFrame implements ActionListener {
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

    MADLanguageQuiz(String name) {
        this.name = name;
        setTitle("MAD Language Quiz");
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
        countdownTimer = new Timer(1000, e -> updateTimer());
        countdownTimer.start();

        setVisible(true);
    }

    private void initializeQuestionsAndAnswers() {
        questions[0] = new String[] { " Which of the following is the main component of an Android application?",
                "Activity", "Service", "Broadcast Receiver",
                "Content Provider" };
        questions[1] = new String[] { " What is the purpose of the AndroidManifest.xml file?",
                "To define the layout of activities",
                "To declare application components and permissions", " To manage application resources",
                "To handle user input" };
        questions[2] = new String[] { " Which method is called when an Android activity is first created?",
                " onStart()",
                "onResume()", "onCreate()", "onPause()" };
        questions[3] = new String[] {
                " Which of the following is used to define the user interface in an Android application?", "XML files",
                "Java classes", "Manifest files", "Gradle scripts" };
        questions[4] = new String[] { "What is an Intent used for in Android?", " To manage user preferences",
                "To start or communicate between components", "To handle background tasks",
                "To display user notifications" };
        questions[5] = new String[] { " Which component is used to perform background operations in Android?",
                "Activity",
                "Service", "Content Provider", "Broadcast Receiver" };
        questions[6] = new String[] {
                " Which Android component is used to respond to system-wide broadcast announcements?\r\n", "Activity",
                "Service", "Broadcast Receiver", "Content Provider" };
        questions[7] = new String[] { " What is the primary role of a Content Provider in Android?",
                "To manage application settings", "To provide data to other applications",
                "To handle user interactions", "To run background tasks" };
        questions[8] = new String[] { " What is the purpose of Gradle in an Android project?",
                "To design user interfaces",
                "To compile and build the application", "To manage application permissions",
                "To handle runtime exceptions" };
        questions[9] = new String[] {
                " Which of the following Android classes is used to manage the application’s UI?\r\n",
                "Fragment", "Service", "BroadcastReceiver", "ContentProvider" };

        answers[0][1] = "Activity";
        answers[1][1] = "To declare application components and permissions";
        answers[2][1] = "onCreate()";
        answers[3][1] = "XML files";
        answers[4][1] = "To start or communicate between components";
        answers[5][1] = "Service";
        answers[6][1] = "Broadcast Receiver";
        answers[7][1] = "To provide data to other applications";
        answers[8][1] = "To compile and build the application";
        answers[9][1] = "Fragment";
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
                    case "Activity":
                        opt1.setSelected(true);
                        break;
                    case "Service":
                        opt2.setSelected(true);
                        break;
                    case "Broadcast Receiver":
                        opt3.setSelected(true);
                        break;
                    case "Content Provider":
                        opt4.setSelected(true);
                        break;
                }
            }

            // Restore answer for the 2nd question
            if (useranswer[count][0] != null && !useranswer[count][0].isEmpty()) {
                switch (useranswer[count][0]) {
                    case "To define the layout of activities":
                        opt1.setSelected(true);
                        break;
                    case "To declare application components and permissions":
                        opt2.setSelected(true);
                        break;
                    case "To manage application resources":
                        opt3.setSelected(true);
                        break;
                    case "To handle user input":
                        opt4.setSelected(true);
                        break;
                }
            }

            // Restore answer for the 3rd question
            if (useranswer[count][0] != null && !useranswer[count][0].isEmpty()) {
                switch (useranswer[count][0]) {
                    case "onStart()":
                        opt1.setSelected(true);
                        break;
                    case "onResume()":
                        opt2.setSelected(true);
                        break;
                    case "onCreate()":
                        opt3.setSelected(true);
                        break;
                    case "onPause()":
                        opt4.setSelected(true);
                        break;
                }
            }

            // Restore answer for the 4th question
            if (useranswer[count][0] != null && !useranswer[count][0].isEmpty()) {
                switch (useranswer[count][0]) {
                    case "XML files":
                        opt1.setSelected(true);
                        break;
                    case "Java classes":
                        opt2.setSelected(true);
                        break;
                    case "Manifest files":
                        opt3.setSelected(true);
                        break;
                    case "Gradle scripts":
                        opt4.setSelected(true);
                        break;
                }
            }

            // Restore answer for the 5th question
            if (useranswer[count][0] != null && !useranswer[count][0].isEmpty()) {
                switch (useranswer[count][0]) {
                    case "To manage user preferences":
                        opt1.setSelected(true);
                        break;
                    case "To start or communicate between components":
                        opt2.setSelected(true);
                        break;
                    case "To handle background tasks":
                        opt3.setSelected(true);
                        break;
                    case "To display user notifications":
                        opt4.setSelected(true);
                        break;
                }
            }

            // Restore answer for the 6th question
            if (useranswer[count][0] != null && !useranswer[count][0].isEmpty()) {
                switch (useranswer[count][0]) {
                    case "Activity":
                        opt1.setSelected(true);
                        break;
                    case "Service":
                        opt2.setSelected(true);
                        break;
                    case "Content Provider":
                        opt3.setSelected(true);
                        break;
                    case "Broadcast Receiver":
                        opt4.setSelected(true);
                        break;
                }
            }

            // Restore answer for the 7th question
            if (useranswer[count][0] != null && !useranswer[count][0].isEmpty()) {
                switch (useranswer[count][0]) {
                    case "Activity":
                        opt1.setSelected(true);
                        break;
                    case "Service":
                        opt2.setSelected(true);
                        break;
                    case "Broadcast Receiver":
                        opt3.setSelected(true);
                        break;
                    case "Content Provider":
                        opt4.setSelected(true);
                        break;
                }
            }

            // Restore answer for the 8th question
            if (useranswer[count][0] != null && !useranswer[count][0].isEmpty()) {
                switch (useranswer[count][0]) {
                    case "To manage application settings":
                        opt1.setSelected(true);
                        break;
                    case "To provide data to other applications":
                        opt2.setSelected(true);
                        break;
                    case "To handle user interactions":
                        opt3.setSelected(true);
                        break;
                    case "To run background tasks":
                        opt4.setSelected(true);
                        break;
                }
            }

            // Restore answer for the 9th question
            if (useranswer[count][0] != null && !useranswer[count][0].isEmpty()) {
                switch (useranswer[count][0]) {
                    case " To design user interfaces":
                        opt1.setSelected(true);
                        break;
                    case "To compile and build the application":
                        opt2.setSelected(true);
                        break;
                    case "To manage application permissions":
                        opt3.setSelected(true);
                        break;
                    case "To handle runtime exceptions":
                        opt4.setSelected(true);
                        break;
                }
            }

            // Restore answer for the 10th question
            if (useranswer[count][0] != null && !useranswer[count][0].isEmpty()) {
                switch (useranswer[count][0]) {
                    case "Fragment":
                        opt1.setSelected(true);
                        break;
                    case "Service":
                        opt2.setSelected(true);
                        break;
                    case "BroadcastReceiver":
                        opt3.setSelected(true);
                        break;
                    case "ContentProvider":
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
        new MADLanguageQuiz("user");
    }
}
