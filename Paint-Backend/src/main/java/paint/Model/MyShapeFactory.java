package paint.Model;

import org.json.JSONException;
import org.json.JSONObject;
public class MyShapeFactory implements ShapeFactory {
	
	public Shape makeShape(String json) {
		JSONObject obj;
		try {
			obj = new JSONObject(json);
			String type = obj.getString("type");
			String color = obj.getString("color");
			String fillColor;
			if(type.equals("Line")) {
			fillColor = color;
			}
			else
				fillColor = obj.getString("fillColor");

			switch (type) {
			case "Circle":
				double x = obj.getDouble("x");
				double y = obj.getDouble("y");
				double radius = obj.getDouble("radius");
				return new Circle(color, fillColor, x, y, radius);
			case "Ellipse":
				x = obj.getDouble("x");
				y = obj.getDouble("y");
				double a = obj.getDouble("a");
				double b = obj.getDouble("b");
				return new Ellipse(color, fillColor, x, y, a, b);
			case "Line":
				x = obj.getDouble("x");
				y = obj.getDouble("y");
				double x2 = obj.getDouble("x2");
				double y2 = obj.getDouble("y2");
				return new Line(color,fillColor, x, y, x2, y2);
			case "Rectangle":
				x = obj.getDouble("x");
				y = obj.getDouble("y");
				double width = obj.getDouble("width");
				double lenght = obj.getDouble("length");
				return new Rectangle(color, fillColor, x, y, width, lenght);
			case "Square":
				x = obj.getDouble("x");
				y = obj.getDouble("y");
				width = obj.getDouble("width");
				return new Square(color, fillColor, x, y, width);
			case "Triangle":
				x = obj.getDouble("x");
				y = obj.getDouble("y");
				x2 = obj.getDouble("x2");
				y2 = obj.getDouble("y2");
				double x3 = obj.getDouble("x3");
				double y3 = obj.getDouble("y3");
				return new Triangle(color, fillColor, x, y, x2, y2, x3, y3);
				default:
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
}