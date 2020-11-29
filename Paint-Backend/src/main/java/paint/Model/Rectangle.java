package paint.Model;

public class Rectangle implements Shape {
	static final String type = "Rectangle";
	String color;
	String fillColor;
	double X1;
	double Y1;
	double width;
	double lenght;
	
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
	public void setX(double X1) {
		this.X1 = X1;
	}
	public double getY() {
		return Y1;
	}
	public void setY(double Y1) {
		this.Y1 = Y1;
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
	public Rectangle (String color, String fillColor, double x1, double y1,double width, double lenght) {
		this.color=color;
		this.fillColor=fillColor;
		this.X1=x1;
		this.Y1=y1;
		this.width=width;
		this.lenght=lenght;
	}


}
