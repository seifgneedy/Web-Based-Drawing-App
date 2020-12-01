package paint;

import java.util.LinkedList;

import org.springframework.stereotype.Service;

import paint.Model.Shape;

@Service
public class ShapeService {
	private static LinkedList<Shape> shapes = new LinkedList<Shape>();
	
	public LinkedList<Shape> getShapes(){
		return shapes;
	}
	
	public void setShapes(LinkedList<Shape> list) {
		shapes = list;
	}
	public LinkedList<Shape> addShape (Shape s) {
		shapes.add(s);
		return shapes;

	}
	
	public LinkedList<Shape> removeShape (int i) {
		shapes.remove(i);
		return shapes;

	}
	
	public LinkedList<Shape> updateShape(Shape s, int i) {
		shapes.remove(i);
		shapes.add(i, s);
		return shapes;
	}

}
