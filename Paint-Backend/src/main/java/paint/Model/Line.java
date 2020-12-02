package paint.Model;

public class Line implements Shape {
	static final String type = "Line";
	String color;
	boolean filled;
	double x;
	double y;
	double x2;
	double y2;
	
	public Line(){ }
	
	public Line(String color,boolean filled, double x, double y, double x2, double y2) {
		this.color=color;
		this.filled=filled;
		this.x=x;
		this.y=y;
		this.x2=x2;
		this.y2=y2;
	}
	
	@Override
	public String getColor() {
		return color;
	}

	@Override
	public void setColor(String color) {
		this.color=color;
	}

	@Override
	public boolean getFilled() {
		return filled;
	}

	@Override
	public void setFilled(boolean filled) {
		this.filled=filled;
	}

	@Override
	public double getX() {
		return x;
	}

	@Override
	public void setX(double x) {
		this.x=x;
	}

	@Override
	public double getY() {
		return y;
	}

	@Override
	public void setY(double y) {
		this.y=y;
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

	public void setEndY(double y2) {
		this.y2 = y2;
	}
	
	public String getType() {
		return type;
	}
	
}
