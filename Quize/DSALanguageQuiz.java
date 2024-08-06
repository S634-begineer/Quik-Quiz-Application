import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DSALanguageQuiz extends JFrame implements ActionListener {
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

    DSALanguageQuiz(String name) {
        this.name = name;
        setTitle("DSA Language Quiz");
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
        questions[0] = new String[] { " What is the time complexity of accessing an element in an array by index?\r\n",
                "O(1)", "O(n)", "O(log n)",
                "O(n^2)" };
        questions[1] = new String[] { " Which data structure uses LIFO (Last In, First Out) order?", "Queue",
                "Stack", "Array", "Linked List" };
        questions[2] = new String[] { " What is the primary advantage of using a linked list over an array?",
                "Faster access to elements",
                "Fixed size", "Dynamic size", "Simpler implementation" };
        questions[3] = new String[] { " Which sorting algorithm has a worst-case time complexity of O(n log n)?",
                "Bubble Sort", "Insertion Sort", "Merge Sort", "Selection Sort" };
        questions[4] = new String[] { " In a binary search tree (BST), how are the nodes organized?\r\n", "Linked List",
                "In ascending order of keys", "In descending order of keys", "In a circular fashion" };
        questions[5] = new String[] { " Which data structure is used to implement a priority queue?", "<ul>",
                "Stack", "Heap", "Array" };
        questions[6] = new String[] {
                " Which algorithm is commonly used for finding the shortest path in a graph with non-negative weights?",
                "Dijkstra's Algorithm",
                "Kruskal's Algorithm", "Bellman-Ford Algorithm", "Floyd-Warshall Algorithm" };
        questions[7] = new String[] {
                " What is the time complexity of the depth-first search (DFS) algorithm on a graph?", "O(n)",
                "O(n + e) where e is the number of edges",
                "O(n^2)", "O(log n)" };
        questions[8] = new String[] {
                " Which traversal method is used to visit all nodes in a binary tree level by level?",
                "Preorder Traversal",
                "Inorder Traversal", "Postorder Traversal", "Level-order Traversal" };
        questions[9] = new String[] { " What is the main characteristic of a hash table?", "Ordered elements",
                "Fixed size", "Key-value pairs with efficient access", "Linear data structure" };

        answers[0][1] = "O(1)";
        answers[1][1] = "Stack";
        answers[2][1] = "Dynamic size";
        answers[3][1] = "Merge Sort";
        answers[4][1] = "In ascending order of keys";
        answers[5][1] = "Heap";
        answers[6][1] = "Dijkstra's Algorithm";
        answers[7][1] = "O(n + e) where e is the number of edges";
        answers[8][1] = "Level-order Traversal";
        answers[9][1] = " Key-value pairs with efficient access";
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
                    case "O(1)":
                        opt1.setSelected(true);
                        break;
                    case "O(n)":
                        opt2.setSelected(true);
                        break;
                    case "O(log n)":
                        opt3.setSelected(true);
                        break;
                    case "O(n^2)":
                        opt4.setSelected(true);
                        break;
                }
            }

            // Restore answer for the 2nd question
            if (useranswer[count][0] != null && !useranswer[count][0].isEmpty()) {
                switch (useranswer[count][0]) {
                    case "Queue":
                        opt1.setSelected(true);
                        break;
                    case "Stack":
                        opt2.setSelected(true);
                        break;
                    case "Array":
                        opt3.setSelected(true);
                        break;
                    case "Linked List":
                        opt4.setSelected(true);
                        break;
                }
            }

            // Restore answer for the 3rd question
            if (useranswer[count][0] != null && !useranswer[count][0].isEmpty()) {
                switch (useranswer[count][0]) {
                    case "Faster access to elements":
                        opt1.setSelected(true);
                        break;
                    case "Fixed size":
                        opt2.setSelected(true);
                        break;
                    case "Dynamic size":
                        opt3.setSelected(true);
                        break;
                    case "Simpler implementation":
                        opt4.setSelected(true);
                        break;
                }
            }

            // Restore answer for the 4th question
            if (useranswer[count][0] != null && !useranswer[count][0].isEmpty()) {
                switch (useranswer[count][0]) {
                    case "Bubble Sort":
                        opt1.setSelected(true);
                        break;
                    case "Insertion Sort":
                        opt2.setSelected(true);
                        break;
                    case "Merge Sort":
                        opt3.setSelected(true);
                        break;
                    case "Selection Sort":
                        opt4.setSelected(true);
                        break;
                }
            }

            // Restore answer for the 5th question
            if (useranswer[count][0] != null && !useranswer[count][0].isEmpty()) {
                switch (useranswer[count][0]) {
                    case "In random order":
                        opt1.setSelected(true);
                        break;
                    case "In ascending order of keys":
                        opt2.setSelected(true);
                        break;
                    case "In descending order of keys":
                        opt3.setSelected(true);
                        break;
                    case "In a circular fashion":
                        opt4.setSelected(true);
                        break;
                }
            }

            // Restore answer for the 6th question
            if (useranswer[count][0] != null && !useranswer[count][0].isEmpty()) {
                switch (useranswer[count][0]) {
                    case "Linked List":
                        opt1.setSelected(true);
                        break;
                    case "Stack":
                        opt2.setSelected(true);
                        break;
                    case "Heap":
                        opt3.setSelected(true);
                        break;
                    case "Array":
                        opt4.setSelected(true);
                        break;
                }
            }

            // Restore answer for the 7th question
            if (useranswer[count][0] != null && !useranswer[count][0].isEmpty()) {
                switch (useranswer[count][0]) {
                    case "O(n)":
                        opt1.setSelected(true);
                        break;
                    case "O(n + e) where e is the number of edges":
                        opt2.setSelected(true);
                        break;
                    case "O(n^2)":
                        opt3.setSelected(true);
                        break;
                    case "O(log n)":
                        opt4.setSelected(true);
                        break;
                }
            }

            // Restore answer for the 8th question
            if (useranswer[count][0] != null && !useranswer[count][0].isEmpty()) {
                switch (useranswer[count][0]) {
                    case "Preorder Traversal":
                        opt1.setSelected(true);
                        break;
                    case "Inorder Traversal":
                        opt2.setSelected(true);
                        break;
                    case "Postorder Traversal":
                        opt3.setSelected(true);
                        break;
                    case "Level-order Traversal":
                        opt4.setSelected(true);
                        break;
                }
            }

            // Restore answer for the 9th question
            if (useranswer[count][0] != null && !useranswer[count][0].isEmpty()) {
                switch (useranswer[count][0]) {
                    case "Ordered elements":
                        opt1.setSelected(true);
                        break;
                    case "Fixed size":
                        opt2.setSelected(true);
                        break;
                    case "Key-value pairs with efficient access":
                        opt3.setSelected(true);
                        break;
                    case "Linear data structure":
                        opt4.setSelected(true);
                        break;
                }
            }

            // Restore answer for the 10th question
            if (useranswer[count][0] != null && !useranswer[count][0].isEmpty()) {
                switch (useranswer[count][0]) {
                    case "Dijkstra's Algorithm":
                        opt1.setSelected(true);
                        break;
                    case "Kruskal's Algorithm":
                        opt2.setSelected(true);
                        break;
                    case "Bellman-Ford Algorithm":
                        opt3.setSelected(true);
                        break;
                    case "Floyd-Warshall Algorithm":
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
        new DSALanguageQuiz("user");
    }
}
