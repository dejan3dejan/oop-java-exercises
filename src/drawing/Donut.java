package drawing;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Donut (Circle with hole) class for Drawing application with color support
 * @author Dejan Zegarac IM133/2021
 */
public class Donut extends Circle {
    
    private int innerRadius;
    
    public Donut() {
        this(new Point(0, 0), 20, 10, Color.BLACK, Color.WHITE);
    }
    
    public Donut(Point center, int outerRadius, int innerRadius) {
        this(center, outerRadius, innerRadius, Color.BLACK, Color.WHITE);
    }
    
    public Donut(Point center, int outerRadius, int innerRadius, Color edgeColor, Color fillColor) {
        super(center, outerRadius, edgeColor, fillColor);
        this.innerRadius = innerRadius;
    }
    
    @Override
    public void draw(Graphics g) {
        Color originalColor = g.getColor();
        
        int outerX = center.getX() - radius;
        int outerY = center.getY() - radius;
        int outerDiameter = radius * 2;
        
        int innerX = center.getX() - innerRadius;
        int innerY = center.getY() - innerRadius;
        int innerDiameter = innerRadius * 2;
        
        // Fill the outer circle
        if (getFillColor() != null) {
            g.setColor(getFillColor());
            g.fillOval(outerX, outerY, outerDiameter, outerDiameter);
            
            // "Cut out" the inner circle by filling with background color
            g.setColor(Color.WHITE); // Assuming white background
            g.fillOval(innerX, innerY, innerDiameter, innerDiameter);
        }
        
        // Draw the edges
        g.setColor(getEdgeColor());
        g.drawOval(outerX, outerY, outerDiameter, outerDiameter);
        g.drawOval(innerX, innerY, innerDiameter, innerDiameter);
        
        // Draw selection indicator
        if (isSelected()) {
            g.setColor(Color.BLUE);
            // Draw selection handles
            g.fillRect(center.getX() - 3, center.getY() - 3, 6, 6); // Center
            g.fillRect(center.getX() + radius - 3, center.getY() - 3, 6, 6); // Outer right
            g.fillRect(center.getX() - radius - 3, center.getY() - 3, 6, 6); // Outer left
            g.fillRect(center.getX() + innerRadius - 3, center.getY() - 3, 6, 6); // Inner right
            g.fillRect(center.getX() - innerRadius - 3, center.getY() - 3, 6, 6); // Inner left
        }
        
        g.setColor(originalColor);
    }
    
    @Override
    public boolean contains(int x, int y) {
        double distance = Math.sqrt(Math.pow(x - center.getX(), 2) + Math.pow(y - center.getY(), 2));
        return distance <= radius && distance >= innerRadius;
    }
    
    @Override
    public String toString() {
        return String.format("Donut [Center: (%d, %d), Outer radius: %d, Inner radius: %d]", 
                           center.getX(), center.getY(), radius, innerRadius);
    }
    
    // Getters and setters
    public int getInnerRadius() {
        return innerRadius;
    }
    
    public void setInnerRadius(int innerRadius) {
        this.innerRadius = innerRadius;
    }
    
    public int getOuterRadius() {
        return radius;
    }
    
    public void setOuterRadius(int outerRadius) {
        this.radius = outerRadius;
    }
    
    @Override
    public double getArea() {
        return Math.PI * (radius * radius - innerRadius * innerRadius);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Donut donut = (Donut) obj;
        return radius == donut.radius && innerRadius == donut.innerRadius &&
               center.equals(donut.center);
    }
}

