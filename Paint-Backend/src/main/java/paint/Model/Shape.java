package paint.Model;

public interface Shape {
	String getColor();
	void setColor(String color);
	boolean getFilled();
	void setFilled(boolean filled);
	double getX();
	void setX(double x);
	double getY();
	void setY(double y);
	double getLineWidth();
	void setLineWidth(double lineWidth);
	String getType();
}