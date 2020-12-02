package paint.Model;

public class Rectangle implements Shape {
	static final String type = "Rectangle";
	String color;
	boolean filled;
	double x;
	double y;
	double width;
	double lenght;
	
	public Rectangle() { }
	
	public Rectangle (String color, boolean filled, double x, double y,double width, double lenght) {
		this.color=color;
		this.filled=filled;
		this.x=x;
		this.y=y;
		this.width=width;
		this.lenght=lenght;
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
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public double getWidth() {
		return width;
	}
	public void setWidth(double width) {
		this.width = width;
	}
	public double getLenght() {
		return lenght;
	}
	public void setLenght(double lenght) {
		this.lenght = lenght;
	}
	public String getType() {
		return type;
	}
	
}
