package stack;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

/**
 * Stack application for managing Circle objects with LIFO principle
 * @author Dejan Zegarac IM133/2021
 */
public class StackApp extends JFrame {

    private Stack<Circle> circleStack;
    private DefaultListModel<Circle> listModel;
    private JList<Circle> lstCircles;
    private JButton btnAdd;
    private JButton btnRemove;
    private JButton btnSort;
    private boolean isSortedView = false;

    public StackApp() {
        circleStack = new Stack<>();
        listModel = new DefaultListModel<>();

        initializeComponents();
        setupLayout();
        setupEvents();

        setTitle("Stack Application - Dejan Zegarac IM133/2021");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
    }

    private void initializeComponents() {
        lstCircles = new JList<>(listModel);
        lstCircles.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lstCircles.setVisibleRowCount(10);

        btnAdd = new JButton("Add Circle to Stack");
        btnRemove = new JButton("Remove Circle from Stack");
        btnSort = new JButton("Sort by Area (Descending)");

        // Initially disable remove and sort buttons
        btnRemove.setEnabled(false);
        btnSort.setEnabled(false);
    }

    private void setupLayout() {
        setLayout(new BorderLayout());

        // Info panel at top
        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.add(new JLabel("LIFO: Last In First Out - newest items appear at the top"));
        add(topPanel, BorderLayout.NORTH);

        // List panel in center
        JPanel listPanel = new JPanel(new BorderLayout());
        listPanel.setBorder(BorderFactory.createTitledBorder("Circle Stack (LIFO Order)"));

        JScrollPane scrollPane = new JScrollPane(lstCircles);
        scrollPane.setPreferredSize(new Dimension(450, 250));
        listPanel.add(scrollPane, BorderLayout.CENTER);

        add(listPanel, BorderLayout.CENTER);

        // Button panel at bottom
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnRemove);
        buttonPanel.add(btnSort);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void setupEvents() {
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addCircleToStack();
            }
        });

        btnRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeCircleFromStack();
            }
        });

        btnSort.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isSortedView) {
                    showNormalView();
                } else {
                    sortCirclesByArea();
                }
            }
        });
    }

    /**
     * Add circle to stack using modal dialog
     */
    private void addCircleToStack() {
        CircleDialog dialog = new CircleDialog(this, "Add New Circle", true);
        dialog.setVisible(true);

        if (dialog.isConfirmed()) {
            Circle newCircle = dialog.getCircle();
            circleStack.push(newCircle);
            isSortedView = false; // Reset to normal view when adding
            updateListDisplay();

            JOptionPane.showMessageDialog(this,
                    "Circle added successfully!\n" + newCircle.toString(),
                    "Circle Added",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Remove circle from stack (LIFO) and show in read-only dialog
     */
    private void removeCircleFromStack() {
        if (circleStack.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Stack is empty!",
                    "Cannot Remove",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Get the top circle from stack (LIFO)
        Circle topCircle = circleStack.peek();

        CircleDialog dialog = new CircleDialog(this, "Remove Circle - View Only", true, topCircle);
        dialog.setVisible(true);

        // After dialog is closed, remove the circle
        circleStack.pop();
        isSortedView = false; // Reset to normal view when removing
        updateListDisplay();

        JOptionPane.showMessageDialog(this,
                "Circle removed successfully!\n" + topCircle.toString(),
                "Circle Removed",
                JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Update JList display with LIFO order (specifikacija)
     */
    private void updateListDisplay() {
        listModel.clear();

        if (isSortedView) {
            // Show sorted view without modifying the original stack
            ArrayList<Circle> circles = new ArrayList<>(circleStack);
            Collections.sort(circles, (c1, c2) -> Double.compare(c2.getArea(), c1.getArea()));
            
            for (Circle circle : circles) {
                listModel.addElement(circle);
            }
            
            btnSort.setText("Show Normal View");
        } else {
            // LIFO: Last In First Out - poslednji dodati na vrhu liste
            Stack<Circle> tempStack = new Stack<>();

            // Move all circles to temp stack (reverses order)
            while (!circleStack.isEmpty()) {
                tempStack.push(circleStack.pop());
            }

            // Add to list model (shows newest first) and restore original stack
            while (!tempStack.isEmpty()) {
                Circle circle = tempStack.pop();
                listModel.addElement(circle);
                circleStack.push(circle);
            }
            
            btnSort.setText("Sort by Area (Descending)");
        }

        // Update button states
        btnRemove.setEnabled(!circleStack.isEmpty());
        btnSort.setEnabled(circleStack.size() > 1);
    }

    /**
     * Sort circles by area in descending order
     * NAPOMENA: Ovo samo menja prikaz, ne menja originalni stack!
     */
    private void sortCirclesByArea() {
        if (circleStack.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Stack is empty - nothing to sort!",
                    "Cannot Sort",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (circleStack.size() == 1) {
            JOptionPane.showMessageDialog(this,
                    "Only one circle in stack - no need to sort!",
                    "Sort Info",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // Switch to sorted view (doesn't modify the original stack)
        isSortedView = true;
        updateListDisplay();

        JOptionPane.showMessageDialog(this,
                "Showing sorted view by area (descending order)!\n" +
                        "Stack operations (Add/Remove) still work with LIFO principle.\n" +
                        "Click 'Show Normal View' to return to LIFO order.",
                "Sorted View Active",
                JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Show normal LIFO view
     */
    private void showNormalView() {
        isSortedView = false;
        updateListDisplay();
        
        JOptionPane.showMessageDialog(this,
                "Showing normal LIFO view.\n" +
                        "Stack operations work with Last In First Out principle.",
                "Normal View Active",
                JOptionPane.INFORMATION_MESSAGE);
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

                new StackApp().setVisible(true);
            }
        });
    }
}