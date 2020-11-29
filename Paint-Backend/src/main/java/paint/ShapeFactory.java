package paint;


import paint.Model.Shape;

public interface ShapeFactory {
	Shape makeShape (String json);
}
