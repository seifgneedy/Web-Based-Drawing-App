package paint.Model;

public class Circle implements Shape{
	static final String type = "Circle";
	String color;
	String fillColor;
	double X;
	double Y;
	double radius;

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
		return X;
	}

	@Override
	public void setX(double x) {
		this.X=x;
	}

	@Override
	public double getY() {
		return Y;
	}

	@Override
	public void setY(double y) {
		this.Y=y;
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
	
	public Circle (String color, String fillColor, double x, double y, double radius) {
		this.color=color;
		this.fillColor=fillColor;
		this.X=x;
		this.Y=y;
		this.radius=radius;
	}
}