import stack.StackApp;
import drawing.DrawingApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Main launcher for all three applications (Stack, Sort, Drawing)
 * @author Dejan Zegarac IM133/2021
 */
public class Main extends JFrame {
    
    public Main() {
        setTitle("Application Launcher - Dejan Zegarac IM133/2021");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(550, 220);
        setLocationRelativeTo(null);
        
        setupLayout();
    }
    
    private void setupLayout() {
        setLayout(new BorderLayout());
        
        // Title panel
        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel("Objektno orijentisane informacione tehnologije 2024/2025");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        titlePanel.add(titleLabel);
        add(titlePanel, BorderLayout.NORTH);
        
        // Button panel
        JPanel buttonPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        
        JButton btnStack = new JButton("Stack Application");
        JButton btnDrawing = new JButton("Drawing Application");
        
        btnStack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        new StackApp().setVisible(true);
                    }
                });
            }
        });
        
        
        btnDrawing.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        new DrawingApp().setVisible(true);
                    }
                });
            }
        });
        
        buttonPanel.add(btnStack);
        buttonPanel.add(btnDrawing);
        
        add(buttonPanel, BorderLayout.CENTER);
        
        // Info panel
        JPanel infoPanel = new JPanel();
        JLabel infoLabel = new JLabel("Select an application to launch");
        infoLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        infoPanel.add(infoLabel);
        add(infoPanel, BorderLayout.SOUTH);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
                new Main().setVisible(true);
            }
        });
    }
}