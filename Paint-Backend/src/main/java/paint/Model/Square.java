package paint.Model;

public class Square implements Shape {
	static final String type = "Square";
	String color;
	String fillColor;
	double x;
	double y;
	double width;
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
	public String getType() {
		return type;
	}
	
	public Square (String color, String fillColor, double x, double y,double width) {
		this.color=color;
		this.fillColor=fillColor;
		this.x=x;
		this.y=y;
		this.width=width;
	}
	

}
