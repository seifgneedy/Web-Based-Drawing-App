package paint.Model;

public class Square implements Shape {
	static final String type = "Square";
	String color;
	String fillColor;
	double X1;
	double Y1;
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
		return X1;
	}
	public void setX(double x1) {
		this.X1 = x1;
	}
	public double getY() {
		return Y1;
	}
	public void setY(double y1) {
		this.Y1 = y1;
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
	
	public Square (String color, String fillColor, double x1, double y1,double width) {
		this.color=color;
		this.fillColor=fillColor;
		this.X1=x1;
		this.Y1=y1;
		this.width=width;
	}
	

}
