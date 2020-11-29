package paint.Model;

public interface Shape {
	String getColor();
	void setColor(String color);
	String getFillColor();
	void setFillColor(String color);
	double getX();
	void setX(double x);
	double getY();
	void setY(double y);
	String getType();
}