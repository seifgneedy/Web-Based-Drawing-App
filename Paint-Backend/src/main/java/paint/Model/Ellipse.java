package paint.Model;

public class Ellipse implements Shape{
	String color;
	String fillColor;
	double X;
	double Y;
	double a;
	double b;
	static final String type = "Ellipse";
	
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getFillColor() {
		return fillColor;
	}
	public void setFillColor(String fillColor) {
		this.fillColor = fillColor;
	}
	public double getX() {
		return X;
	}
	public void setX(double X) {
		this.X = X;
	}
	public double getY() {
		return Y;
	}
	public void setY(double Y) {
		this.Y = Y;
	}
	public double getA() {
		return a;
	}
	public void setA(double a) {
		this.a = a;
	}
	public double getB() {
		return b;
	}
	public void setB(double b) {
		this.b = b;
	}
	public String getType() {
		return type;
	}
	
	public Ellipse (String color, String fillColor, double x, double y, double a, double b) {
		this.color=color;
		this.fillColor=fillColor;
		this.X=x;
		this.Y=y;
		this.a=a;
		this.b=b;

	}
}
