package drawing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Main Drawing application with selection, modification, and deletion functionality
 * @author Dejan Zegarac IM133/2021
 */
public class DrawingApp extends JFrame {
    
    // Drawing modes
    private enum DrawingMode {
        SELECT, POINT, LINE, RECTANGLE, CIRCLE, DONUT
    }
    
    private PnlDrawing drawingPanel;
    private DrawingMode currentMode = DrawingMode.SELECT;
    
    // UI Components
    private JToggleButton btnSelect;
    private JToggleButton btnPoint;
    private JToggleButton btnLine;
    private JToggleButton btnRectangle;
    private JToggleButton btnCircle;
    private JToggleButton btnDonut;
    private JButton btnModify;
    private JButton btnDelete;
    private JLabel lblStatus;
    
    private ButtonGroup modeButtonGroup;
    
    // Line drawing state
    private Point lineStartPoint = null;
    
    public DrawingApp() {
        initializeComponents();
        setupLayout();
        setupEvents();
        
        // SPECIFIKACIJA: u naslovu prezime, ime i broj indeksa
        setTitle("Drawing Application - Dejan Zegarac IM133/2021");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        
        updateUI();
    }
    
    private void initializeComponents() {
        drawingPanel = new PnlDrawing();
        
        // Mode buttons
        btnSelect = new JToggleButton("Select");
        btnPoint = new JToggleButton("Point");
        btnLine = new JToggleButton("Line");
        btnRectangle = new JToggleButton("Rectangle");
        btnCircle = new JToggleButton("Circle");
        btnDonut = new JToggleButton("Donut");
        
        // Action buttons
        btnModify = new JButton("Modify");
        btnDelete = new JButton("Delete");
        
        // Status label
        lblStatus = new JLabel("Select a drawing mode and click on the canvas");
        
        // Group mode buttons
        modeButtonGroup = new ButtonGroup();
        modeButtonGroup.add(btnSelect);
        modeButtonGroup.add(btnPoint);
        modeButtonGroup.add(btnLine);
        modeButtonGroup.add(btnRectangle);
        modeButtonGroup.add(btnCircle);
        modeButtonGroup.add(btnDonut);
        
        // Set default selection
        btnSelect.setSelected(true);
        
        // Initially disable modify and delete buttons
        btnModify.setEnabled(false);
        btnDelete.setEnabled(false);
    }
    
    private void setupLayout() {
        setLayout(new BorderLayout());
        
        // Top toolbar
        JPanel toolbar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        toolbar.setBorder(BorderFactory.createTitledBorder("Drawing Tools"));
        
        toolbar.add(btnSelect);
        toolbar.add(new JSeparator(SwingConstants.VERTICAL));
        toolbar.add(btnPoint);
        toolbar.add(btnLine);
        toolbar.add(btnRectangle);
        toolbar.add(btnCircle);
        toolbar.add(btnDonut);
        toolbar.add(new JSeparator(SwingConstants.VERTICAL));
        toolbar.add(btnModify);
        toolbar.add(btnDelete);
        
        add(toolbar, BorderLayout.NORTH);
        
        // Drawing area
        JScrollPane scrollPane = new JScrollPane(drawingPanel);
        scrollPane.setPreferredSize(new Dimension(800, 600));
        add(scrollPane, BorderLayout.CENTER);
        
        // Status bar
        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        statusPanel.add(lblStatus);
        add(statusPanel, BorderLayout.SOUTH);
    }
    
