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
	
	public boolean contains(int x, int y) {
		return center.distance(new Point(x, y)) <= radius; 
	}
	
	@Override
	public String toString() {
		return "Center: (" + center.getX() + ", " +
				center.getY() + "), radius = " + radius;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof Circle) {
			Circle temp = (Circle) o;
			if(radius == temp.radius()) {
				return true;
			}
		}
		return false;
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
