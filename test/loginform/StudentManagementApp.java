package loginform;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class StudentManagementApp extends JFrame {
    private DefaultTableModel tableModel;
    private JTable studentsTable;
    private final JButton addButton;
    private JButton editButton;
    private JButton deleteButton;
    private JTextField nameField;
    private JTextField rollNoField;
    private JTextField subjectField;
    private JTextField startTimeField;
    private JTextField durationField;
    private int selectedRow = -1;

    public StudentManagementApp() {
        setTitle("Student Management App");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1500, 1000);
        setLocationRelativeTo(null);

        tableModel = new DefaultTableModel();
        tableModel.addColumn("Roll No");
        tableModel.addColumn("Name");
        tableModel.addColumn("Subject");
        tableModel.addColumn("Start Time");
        tableModel.addColumn("Duration");
        tableModel.addColumn("End Time");

        studentsTable = new JTable(tableModel);

        nameField = new JTextField(20);
        rollNoField = new JTextField(10);
        subjectField = new JTextField(15);
        startTimeField = new JTextField(10);
        durationField = new JTextField(10);

        addButton = new JButton("Add Student");
        addButton.setPreferredSize(new Dimension(150, 30));

        editButton = new JButton("Edit");
        editButton.setPreferredSize(new Dimension(100, 30));
        editButton.setEnabled(false);

        deleteButton = new JButton("Delete");
        deleteButton.setPreferredSize(new Dimension(100, 30));
        deleteButton.setEnabled(false);

        studentsTable.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            if (!e.getValueIsAdjusting()) {
                selectedRow = studentsTable.getSelectedRow();
                editButton.setEnabled(selectedRow != -1);
                deleteButton.setEnabled(selectedRow != -1);
            }
        });

        addButton.addActionListener((ActionEvent e) -> {
            String name1 = nameField.getText();
            String rollNo = rollNoField.getText();
            String subject = subjectField.getText();
            String startTime = startTimeField.getText();
            String duration = durationField.getText();
            if (!name1.isEmpty() && !rollNo.isEmpty() && !subject.isEmpty() && !startTime.isEmpty() && !duration.isEmpty()) {
                String endTime = calculateEndTime(startTime, duration);
                tableModel.addRow(new String[]{rollNo, name1, subject, startTime, duration, endTime});
                nameField.setText("");
                rollNoField.setText("");
                subjectField.setText("");
                startTimeField.setText("");
                durationField.setText("");
            }
        });

       editButton.addActionListener((ActionEvent e) -> {
           if (selectedRow != -1) {
               EditDialog editDialog = new EditDialog(StudentManagementApp.this, tableModel, selectedRow);
               editDialog.setVisible(true);
           }
        });

        deleteButton.addActionListener((ActionEvent e) -> {
            if (selectedRow != -1) {
                tableModel.removeRow(selectedRow);
            }
        });

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Roll No:"));
        inputPanel.add(rollNoField);
        inputPanel.add(new JLabel("Subject:"));
        inputPanel.add(subjectField);
        inputPanel.add(new JLabel("Start Time:"));
        inputPanel.add(startTimeField);
        inputPanel.add(new JLabel("Duration:"));
        inputPanel.add(durationField);
        inputPanel.add(addButton);
        inputPanel.add(editButton);
        inputPanel.add(deleteButton);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(new JScrollPane(studentsTable), BorderLayout.CENTER);
        getContentPane().add(inputPanel, BorderLayout.SOUTH);
    }

    private String calculateEndTime(String startTime, String duration) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        try {
            Date startTimeDate = sdf.parse(startTime);
            int durationInMinutes = Integer.parseInt(duration);
            long endTimeMillis = startTimeDate.getTime() + (durationInMinutes * 60000);
            Date endTimeDate = new Date(endTimeMillis);
            return sdf.format(endTimeDate);
        } catch (ParseException e) {
            return "";
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StudentManagementApp app = new StudentManagementApp();
            app.setVisible(true);
        });
    }
}
