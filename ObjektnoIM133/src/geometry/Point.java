package geometry;

public class Point {
	
	private int x;
	private int y;
	private boolean selected;
	
	public double distance(Point p) {
		int dx = x - p.x;
		int dy = y - p.y;
		double distance = Math.sqrt(dx*dx + dy*dy);
		return distance;
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
//		System.out.println(this);
//		System.out.println(this.x);
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
}
