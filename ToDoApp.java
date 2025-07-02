import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ToDoApp extends JFrame {
    private DefaultListModel<String> taskListModel;
    private JList<String> taskList;
    private JTextField taskField;
    private JButton addButton, deleteButton;

    public ToDoApp() {
        // Frame Title
        setTitle("ToDo App");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // North panel: Input + Add Button
        JPanel topPanel = new JPanel(new BorderLayout());
        taskField = new JTextField();
        addButton = new JButton("Add");
        topPanel.add(taskField, BorderLayout.CENTER);
        topPanel.add(addButton, BorderLayout.EAST);

        // Center panel: Task List
        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);
        JScrollPane scrollPane = new JScrollPane(taskList);

        // South panel: Delete Button
        JPanel bottomPanel = new JPanel();
        deleteButton = new JButton("Delete Selected");
        bottomPanel.add(deleteButton);

        // Add panels to frame
        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        // Button Listeners
        addButton.addActionListener(e -> {
            String task = taskField.getText().trim();
            if (!task.isEmpty()) {
                taskListModel.addElement(task);
                taskField.setText("");
            }
        });

        deleteButton.addActionListener(e -> {
            int selectedIndex = taskList.getSelectedIndex();
            if (selectedIndex != -1) {
                taskListModel.remove(selectedIndex);
            } else {
                JOptionPane.showMessageDialog(this, "Please select a task to delete.");
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        // Run GUI on the Event Dispatch Thread
        SwingUtilities.invokeLater(ToDoApp::new);
    }
}
