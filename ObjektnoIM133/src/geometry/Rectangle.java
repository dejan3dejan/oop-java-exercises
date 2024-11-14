package geometry;

public class Rectangle {
	
	private Point upperLeft;
	private int height;
	private int width;
	private boolean selected;
	
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
	
	public boolean isSelected() {
		return selected;
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
	
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
}