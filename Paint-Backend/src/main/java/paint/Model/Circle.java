package paint.Model;

public class Circle implements Shape{

	static final String type = "Circle";
	String color;
	boolean filled;
	double x;
	double y;
	double radius;
	double lineWidth;
	
	public Circle () { }
	
	public Circle (String color, boolean filled,double lineWidth, double x, double y, double radius) {
		this.color=color;
		this.filled=filled;
		this.lineWidth=lineWidth;
		this.x=x;
		this.y=y;
		this.radius=radius;
	}
	
	@Override
	public String getColor() {
		return color;
	}

	@Override
	public void setColor(String color) {
		this.color=color;
	}

	@Override
	public boolean getFilled() {
		return filled;
	}

	@Override
	public void setFilled(boolean filled) {
		this.filled=filled;
	}

	@Override
	public double getX() {
		return x;
	}

	@Override
	public void setX(double x) {
		this.x=x;
	}

	@Override
	public double getY() {
		return y;
	}

	@Override
	public void setY(double y) {
		this.y=y;
	}
	
	@Override
	public double getLineWidth() {
		return lineWidth;
	}

	@Override
	public void setLineWidth(double lineWidth) {
		this.lineWidth = lineWidth;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}
	public String getType() {
		return type;
	}
}