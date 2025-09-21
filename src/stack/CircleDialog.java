package stack;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Modal dialog for Circle input/display
 * @author Dejan Zegarac IM133/2021
 */
public class CircleDialog extends JDialog {

    private JTextField txtX;
    private JTextField txtY;
    private JTextField txtRadius;
    private JButton btnOK;
    private JButton btnCancel;

    private Circle circle;
    private boolean confirmed = false;
    private boolean readOnly = false;

    // Constructor for adding new circle
    public CircleDialog(Frame parent, String title, boolean modal) {
        super(parent, title, modal);
        this.readOnly = false;
        initializeComponents();
        setupLayout();
        setupEvents();
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(parent);
    }

    // Constructor for displaying existing circle (read-only)
    public CircleDialog(Frame parent, String title, boolean modal, Circle circle) {
        super(parent, title, modal);
        this.circle = circle;
        this.readOnly = true;
        initializeComponents();
        setupLayout();
        setupEvents();
        populateFields();
        setFieldsReadOnly();
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(parent);
    }

    private void initializeComponents() {
        txtX = new JTextField(10);
        txtY = new JTextField(10);
        txtRadius = new JTextField(10);

        btnOK = new JButton("OK");
        btnCancel = new JButton("Cancel");
    }

    private void setupLayout() {
        setLayout(new BorderLayout());

        // Main panel with form fields
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // X coordinate
        gbc.gridx = 0; gbc.gridy = 0;
        mainPanel.add(new JLabel("X coordinate:"), gbc);
        gbc.gridx = 1;
        mainPanel.add(txtX, gbc);

        // Y coordinate
        gbc.gridx = 0; gbc.gridy = 1;
        mainPanel.add(new JLabel("Y coordinate:"), gbc);
        gbc.gridx = 1;
        mainPanel.add(txtY, gbc);

        // Radius
        gbc.gridx = 0; gbc.gridy = 2;
        mainPanel.add(new JLabel("Radius:"), gbc);
        gbc.gridx = 1;
        mainPanel.add(txtRadius, gbc);

        add(mainPanel, BorderLayout.CENTER);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(btnOK);
        buttonPanel.add(btnCancel);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void setupEvents() {
        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (readOnly) {
                    dispose();
                    return;
                }

                if (validateInput()) {
                    createCircle();
                    confirmed = true;
                    dispose();
                }
            }
        });

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmed = false;
                dispose();
            }
        });
    }

    private void populateFields() {
        if (circle != null) {
            txtX.setText(String.valueOf(circle.getX()));
            txtY.setText(String.valueOf(circle.getY()));
            txtRadius.setText(String.valueOf(circle.getRadius()));
        }
    }

    private void setFieldsReadOnly() {
        txtX.setEditable(false);
        txtY.setEditable(false);
        txtRadius.setEditable(false);
        btnOK.setText("Close");
        btnCancel.setVisible(false);

        // Visual indication that fields are read-only
        txtX.setBackground(Color.LIGHT_GRAY);
        txtY.setBackground(Color.LIGHT_GRAY);
        txtRadius.setBackground(Color.LIGHT_GRAY);
    }

    private boolean validateInput() {
        try {
            // Check if fields are empty
            String xText = txtX.getText().trim();
            String yText = txtY.getText().trim();
            String radiusText = txtRadius.getText().trim();

            if (xText.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Please enter X coordinate!",
                        "Missing Input",
                        JOptionPane.ERROR_MESSAGE);
                txtX.requestFocus();
                return false;
            }

            if (yText.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Please enter Y coordinate!",
                        "Missing Input",
                        JOptionPane.ERROR_MESSAGE);
                txtY.requestFocus();
                return false;
            }

            if (radiusText.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Please enter radius!",
                        "Missing Input",
                        JOptionPane.ERROR_MESSAGE);
                txtRadius.requestFocus();
                return false;
            }

            // Parse numbers
            Integer.parseInt(xText); // Validate format
            Integer.parseInt(yText); // Validate format
            int radius = Integer.parseInt(radiusText);

            if (radius <= 0) {
                JOptionPane.showMessageDialog(this,
                        "Radius must be greater than 0!",
                        "Invalid Input",
                        JOptionPane.ERROR_MESSAGE);
                txtRadius.requestFocus();
                return false;
            }

            return true;

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Please enter valid integer numbers for all fields!\n" +
                            "Example: X=100, Y=200, Radius=50",
                    "Invalid Input",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    private void createCircle() {
        int x = Integer.parseInt(txtX.getText().trim());
        int y = Integer.parseInt(txtY.getText().trim());
        int radius = Integer.parseInt(txtRadius.getText().trim());

        circle = new Circle(x, y, radius);
    }

    public Circle getCircle() {
        return circle;
    }

    public boolean isConfirmed() {
        return confirmed;
    }
}