package paint.Model;

public class Rectangle implements Shape {
	static final String type = "Rectangle";
	String color;
	boolean filled;
	double lineWidth;
	double x;
	double y;
	double width;
	double length;
	
	public Rectangle() { }
	
	public Rectangle (String color, boolean filled,double lineWidth, double x, double y,double width, double length) {
		this.color=color;
		this.filled=filled;
		this.lineWidth=lineWidth;
		this.x=x;
		this.y=y;
		this.width=width;
		this.length=length;
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
	
	public double getLineWidth() {
		return lineWidth;
	}

	public void setLineWidth(double lineWidth) {
		this.lineWidth = lineWidth;
	}

	public double getWidth() {
		return width;
	}
	public void setWidth(double width) {
		this.width = width;
	}
	public double getlength() {
		return length;
	}
	public void setlength(double length) {
		this.length = length;
	}
	public String getType() {
		return type;
	}
	
}
