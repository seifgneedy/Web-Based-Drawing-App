package paint.Model;

public class Ellipse implements Shape{
	String color;
	boolean filled;
	double x;
	double y;
	double a;
	double b;
	static final String type = "Ellipse";
	
	public Ellipse(){ }
	
	public Ellipse (String color, boolean filled, double x, double y, double a, double b) {
		this.color=color;
		this.filled=filled;
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
	public boolean getFilled() {
		return filled;
	}
	public void setFilled(boolean filled) {
		this.filled = filled;
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
