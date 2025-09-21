package drawing;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Abstract base class for all shapes in Drawing application
 * @author Dejan Zegarac IM133/2021
 */
public abstract class Shape {
    
    protected boolean selected;
    protected Color edgeColor;
    protected Color fillColor;
    
    public Shape() {
        this(Color.BLACK, Color.WHITE);
    }
    
    public Shape(Color edgeColor, Color fillColor) {
        this.selected = false;
        this.edgeColor = edgeColor;
        this.fillColor = fillColor;
    }
    
    /**
     * Abstract method to draw the shape
     */
    public abstract void draw(Graphics g);
    
    /**
     * Abstract method to check if point is inside the shape
     */
    public abstract boolean contains(int x, int y);
    
    /**
     * Abstract toString method for shape description
     */
    public abstract String toString();
    
    // Selection methods
    public boolean isSelected() {
        return selected;
    }
    
    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    
    // Color methods
    public Color getEdgeColor() {
        return edgeColor;
    }
    
    public void setEdgeColor(Color edgeColor) {
        this.edgeColor = edgeColor;
    }
    
    public Color getFillColor() {
        return fillColor;
    }
    
    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }
}