    private void setupEvents() {
        // Mode button events
        btnSelect.addActionListener(e -> {
            currentMode = DrawingMode.SELECT;
            lineStartPoint = null;
            updateUI();
        });
        
        btnPoint.addActionListener(e -> {
            currentMode = DrawingMode.POINT;
            lineStartPoint = null;
            updateUI();
        });
        
        btnLine.addActionListener(e -> {
            currentMode = DrawingMode.LINE;
            lineStartPoint = null;
            updateUI();
        });
        
        btnRectangle.addActionListener(e -> {
            currentMode = DrawingMode.RECTANGLE;
            lineStartPoint = null;
            updateUI();
        });
        
        btnCircle.addActionListener(e -> {
            currentMode = DrawingMode.CIRCLE;
            lineStartPoint = null;
            updateUI();
        });
        
        btnDonut.addActionListener(e -> {
            currentMode = DrawingMode.DONUT;
            lineStartPoint = null;
            updateUI();
        });
        
        // Action button events
        btnModify.addActionListener(e -> modifySelectedShape());
        btnDelete.addActionListener(e -> deleteSelectedShape());
        
        // Drawing panel mouse events
        drawingPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleMouseClick(e.getX(), e.getY());
            }
        });
    }
    
    private void handleMouseClick(int x, int y) {
        switch (currentMode) {
            case SELECT:
                handleSelection(x, y);
                break;
            case POINT:
                drawPoint(x, y);
                break;
            case LINE:
                drawLine(x, y);
                break;
            case RECTANGLE:
                drawRectangle(x, y);
                break;
            case CIRCLE:
                drawCircle(x, y);
                break;
            case DONUT:
                drawDonut(x, y);
                break;
        }
    }
    
    private void handleSelection(int x, int y) {
        Shape shapeAt = drawingPanel.getShapeAt(x, y);
        if (shapeAt != null) {
            drawingPanel.selectShapeAt(x, y);
            lblStatus.setText("Selected: " + shapeAt.toString());
        } else {
            drawingPanel.deselectShape();
            lblStatus.setText("No shape selected");
        }
        updateUI();
    }
    
    /**
     * SPECIFIKACIJA: tačka (iscrtavanje na mestu klika)
     */
    private void drawPoint(int x, int y) {
        Point point = new Point(x, y);
        drawingPanel.addShape(point);
        lblStatus.setText("Point drawn at (" + x + ", " + y + ")");
    }
    
    /**
     * SPECIFIKACIJA: linija (iscrtavanje između dva klika)
     */
    private void drawLine(int x, int y) {
        if (lineStartPoint == null) {
            lineStartPoint = new Point(x, y);
            lblStatus.setText("Line start point set. Click again to set end point.");
        } else {
            Point endPoint = new Point(x, y);
            Line line = new Line(lineStartPoint, endPoint);
            drawingPanel.addShape(line);
            lblStatus.setText("Line drawn from (" + lineStartPoint.getX() + ", " + 
                            lineStartPoint.getY() + ") to (" + x + ", " + y + ")");
            lineStartPoint = null;
        }
    }
    
    /**
     * SPECIFIKACIJA: pravougaonik (gornja leva tačka na mestu klika, unos širine i visine putem dijaloga)
     */
    private void drawRectangle(int x, int y) {
        RectangleDialog dialog = new RectangleDialog(this, "Create Rectangle", true);
        dialog.setVisible(true);
        
        if (dialog.isConfirmed()) {
            Rectangle rectangle = dialog.getRectangle();
            rectangle.setUpperLeft(new Point(x, y));
            drawingPanel.addShape(rectangle);
            lblStatus.setText("Rectangle drawn at (" + x + ", " + y + ")");
        }
    }
    
    /**
     * SPECIFIKACIJA: krug (centar na mestu klika, unos dužine poluprečnika putem dijaloga)
     */
    private void drawCircle(int x, int y) {
        CircleDialog dialog = new CircleDialog(this, "Create Circle", true);
        dialog.setVisible(true);
        
        if (dialog.isConfirmed()) {
            Circle circle = dialog.getCircle();
            circle.setCenter(new Point(x, y));
            drawingPanel.addShape(circle);
            lblStatus.setText("Circle drawn at (" + x + ", " + y + ")");
        }
    }
    
    /**
     * SPECIFIKACIJA: krug sa rupom (centar na mestu klika, unos dužine spoljašnjeg i unutrašnjeg
     * poluprečnika putem dijaloga)
     */
    private void drawDonut(int x, int y) {
        DonutDialog dialog = new DonutDialog(this, "Create Donut", true);
        dialog.setVisible(true);
        
        if (dialog.isConfirmed()) {
            Donut donut = dialog.getDonut();
            donut.setCenter(new Point(x, y));
            drawingPanel.addShape(donut);
            lblStatus.setText("Donut drawn at (" + x + ", " + y + ")");
        }
    }
    
    /**
     * SPECIFIKACIJA: Modifikaciju obezbediti SAMO za selektovan objekat i prikazati trenutne 
     * osobine selektovanog objekta i obezbediti izmenu tih osobina koristeći MODALAN DIJALOG
     */
    private void modifySelectedShape() {
        Shape selectedShape = drawingPanel.getSelectedShape();
        if (selectedShape == null) {
            JOptionPane.showMessageDialog(this,
                    "Please select a shape first!",
                    "No Shape Selected",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (selectedShape instanceof Rectangle) {
            Rectangle rect = (Rectangle) selectedShape;
            RectangleDialog dialog = new RectangleDialog(this, "Modify Rectangle", true, rect);
            dialog.setVisible(true);
            if (dialog.isConfirmed()) {
                drawingPanel.updateDrawing();
                lblStatus.setText("Rectangle modified: " + rect.toString());
            }
        } else if (selectedShape instanceof Donut) {
            Donut donut = (Donut) selectedShape;
            DonutDialog dialog = new DonutDialog(this, "Modify Donut", true, donut);
            dialog.setVisible(true);
            if (dialog.isConfirmed()) {
                drawingPanel.updateDrawing();
                lblStatus.setText("Donut modified: " + donut.toString());
            }
        } else if (selectedShape instanceof Circle) {
            Circle circle = (Circle) selectedShape;
            CircleDialog dialog = new CircleDialog(this, "Modify Circle", true, circle);
            dialog.setVisible(true);
            if (dialog.isConfirmed()) {
                drawingPanel.updateDrawing();
                lblStatus.setText("Circle modified: " + circle.toString());
            }
        } else {
            JOptionPane.showMessageDialog(this,
                    "This shape type cannot be modified through dialog.\n" +
                    "Points and lines have fixed properties.",
                    "Cannot Modify",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    /**
     * SPECIFIKACIJA: Brisanje obezbediti SAMO za selektovan objekat uz obavezno pitanje 
     * za korisnika da li je siguran da želi da obriše i samo ukoliko je potvrdan odgovor 
     * obrisati selektovani objekat
     */
    private void deleteSelectedShape() {
        Shape selectedShape = drawingPanel.getSelectedShape();
        if (selectedShape == null) {
            JOptionPane.showMessageDialog(this,
                    "Please select a shape first!",
                    "No Shape Selected",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int result = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete the selected shape?\n" + 
                selectedShape.toString(),
                "Confirm Deletion",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        
        if (result == JOptionPane.YES_OPTION) {
            drawingPanel.removeShape(selectedShape);
            lblStatus.setText("Shape deleted: " + selectedShape.toString());
            updateUI();
        }
    }
    
    private void updateUI() {
        Shape selectedShape = drawingPanel.getSelectedShape();
        btnModify.setEnabled(selectedShape != null);
        btnDelete.setEnabled(selectedShape != null);
        
        // Update status based on current mode
        switch (currentMode) {
            case SELECT:
                if (selectedShape != null) {
                    lblStatus.setText("Selected: " + selectedShape.toString());
                } else {
                    lblStatus.setText("Selection mode - click on a shape to select it");
                }
                break;
            case POINT:
                lblStatus.setText("Point mode - click to draw a point");
                break;
            case LINE:
                if (lineStartPoint == null) {
                    lblStatus.setText("Line mode - click to set start point");
                } else {
                    lblStatus.setText("Line mode - click to set end point");
                }
                break;
            case RECTANGLE:
                lblStatus.setText("Rectangle mode - click to set upper-left corner");
                break;
            case CIRCLE:
                lblStatus.setText("Circle mode - click to set center");
                break;
            case DONUT:
                lblStatus.setText("Donut mode - click to set center");
                break;
        }
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
                
                new DrawingApp().setVisible(true);
            }
        });
    }
}
