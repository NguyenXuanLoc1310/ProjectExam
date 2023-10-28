/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package loginform;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.table.DefaultTableModel;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class EditDialog extends JDialog {
    private JTextField nameField;
    private JTextField rollNoField;
    private JTextField subjectField;
    private JTextField startTimeField;
    private JTextField durationField;
    private JButton saveButton;
    private JButton cancelButton;

    public EditDialog() {
    }

    public EditDialog(JTextField nameField, JTextField rollNoField, JTextField subjectField, JTextField startTimeField, JTextField durationField, JButton saveButton, JButton cancelButton, String rollNo, String name, String subject, String startTime, String endTime) {
        this.nameField = nameField;
        this.rollNoField = rollNoField;
        this.subjectField = subjectField;
        this.startTimeField = startTimeField;
        this.durationField = durationField;
        this.saveButton = saveButton;
        this.cancelButton = cancelButton;
        this.rollNo = rollNo;
        this.name = name;
        this.subject = subject;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    private boolean confirmed = false;

    private String rollNo;
    private String name;
    private String subject;
    private String startTime;
    private String duration;
    private String endTime;

    public EditDialog(String rollNo, String name, String subject, String startTime, String duration) {
        this.rollNo = rollNo;
        this.name = name;
        this.subject = subject;
        this.startTime = startTime;
        this.duration = duration;

        setTitle("Edit Student Information");
        setSize(300, 200);
        setLayout(new GridLayout(6, 2));
        setLocationRelativeTo(null);

        rollNoField = new JTextField(10);
        rollNoField.setText(rollNo);
        add(new JLabel("Roll No:"));
        add(rollNoField);

        nameField = new JTextField(20);
        nameField.setText(name);
        add(new JLabel("Name:"));
        add(nameField);

        subjectField = new JTextField(15);
        subjectField.setText(subject);
        add(new JLabel("Subject:"));
        add(subjectField);

        startTimeField = new JTextField(10);
        startTimeField.setText(startTime);
        add(new JLabel("Start Time:"));
        add(startTimeField);

        durationField = new JTextField(10);
        durationField.setText(duration);
        add(new JLabel("Duration:"));
        add(durationField);

        saveButton = new JButton("Save");
        add(saveButton);

        cancelButton = new JButton("Cancel");
        add(cancelButton);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveChanges();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    public EditDialog(JTextField rollNoField, JTextField nameField, JTextField subjectField, JTextField startTimeField, JTextField durationField) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public EditDialog(StudentManagementApp aThis, DefaultTableModel tableModel, int selectedRow) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void saveChanges() {
        rollNo = rollNoField.getText();
        name = nameField.getText();
        subject = subjectField.getText();
        startTime = startTimeField.getText();
        duration = durationField.getText();
        endTime = calculateEndTime(startTime, duration);
        confirmed = true;
        dispose();
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
            e.printStackTrace();
            return "";
        }
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public String getRollNo() {
        return rollNo;
    }

    /**
     *
     * @return
     */
    @Override
    public String getName() {
        return name;
    }

    public String getSubject() {
        return subject;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getDuration() {
        return duration;
    }

    public String getEndTime() {
        return endTime;
    }
}

