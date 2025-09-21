package stack;

/**
 * Circle class for Stack application
 * @author Dejan Zegarac IM133/2021
 */
public class Circle {
    private int x;      // X coordinate of center
    private int y;      // Y coordinate of center
    private int radius; // Radius of the circle

    // Default constructor
    public Circle() {
        this(0, 0, 1);
    }

    // Constructor with parameters
    public Circle(int x, int y, int radius) {
        this.x = x;
        this.y = y;
        setRadius(radius); // Use setter for validation
    }

    // Getters
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getRadius() {
        return radius;
    }

    // Setters with validation
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setRadius(int radius) {
        if (radius <= 0) {
            throw new IllegalArgumentException("Radius must be greater than 0!");
        }
        this.radius = radius;
    }

    // Calculate area for sorting later
    public double getArea() {
        return Math.PI * radius * radius;
    }

    // toString for JList display (LIFO format)
    @Override
    public String toString() {
        return String.format("Circle [Center: (%d, %d), Radius: %d, Area: %.2f]",
                x, y, radius, getArea());
    }

    // equals method
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Circle circle = (Circle) obj;
        return x == circle.x && y == circle.y && radius == circle.radius;
    }

    @Override
    public int hashCode() {
        return x * 31 + y * 31 + radius;
    }
}