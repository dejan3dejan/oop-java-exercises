package drawing;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Circle class for Drawing application with color support
 * @author Dejan Zegarac IM133/2021
 */
public class Circle extends Shape {
    
    protected Point center;
    protected int radius;
    
    public Circle() {
        this(new Point(0, 0), 10, Color.BLACK, Color.WHITE);
    }
    
    public Circle(Point center, int radius) {
        this(center, radius, Color.BLACK, Color.WHITE);
    }
    
    public Circle(Point center, int radius, Color edgeColor, Color fillColor) {
        super(edgeColor, fillColor);
        this.center = center;
        this.radius = radius;
    }
    
    @Override
    public void draw(Graphics g) {
        Color originalColor = g.getColor();
        
        int x = center.getX() - radius;
        int y = center.getY() - radius;
        int diameter = radius * 2;
        
        // Fill the circle
        if (getFillColor() != null) {
            g.setColor(getFillColor());
            g.fillOval(x, y, diameter, diameter);
        }
        
        // Draw the edge
        g.setColor(getEdgeColor());
        g.drawOval(x, y, diameter, diameter);
        
        // Draw selection indicator
        if (isSelected()) {
            g.setColor(Color.BLUE);
            // Draw selection handles
            g.fillRect(center.getX() - 3, center.getY() - 3, 6, 6); // Center
            g.fillRect(center.getX() + radius - 3, center.getY() - 3, 6, 6); // Right
            g.fillRect(center.getX() - radius - 3, center.getY() - 3, 6, 6); // Left
            g.fillRect(center.getX() - 3, center.getY() + radius - 3, 6, 6); // Bottom
            g.fillRect(center.getX() - 3, center.getY() - radius - 3, 6, 6); // Top
        }
        
        g.setColor(originalColor);
    }
    
    @Override
    public boolean contains(int x, int y) {
        double distance = Math.sqrt(Math.pow(x - center.getX(), 2) + Math.pow(y - center.getY(), 2));
        return distance <= radius;
    }
    
    @Override
    public String toString() {
        return String.format("Circle [Center: (%d, %d), Radius: %d]", 
                           center.getX(), center.getY(), radius);
    }
    
    // Getters and setters
    public Point getCenter() {
        return center;
    }
    
    public void setCenter(Point center) {
        this.center = center;
    }
    
    public int getRadius() {
        return radius;
    }
    
    public void setRadius(int radius) {
        this.radius = radius;
    }
    
    public double getArea() {
        return Math.PI * radius * radius;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Circle circle = (Circle) obj;
        return radius == circle.radius && center.equals(circle.center);
    }
}
