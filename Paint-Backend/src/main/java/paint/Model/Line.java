package paint.Model;

public class Line implements Shape {
	static final String type = "Line";
	String color;
	String fillColor;
	double startX;
	double startY;
	double endX;
	double endY;

	@Override
	public String getColor() {
		return color;
	}

	@Override
	public void setColor(String color) {
		this.color=color;
	}

	@Override
	public String getFillColor() {
		return fillColor;
	}

	@Override
	public void setFillColor(String color) {
		this.fillColor=color;
	}

	@Override
	public double getX() {
		return startX;
	}

	@Override
	public void setX(double x) {
		this.startX=x;
	}

	@Override
	public double getY() {
		return startY;
	}

	@Override
	public void setY(double y) {
		this.startY=y;
	}

	public double getEndX() {
		return endX;
	}

	public void setEndX(double endX) {
		this.endX = endX;
	}

	public double getEndY() {
		return endY;
	}

	public void setEndY(double endY) {
		this.endY = endY;
	}
	
	public String getType() {
		return type;
	}
	
	public Line (String color, double startX, double startY, double endX, double endY) {
		this.color=color;
		this.startX=startX;
		this.startY=startY;
		this.endX=endX;
		this.endY=endY;
	}
	
}
