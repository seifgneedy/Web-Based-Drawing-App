package paint.Model;

public class Ellipse implements Shape{
	String color;
	String fillColor;
	double x;
	double y;
	double a;
	double b;
	static final String type = "Ellipse";
	
	public Ellipse(){ }
	
	public Ellipse (String color, String fillColor, double x, double y, double a, double b) {
		this.color=color;
		this.fillColor=fillColor;
		this.x=x;
		this.y=y;
		this.a=a;
		this.b=b;
	}
	
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
		return x;
	}
	public void setX(double X) {
		this.x = X;
	}
	public double getY() {
		return y;
	}
	public void setY(double Y) {
		this.y = Y;
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
}
