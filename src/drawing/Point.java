package drawing;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Point class for Drawing application with color support
 * @author Dejan Zegarac IM133/2021
 */
public class Point extends Shape {
    
    private int x;
    private int y;
    
    public Point() {
        this(0, 0, Color.BLACK);
    }
    
    public Point(int x, int y) {
        this(x, y, Color.BLACK);
    }
    
    public Point(int x, int y, Color edgeColor) {
        super(edgeColor, edgeColor);
        this.x = x;
        this.y = y;
    }
    
    @Override
    public void draw(Graphics g) {
        Color originalColor = g.getColor();
        
        if (isSelected()) {
            g.setColor(Color.BLUE);
            g.drawLine(x - 4, y, x + 4, y);
            g.drawLine(x, y - 4, x, y + 4);
        }
        
        g.setColor(getEdgeColor());
        g.drawLine(x - 2, y, x + 2, y);
        g.drawLine(x, y - 2, x, y + 2);
        
        g.setColor(originalColor);
    }
    
    @Override
    public boolean contains(int x, int y) {
        return Math.sqrt((this.x - x) * (this.x - x) + (this.y - y) * (this.y - y)) <= 3;
    }
    
    @Override
    public String toString() {
        return String.format("Point [(%d, %d)]", x, y);
    }
    
    // Getters and setters
    public int getX() {
        return x;
    }
    
    public void setX(int x) {
        this.x = x;
    }
    
    public int getY() {
        return y;
    }
    
    public void setY(int y) {
        this.y = y;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Point point = (Point) obj;
        return x == point.x && y == point.y;
    }
}

