package geometry;
import java.awt.Graphics;

public class Donut extends Circle implements Moveable {
	
	private int innerRadius;
	
	public Donut() {
		
	}
	
	public Donut(Point center, int radius, int innerRadius) {
		super(center, radius);
		this.innerRadius = innerRadius;
	}

	public Donut(Point center, int radius, int innerRadius, boolean selected) {
		this(center, radius, innerRadius);
		this.selected = selected;
	}
	
	@Override
	public void draw(Graphics g) {
		super.draw(g);
		g.drawOval(center.getX()-innerRadius, center.getY()-innerRadius,
				innerRadius*2, innerRadius*2);
	}
	
	@Override
	public String toString() {
		return super.toString() + " , innerRadius = " + innerRadius;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof Donut) {
			Donut temp = (Donut)o;
			if(radius == temp.radius && innerRadius == temp.innerRadius)
				return true;
		}
		return false;
	}
	
	@Override
	public double area() {
		return super.area() - innerRadius*innerRadius*Math.PI;		
	}
	
	@Override
	public String toString() {
		return super.toString() + " , innerRadius = " + innerRadius;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof Donut) {
			Donut temp = (Donut)o;
			if(radius == temp.radius && innerRadius == temp.innerRadius)
				return true;
		}
		return false;
	}
	
	@Override
	public boolean contains(int x, int y) {
		return super.contains(x, y) && 
				center.distance(new Point(x,y)) >= innerRadius;
	}
	
	@Override
	public boolean contains(Point p) {
		return contains(p.getX(), p.getY());
	}
	
	public int getInnerRadius() {
		return innerRadius;
	}
	
	public void setInnerRadius(int innerRadius) {
		this.innerRadius = innerRadius;
	}
}
