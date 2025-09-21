package drawing;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Drawing panel class that extends JPanel
 * @author Dejan Zegarac IM133/2021
 */
public class PnlDrawing extends JPanel {
    
    private List<Shape> shapes;
    private Shape selectedShape;
    
    public PnlDrawing() {
        shapes = new ArrayList<>();
        selectedShape = null;
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(800, 600));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        
        // SPECIFIKACIJA: Metodu draw(Graphics g) je dozvoljeno pozivati samo u ovoj klasi
        for (Shape shape : shapes) {
            shape.draw(g);
        }
    }
    
    /**
     * Add shape to the drawing
     */
    public void addShape(Shape shape) {
        shapes.add(shape);
        repaint();
    }
    
    /**
     * Remove shape from the drawing
     */
    public void removeShape(Shape shape) {
        shapes.remove(shape);
        if (selectedShape == shape) {
            selectedShape = null;
        }
        repaint();
    }
    
    /**
     * Get shape at specified coordinates
     */
    public Shape getShapeAt(int x, int y) {
        // Iterate from the end (last drawn) to beginning to find the topmost shape
        for (int i = shapes.size() - 1; i >= 0; i--) {
            Shape shape = shapes.get(i);
            if (shape.contains(x, y)) {
                return shape;
            }
        }
        return null;
    }
    
    /**
     * Select shape at coordinates
     */
    public void selectShapeAt(int x, int y) {
        // Deselect current shape
        if (selectedShape != null) {
            selectedShape.setSelected(false);
            selectedShape = null;
        }
        
        // Find and select new shape
        Shape shape = getShapeAt(x, y);
        if (shape != null) {
            selectedShape = shape;
            selectedShape.setSelected(true);
        }
        
        repaint();
    }
    
    /**
     * Deselect current shape
     */
    public void deselectShape() {
        if (selectedShape != null) {
            selectedShape.setSelected(false);
            selectedShape = null;
            repaint();
        }
    }
    
    /**
     * Get currently selected shape
     */
    public Shape getSelectedShape() {
        return selectedShape;
    }
    
    /**
     * Get all shapes
     */
    public List<Shape> getShapes() {
        return new ArrayList<>(shapes);
    }
    
    /**
     * Clear all shapes
     */
    public void clearShapes() {
        shapes.clear();
        selectedShape = null;
        repaint();
    }
    
    /**
     * Update the drawing (force repaint)
     */
    public void updateDrawing() {
        repaint();
    }
}
