package paint;

import paint.Model.Circle;
import paint.Model.Ellipse;
import paint.Model.Line;
import paint.Model.Rectangle;
import paint.Model.Shape;
import paint.Model.Square;
import paint.Model.Triangle;

public class MyShapeFactory implements ShapeFactory {
	
	public Shape makeShape(String type) {
		switch (type) {
		case "Circle":
			return new Circle(null, null, 0, 0, 0);
		case "Ellipse":
			return new Ellipse(null, null, 0, 0, 0, 0);
		case "Line":
			return new Line(null, 0, 0, 0, 0);
		case "Rectangle":
			return new Rectangle(null, null, 0, 0, 0, 0);
		case "Square":
			return new Square(null, null, 0, 0, 0);
		case "Triangle":
			return new Triangle(null, null, 0, 0, 0, 0, 0, 0);
		}
		return null;
	}
}