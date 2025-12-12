import javax.swing.*;

/**
 * Main entry point for the Sorted List application.
 */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SortedListGUI gui = new SortedListGUI();
            gui.setVisible(true);
        });
    }
}