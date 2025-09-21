package drawing;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Line class for Drawing application with color support
 * @author Dejan Zegarac IM133/2021
 */
public class Line extends Shape {
    
    private Point startPoint;
    private Point endPoint;
    
    public Line() {
        this(new Point(0, 0), new Point(0, 0), Color.BLACK);
    }
    
    public Line(Point startPoint, Point endPoint) {
        this(startPoint, endPoint, Color.BLACK);
    }
    
    public Line(Point startPoint, Point endPoint, Color edgeColor) {
        super(edgeColor, edgeColor);
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }
    
    @Override
    public void draw(Graphics g) {
        Color originalColor = g.getColor();
        
        g.setColor(getEdgeColor());
        g.drawLine(startPoint.getX(), startPoint.getY(), endPoint.getX(), endPoint.getY());
        
        if (isSelected()) {
            g.setColor(Color.BLUE);
            // Draw selection markers at endpoints
            g.fillRect(startPoint.getX() - 3, startPoint.getY() - 3, 6, 6);
            g.fillRect(endPoint.getX() - 3, endPoint.getY() - 3, 6, 6);
        }
        
        g.setColor(originalColor);
    }
    
    @Override
    public boolean contains(int x, int y) {
        // Check if point is close to the line
        double length = Math.sqrt(Math.pow(endPoint.getX() - startPoint.getX(), 2) + 
                                Math.pow(endPoint.getY() - startPoint.getY(), 2));
        
        if (length == 0) return false;
        
        double distanceToStart = Math.sqrt(Math.pow(x - startPoint.getX(), 2) + 
                                         Math.pow(y - startPoint.getY(), 2));
        double distanceToEnd = Math.sqrt(Math.pow(x - endPoint.getX(), 2) + 
                                       Math.pow(y - endPoint.getY(), 2));
        
        return Math.abs(length - (distanceToStart + distanceToEnd)) <= 2;
    }
    
    @Override
    public String toString() {
        return String.format("Line [(%d, %d) -> (%d, %d)]", 
                           startPoint.getX(), startPoint.getY(), 
                           endPoint.getX(), endPoint.getY());
    }
    
    // Getters and setters
    public Point getStartPoint() {
        return startPoint;
    }
    
    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
    }
    
    public Point getEndPoint() {
        return endPoint;
    }
    
    public void setEndPoint(Point endPoint) {
        this.endPoint = endPoint;
    }
    
    public double getLength() {
        return Math.sqrt(Math.pow(endPoint.getX() - startPoint.getX(), 2) + 
                        Math.pow(endPoint.getY() - startPoint.getY(), 2));
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Line line = (Line) obj;
        return startPoint.equals(line.startPoint) && endPoint.equals(line.endPoint);
    }
}
