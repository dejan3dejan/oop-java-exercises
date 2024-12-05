package geometry;
import java.awt.Graphics;

public class Line extends Shape implements Moveable {
	
	private Point startPoint;
	private Point endPoint;
	
	public Line() {
		
	}
	
	public Line(Point startPoint, Point endPoint){
		this.startPoint = startPoint;
		this.endPoint = endPoint;
	}
	
	public Line(Point startPoint, Point endPoint, boolean selected){
		this(startPoint,endPoint);
		this.selected = selected;
	}
	
	@Override
	public void draw(Graphics g) {
		g.drawLine(startPoint.getX(), startPoint.getY(),
				endPoint.getX(), endPoint.getY());
	}
	
	@Override
	public void moveTo(int x, int y) {
		return;
	}

	@Override
	public void moveBy(int byX, int byY) {
		startPoint.moveBy(byX, byY);
		endPoint.moveBy(byX, byY);
	}
	
	public double length() {
		return startPoint.distance(endPoint);
	}
	
	public boolean contains(int x, int y) {
		Point click = new Point(x, y);
		return length() - (startPoint.distance(click) + endPoint.distance(click)) <= 2;
	}
	
	@Override
	public String toString()
	{
		return "(" + startPoint.getX() + ", " + startPoint.getY() +
				") --> (" + endPoint.getX() + ", " + endPoint.getY() + ")";
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof Line) {
			Line temp = (Line) o;
			if(startPoint.equals(temp.getStartPoint()) &&
					endPoint.equals(temp.getEndPoint())) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public int compareTo(Shape o) {
		if(o instanceof Line) {
		 Line temp = (Line) o;
		 return (int)(this.length() - temp.length());
		}
		return 0;
	}
	
	public Point getStartPoint() {
		return startPoint;
	}
	
	public Point getEndPoint() {
		return endPoint;
	}
	
	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}
	
	public void setEndPoint(Point endPoint) {
		this.endPoint = endPoint;
	}
}
