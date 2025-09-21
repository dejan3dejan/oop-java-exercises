package drawing;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Rectangle class for Drawing application with color support
 * @author Dejan Zegarac IM133/2021
 */
public class Rectangle extends Shape {
    
    private Point upperLeft;
    private int width;
    private int height;
    
    public Rectangle() {
        this(new Point(0, 0), 10, 10, Color.BLACK, Color.WHITE);
    }
    
    public Rectangle(Point upperLeft, int width, int height) {
        this(upperLeft, width, height, Color.BLACK, Color.WHITE);
    }
    
    public Rectangle(Point upperLeft, int width, int height, Color edgeColor, Color fillColor) {
        super(edgeColor, fillColor);
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }
    
    @Override
    public void draw(Graphics g) {
        Color originalColor = g.getColor();
        
        // Fill the rectangle
        if (getFillColor() != null) {
            g.setColor(getFillColor());
            g.fillRect(upperLeft.getX(), upperLeft.getY(), width, height);
        }
        
        // Draw the edge
        g.setColor(getEdgeColor());
        g.drawRect(upperLeft.getX(), upperLeft.getY(), width, height);
        
        // Draw selection indicators
        if (isSelected()) {
            g.setColor(Color.BLUE);
            // Draw selection handles at corners
            int x = upperLeft.getX();
            int y = upperLeft.getY();
            g.fillRect(x - 3, y - 3, 6, 6);
            g.fillRect(x + width - 3, y - 3, 6, 6);
            g.fillRect(x - 3, y + height - 3, 6, 6);
            g.fillRect(x + width - 3, y + height - 3, 6, 6);
        }
        
        g.setColor(originalColor);
    }
    
    @Override
    public boolean contains(int x, int y) {
        return x >= upperLeft.getX() && x <= upperLeft.getX() + width &&
               y >= upperLeft.getY() && y <= upperLeft.getY() + height;
    }
    
    @Override
    public String toString() {
        return String.format("Rectangle [Upper left: (%d, %d), Width: %d, Height: %d]", 
                           upperLeft.getX(), upperLeft.getY(), width, height);
    }
    
    // Getters and setters
    public Point getUpperLeft() {
        return upperLeft;
    }
    
    public void setUpperLeft(Point upperLeft) {
        this.upperLeft = upperLeft;
    }
    
    public int getWidth() {
        return width;
    }
    
    public void setWidth(int width) {
        this.width = width;
    }
    
    public int getHeight() {
        return height;
    }
    
    public void setHeight(int height) {
        this.height = height;
    }
    
    public int getArea() {
        return width * height;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Rectangle rectangle = (Rectangle) obj;
        return width == rectangle.width && height == rectangle.height &&
               upperLeft.equals(rectangle.upperLeft);
    }
}

