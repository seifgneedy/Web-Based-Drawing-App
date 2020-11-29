package paint.Model;

public class Triangle implements Shape {
	static final String type = "Triangle";
	String color;
	String fillColor;
	double X1;
	double Y1;
	double X2;
	double Y2;
	double X3;
	double Y3;
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
	public double getX2() {
		return X2;
	}
	public void setX2(double x2) {
		this.X2 = x2;
	}
	public double getY2() {
		return Y2;
	}
	public void setY2(double y2) {
		this.Y2 = y2;
	}
	public double getX3() {
		return X3;
	}
	public void setX3(double x3) {
		this.X3 = x3;
	}
	public double getY3() {
		return Y3;
	}
	public void setY3(double y3) {
		this.Y3 = y3;
	}
	public String getType() {
		return type;
	}
	
	public Triangle (String color, String fillColor, double x1, double y1,double x2, double y2, double x3, double y3) {
		this.color=color;
		this.fillColor=fillColor;
		this.X1=x1;
		this.Y1=y1;
		this.X2=x2;
		this.Y2=y2;
		this.X3=x3;
		this.Y3=y3;

	}

}
