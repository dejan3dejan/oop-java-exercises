package geometry;
import java.awt.Graphics;

public class Rectangle extends Shape implements Moveable{
	
	private Point upperLeft;
	private int height;
	private int width;
	
	public Rectangle() {
		
	}

	public Rectangle(Point upperLeft, int height, int width) {
		this.upperLeft = upperLeft;
		this.height = height;
		this.width = width;
	}
	
	public Rectangle(Point upperLeft, int height, int width, boolean selected) {
		this(upperLeft,height,width);
		this.selected = selected;
	}
	
	@Override
	public void draw(Graphics g) {
		g.drawRectangle(upperLeft.getX(), upperLeft.getY(), width, height);
	}
	
	@Override
	public void moveTo(int x, int y) {
		upperLeft.moveTo(x, y);
	}

	@Override
	public void moveBy(int byX, int byY) {
		upperLeft.moveBy(byX, byY);
	}
	
	public boolean contains(int x, int y) {
		return (x >= upperLeft.getX() && x <= upperLeft.getX() + width) &&
				(y >= upperLeft.getY() && y <= upperLeft.getY() + height); 
	}
	
	public boolean contains(Point p) {
		return contains(p.getX(), p.getY());
	}
	
	@Override
	public String toString() {
		return "Upper left point: (" + upperLeft.getX() + ", " +
				upperLeft.getY() + "), width = " + width + ", height = " +
				height;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof Rectangle) {
			Rectangle temp = (Rectangle) o;
			if(area() == temp.area()) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public int compareTo(Shape o) {
		if(o instanceof Rectangle) {
		 Rectangle temp = (Rectangle) o;
		 return this.area() - temp.area();
		}
		return 0;
	}
	
	public int area() {
		return width * height;
	}
	
	
	public int circumference() {
		return 2*height + 2*width;
	}
	
	public Point getUpperLeft() {
		return upperLeft;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setUpperLeft(Point upperLeft) {
		this.upperLeft = upperLeft;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
}