package drawing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Modal dialog for Circle input/modification with color chooser
 * @author Dejan Zegarac IM133/2021
 */
public class CircleDialog extends JDialog {
    
    private JTextField txtX;
    private JTextField txtY;
    private JTextField txtRadius;
    private JButton btnEdgeColor;
    private JButton btnFillColor;
    private JButton btnOK;
    private JButton btnCancel;
    
    private Color edgeColor = Color.BLACK;
    private Color fillColor = Color.WHITE;
    private Circle circle;
    private boolean confirmed = false;
    
    // Constructor for new circle
    public CircleDialog(Frame parent, String title, boolean modal) {
        super(parent, title, modal);
        initializeComponents();
        setupLayout();
        setupEvents();
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(parent);
    }
    
    // Constructor for modifying existing circle
    public CircleDialog(Frame parent, String title, boolean modal, Circle circle) {
        super(parent, title, modal);
        this.circle = circle;
        this.edgeColor = circle.getEdgeColor();
        this.fillColor = circle.getFillColor();
        initializeComponents();
        setupLayout();
        setupEvents();
        populateFields();
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(parent);
    }
    
    private void initializeComponents() {
        txtX = new JTextField(10);
        txtY = new JTextField(10);
        txtRadius = new JTextField(10);
        
        btnEdgeColor = new JButton("Edge Color");
        btnEdgeColor.setBackground(edgeColor);
        btnEdgeColor.setOpaque(true);
        
        btnFillColor = new JButton("Fill Color");
        btnFillColor.setBackground(fillColor);
        btnFillColor.setOpaque(true);
        
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
        
        // Colors
        gbc.gridx = 0; gbc.gridy = 3;
        mainPanel.add(new JLabel("Edge Color:"), gbc);
        gbc.gridx = 1;
        mainPanel.add(btnEdgeColor, gbc);
        
        gbc.gridx = 0; gbc.gridy = 4;
        mainPanel.add(new JLabel("Fill Color:"), gbc);
        gbc.gridx = 1;
        mainPanel.add(btnFillColor, gbc);
        
        add(mainPanel, BorderLayout.CENTER);
        
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(btnOK);
        buttonPanel.add(btnCancel);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    private void setupEvents() {
        btnEdgeColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color selectedColor = JColorChooser.showDialog(CircleDialog.this, 
                        "Choose Edge Color", edgeColor);
                if (selectedColor != null) {
                    edgeColor = selectedColor;
                    btnEdgeColor.setBackground(edgeColor);
                }
            }
        });
        
        btnFillColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color selectedColor = JColorChooser.showDialog(CircleDialog.this, 
                        "Choose Fill Color", fillColor);
                if (selectedColor != null) {
                    fillColor = selectedColor;
                    btnFillColor.setBackground(fillColor);
                }
            }
        });
        
        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validateInput()) {
                    createOrUpdateCircle();
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
            txtX.setText(String.valueOf(circle.getCenter().getX()));
            txtY.setText(String.valueOf(circle.getCenter().getY()));
            txtRadius.setText(String.valueOf(circle.getRadius()));
        }
    }
    
    private boolean validateInput() {
        try {
            String xText = txtX.getText().trim();
            String yText = txtY.getText().trim();
            String radiusText = txtRadius.getText().trim();
            
            if (xText.isEmpty() || yText.isEmpty() || radiusText.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Please fill in all fields!",
                        "Missing Input",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }
            
            Integer.parseInt(xText); // Validate format
            Integer.parseInt(yText); // Validate format
            int radius = Integer.parseInt(radiusText);
            
            if (radius <= 0) {
                JOptionPane.showMessageDialog(this,
                        "Radius must be greater than 0!",
                        "Invalid Input",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }
            
            return true;
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Please enter valid integer numbers for all fields!",
                    "Invalid Input",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    private void createOrUpdateCircle() {
        int x = Integer.parseInt(txtX.getText().trim());
        int y = Integer.parseInt(txtY.getText().trim());
        int radius = Integer.parseInt(txtRadius.getText().trim());
        
        if (circle == null) {
            // Create new circle
            circle = new Circle(new Point(x, y), radius, edgeColor, fillColor);
        } else {
            // Update existing circle
            circle.setCenter(new Point(x, y));
            circle.setRadius(radius);
            circle.setEdgeColor(edgeColor);
            circle.setFillColor(fillColor);
        }
    }
    
    public Circle getCircle() {
        return circle;
    }
    
    public boolean isConfirmed() {
        return confirmed;
    }
}
