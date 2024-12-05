package geometry;
import java.awt.Graphics;

public class Circle extends Shape implements Moveable {
	
	protected Point center;
	protected int radius;
	
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
	
	@Override
	public void draw(Graphics g) {
		g.drawOval(center.getX()-radius, center.getY()-radius, radius*2, radius*2);
	}
	
	@Override
	public void moveTo(int x, int y) {
		center.moveTo(x, y);
	}

	@Override
	public void moveBy(int byX, int byY) {
		center.moveBy(byX, byY);
	}
	
	public boolean contains(int x, int y) {
		return center.distance(new Point(x, y)) <= radius; 
	}
	
	public boolean contains(Point p) {
		return contains(p.getX(), p.getY());
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
	
	@Override
	public int compareTo(Shape o) {
		if(o instanceof Circle) {
		 Circle temp = (Circle) o;
		 return (int)(this.area() - temp.area());
		}
		return 0;
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
	
	public void setCenter(Point center) {
		this.center = center;
	}
	
	public void setRadius(int radius) {
		this.radius = radius;
	}
}
