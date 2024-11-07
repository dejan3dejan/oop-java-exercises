package geometry;

public class Point {
	
	private int x;
	private int y;
	private boolean selected;
	
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
	
	public double distance(Point p) {
		int dx = x - p.x;
		int dy = y - p.y;
		double distance = Math.sqrt(dx*dx + dy*dy);
		return distance;
	}
	
	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public boolean isSelected() {
		return selected;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
}
