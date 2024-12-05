package geometry;
import java.awt.Graphics;

public class Point extends Shape implements Moveable {
	
	private int x;
	private int y;
	
	public Point(){
		
	}

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Point(int x, int y, boolean selected) {
		this(x, y);
		this.selected = selected;
	}
	
	@Override
	public void draw(Graphics g) {
		g.drawLine(x - 3, y, x + 3, y);
		g.drawLine(x, y - 3, x, y + 3);
	}
	
	@Override
	public void moveTo(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public void moveBy(int byX, int byY) {
		x += byX;
		// x = x + byX;
		y += byY;
		// y = y + byY;
	}
	
	public double distance(Point p) {
		int dx = x - p.x;
		int dy = y - p.y;
		double distance = Math.sqrt(dx*dx + dy*dy);
		return distance;
	}
	
	public boolean contains(int x, int y) {
		return distance(new Point(x,y)) <= 2;
	}
	
	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof Point) {
			Point temp = (Point) o;
			if(x == temp.getX() && y == temp.getY()) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public int compareTo(Shape o) {
		if(o instanceof Point) {
		 Point coordinateCenter = new Point(0,0);
		 Point temp = (Point) o;
		 return (int)(this.distance(coordinateCenter) - 
				 temp.distance(coordinateCenter));
		}
		return 0;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}

}
