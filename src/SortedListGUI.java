import javax.swing.*;
import java.awt.*;

/**
 * GUI for the SortedList application.
 * Allows users to add strings and search for elements.
 */
public class SortedListGUI extends JFrame {
    private SortedList sortedList;
    private JTextField inputField;
    private JTextArea displayArea;
    private JButton addButton;
    private JButton searchButton;
    private JButton clearButton;

    public SortedListGUI() {
        sortedList = new SortedList();
        setupGUI();
    }

    private void setupGUI() {
        setTitle("Sorted List with Binary Search");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Top panel - input
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.add(new JLabel("Enter String:"));
        inputField = new JTextField(20);
        addButton = new JButton("Add");
        searchButton = new JButton("Search");
        clearButton = new JButton("Clear List");

        inputPanel.add(inputField);
        inputPanel.add(addButton);
        inputPanel.add(searchButton);
        inputPanel.add(clearButton);

        // Center panel - display
        displayArea = new JTextArea(20, 40);
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(displayArea);

        // Add components
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Event listeners
        addButton.addActionListener(e -> addElement());
        searchButton.addActionListener(e -> searchElement());
        clearButton.addActionListener(e -> clearList());
        inputField.addActionListener(e -> addElement());

        pack();
        setLocationRelativeTo(null);
    }

    private void addElement() {
        String input = inputField.getText().trim();
        if (input.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a string.");
            return;
        }

        sortedList.add(input);
        displayArea.append("Added: \"" + input + "\"\n");
        updateDisplay();
        inputField.setText("");
        inputField.requestFocus();
    }

    private void searchElement() {
        String input = inputField.getText().trim();
        if (input.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a string to search.");
            return;
        }

        int index = sortedList.search(input);
        displayArea.append("\nSearching for: \"" + input + "\"\n");

        if (index != -1) {
            displayArea.append("FOUND at index " + index + "\n\n");
        } else {
            int insertPos = sortedList.findInsertPosition(input);
            displayArea.append("NOT FOUND - Would be inserted at index " + insertPos + "\n\n");
        }

        inputField.setText("");
        inputField.requestFocus();
    }

    private void clearList() {
        sortedList = new SortedList();
        displayArea.setText("");
        inputField.setText("");
        inputField.requestFocus();
    }

    private void updateDisplay() {
        displayArea.append("\nCurrent List:\n");
        displayArea.append(sortedList.toString());
        displayArea.append("-------------------\n");
    }
}