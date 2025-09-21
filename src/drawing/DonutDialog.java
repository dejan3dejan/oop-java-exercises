package drawing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Modal dialog for Donut input/modification with color chooser
 * @author Dejan Zegarac IM133/2021
 */
public class DonutDialog extends JDialog {
    
    private JTextField txtX;
    private JTextField txtY;
    private JTextField txtOuterRadius;
    private JTextField txtInnerRadius;
    private JButton btnEdgeColor;
    private JButton btnFillColor;
    private JButton btnOK;
    private JButton btnCancel;
    
    private Color edgeColor = Color.BLACK;
    private Color fillColor = Color.WHITE;
    private Donut donut;
    private boolean confirmed = false;
    
    // Constructor for new donut
    public DonutDialog(Frame parent, String title, boolean modal) {
        super(parent, title, modal);
        initializeComponents();
        setupLayout();
        setupEvents();
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(parent);
    }
    
    // Constructor for modifying existing donut
    public DonutDialog(Frame parent, String title, boolean modal, Donut donut) {
        super(parent, title, modal);
        this.donut = donut;
        this.edgeColor = donut.getEdgeColor();
        this.fillColor = donut.getFillColor();
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
        txtOuterRadius = new JTextField(10);
        txtInnerRadius = new JTextField(10);
        
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
        
        // Outer radius
        gbc.gridx = 0; gbc.gridy = 2;
        mainPanel.add(new JLabel("Outer radius:"), gbc);
        gbc.gridx = 1;
        mainPanel.add(txtOuterRadius, gbc);
        
        // Inner radius
        gbc.gridx = 0; gbc.gridy = 3;
        mainPanel.add(new JLabel("Inner radius:"), gbc);
        gbc.gridx = 1;
        mainPanel.add(txtInnerRadius, gbc);
        
        // Colors
        gbc.gridx = 0; gbc.gridy = 4;
        mainPanel.add(new JLabel("Edge Color:"), gbc);
        gbc.gridx = 1;
        mainPanel.add(btnEdgeColor, gbc);
        
        gbc.gridx = 0; gbc.gridy = 5;
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
                Color selectedColor = JColorChooser.showDialog(DonutDialog.this, 
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
                Color selectedColor = JColorChooser.showDialog(DonutDialog.this, 
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
                    createOrUpdateDonut();
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
        if (donut != null) {
            txtX.setText(String.valueOf(donut.getCenter().getX()));
            txtY.setText(String.valueOf(donut.getCenter().getY()));
            txtOuterRadius.setText(String.valueOf(donut.getOuterRadius()));
            txtInnerRadius.setText(String.valueOf(donut.getInnerRadius()));
        }
    }
    
    private boolean validateInput() {
        try {
            String xText = txtX.getText().trim();
            String yText = txtY.getText().trim();
            String outerRadiusText = txtOuterRadius.getText().trim();
            String innerRadiusText = txtInnerRadius.getText().trim();
            
            if (xText.isEmpty() || yText.isEmpty() || outerRadiusText.isEmpty() || innerRadiusText.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Please fill in all fields!",
                        "Missing Input",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }
            
            Integer.parseInt(xText); // Validate format
            Integer.parseInt(yText); // Validate format
            int outerRadius = Integer.parseInt(outerRadiusText);
            int innerRadius = Integer.parseInt(innerRadiusText);
            
            if (outerRadius <= 0 || innerRadius <= 0) {
                JOptionPane.showMessageDialog(this,
                        "Both radii must be greater than 0!",
                        "Invalid Input",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }
            
            if (innerRadius >= outerRadius) {
                JOptionPane.showMessageDialog(this,
                        "Inner radius must be smaller than outer radius!",
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
    
    private void createOrUpdateDonut() {
        int x = Integer.parseInt(txtX.getText().trim());
        int y = Integer.parseInt(txtY.getText().trim());
        int outerRadius = Integer.parseInt(txtOuterRadius.getText().trim());
        int innerRadius = Integer.parseInt(txtInnerRadius.getText().trim());
        
        if (donut == null) {
            // Create new donut
            donut = new Donut(new Point(x, y), outerRadius, innerRadius, edgeColor, fillColor);
        } else {
            // Update existing donut
            donut.setCenter(new Point(x, y));
            donut.setOuterRadius(outerRadius);
            donut.setInnerRadius(innerRadius);
            donut.setEdgeColor(edgeColor);
            donut.setFillColor(fillColor);
        }
    }
    
    public Donut getDonut() {
        return donut;
    }
    
    public boolean isConfirmed() {
        return confirmed;
    }
}
