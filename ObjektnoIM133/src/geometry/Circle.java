package geometry;

public class Circle {
	
	private Point center;
	private int radius;
	private boolean selected;
	
	public Circle() {
		
	}
	
	public Circle(Point center, int radius) {
		this.center = center;
		this.radius = radius;
	}
	
	public Circle(Point center, int radius, boolean selected) {
		this(center, radius);
		this.selected = selected;
	}
	
	public double area() {
		return radius * radius	* Math.PI;
	}
	
	public double circumference() {
		return 2 * radius * Math.PI;
	}
	
	public Point center() {
		return center;
	}
	
	public int radius() {
		return radius;
	}
	
	public boolean isSelected() {
		return selected;
	}
	
	public void setCenter(Point center) {
		this.center = center;
	}
	
	public void setRadius(int radius) {
		this.radius = radius;
	}
	
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
}
