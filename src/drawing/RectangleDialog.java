package drawing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Modal dialog for Rectangle input/modification with color chooser
 * @author Dejan Zegarac IM133/2021
 */
public class RectangleDialog extends JDialog {
    
    private JTextField txtX;
    private JTextField txtY;
    private JTextField txtWidth;
    private JTextField txtHeight;
    private JButton btnEdgeColor;
    private JButton btnFillColor;
    private JButton btnOK;
    private JButton btnCancel;
    
    private Color edgeColor = Color.BLACK;
    private Color fillColor = Color.WHITE;
    private Rectangle rectangle;
    private boolean confirmed = false;
    
    // Constructor for new rectangle
    public RectangleDialog(Frame parent, String title, boolean modal) {
        super(parent, title, modal);
        initializeComponents();
        setupLayout();
        setupEvents();
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(parent);
    }
    
    // Constructor for modifying existing rectangle
    public RectangleDialog(Frame parent, String title, boolean modal, Rectangle rectangle) {
        super(parent, title, modal);
        this.rectangle = rectangle;
        this.edgeColor = rectangle.getEdgeColor();
        this.fillColor = rectangle.getFillColor();
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
        txtWidth = new JTextField(10);
        txtHeight = new JTextField(10);
        
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
        
        // Width
        gbc.gridx = 0; gbc.gridy = 2;
        mainPanel.add(new JLabel("Width:"), gbc);
        gbc.gridx = 1;
        mainPanel.add(txtWidth, gbc);
        
        // Height
        gbc.gridx = 0; gbc.gridy = 3;
        mainPanel.add(new JLabel("Height:"), gbc);
        gbc.gridx = 1;
        mainPanel.add(txtHeight, gbc);
        
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
                Color selectedColor = JColorChooser.showDialog(RectangleDialog.this, 
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
                Color selectedColor = JColorChooser.showDialog(RectangleDialog.this, 
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
                    createOrUpdateRectangle();
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
        if (rectangle != null) {
            txtX.setText(String.valueOf(rectangle.getUpperLeft().getX()));
            txtY.setText(String.valueOf(rectangle.getUpperLeft().getY()));
            txtWidth.setText(String.valueOf(rectangle.getWidth()));
            txtHeight.setText(String.valueOf(rectangle.getHeight()));
        }
    }
    
    private boolean validateInput() {
        try {
            String xText = txtX.getText().trim();
            String yText = txtY.getText().trim();
            String widthText = txtWidth.getText().trim();
            String heightText = txtHeight.getText().trim();
            
            if (xText.isEmpty() || yText.isEmpty() || widthText.isEmpty() || heightText.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Please fill in all fields!",
                        "Missing Input",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }
            
            Integer.parseInt(xText); // Validate format
            Integer.parseInt(yText); // Validate format
            int width = Integer.parseInt(widthText);
            int height = Integer.parseInt(heightText);
            
            if (width <= 0 || height <= 0) {
                JOptionPane.showMessageDialog(this,
                        "Width and height must be greater than 0!",
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
    
    private void createOrUpdateRectangle() {
        int x = Integer.parseInt(txtX.getText().trim());
        int y = Integer.parseInt(txtY.getText().trim());
        int width = Integer.parseInt(txtWidth.getText().trim());
        int height = Integer.parseInt(txtHeight.getText().trim());
        
        if (rectangle == null) {
            // Create new rectangle
            rectangle = new Rectangle(new Point(x, y), width, height, edgeColor, fillColor);
        } else {
            // Update existing rectangle
            rectangle.setUpperLeft(new Point(x, y));
            rectangle.setWidth(width);
            rectangle.setHeight(height);
            rectangle.setEdgeColor(edgeColor);
            rectangle.setFillColor(fillColor);
        }
    }
    
    public Rectangle getRectangle() {
        return rectangle;
    }
    
    public boolean isConfirmed() {
        return confirmed;
    }
}
