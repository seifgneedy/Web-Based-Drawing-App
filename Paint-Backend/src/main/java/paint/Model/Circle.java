package paint.Model;

public class Circle implements Shape{
	static final String type = "Circle";
	String color;
	String fillColor;
	double x;
	double y;
	double radius;
	
	public Circle () { }
	
	public Circle (String color, String fillColor, double x, double y, double radius) {
		this.color=color;
		this.fillColor=fillColor;
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
	public String getFillColor() {
		return fillColor;
	}

	@Override
	public void setFillColor(String color) {
		this.fillColor=color;
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