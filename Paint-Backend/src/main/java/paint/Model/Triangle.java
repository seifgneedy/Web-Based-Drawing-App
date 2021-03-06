package paint.Model;

public class Triangle implements Shape {
	static final String type = "Triangle";
	String color;
	boolean filled;
	double lineWidth;
	double x;
	double y;
	double x2;
	double y2;
	double x3;
	double y3;
	
	public Triangle() { }
	
	public Triangle (String color, boolean filled,double lineWidth, double x1, double y1,double x2, double y2, double x3, double y3) {
		this.color=color;
		this.filled=filled;
		this.lineWidth=lineWidth;
		this.x=x1;
		this.y=y1;
		this.x2=x2;
		this.y2=y2;
		this.x3=x3;
		this.y3=y3;
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

	public double getX2() {
		return x2;
	}
	public void setX2(double x2) {
		this.x2 = x2;
	}
	public double getY2() {
		return y2;
	}
	public void setY2(double y2) {
		this.y2 = y2;
	}
	public double getX3() {
		return x3;
	}
	public void setX3(double x3) {
		this.x3 = x3;
	}
	public double getY3() {
		return y3;
	}
	public void setY3(double y3) {
		this.y3 = y3;
	}
	public String getType() {
		return type;
	}
	

}
