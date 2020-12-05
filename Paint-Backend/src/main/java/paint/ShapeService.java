package paint;

import java.util.LinkedList;
import java.util.Stack;

import org.springframework.stereotype.Service;

import paint.Model.Shape;

@Service
public class ShapeService {
	private static LinkedList<Shape> shapes = new LinkedList<Shape>();
	private Stack<LinkedList<Shape>> undo = new Stack<LinkedList<Shape>>();
	private Stack<LinkedList<Shape>> redo = new Stack<LinkedList<Shape>>();

	
	public LinkedList<Shape> getShapes(){
		return shapes;
	}
	
	public void setShapes(LinkedList<Shape> list) {
		shapes = list;
		undo.clear();
		redo.clear();
	}
	public LinkedList<Shape> addShape (Shape s) {
		shapes.add(s);
		LinkedList<Shape> current = new LinkedList<Shape>();
		for(int i=0;i<shapes.size();i++) {
			current.add(shapes.get(i));
		}
		undo.push(current);
		redo.clear();
		return shapes;

	}
	
	public LinkedList<Shape> removeShape (int i) {
		shapes.remove(i);
		LinkedList<Shape> current = new LinkedList<Shape>();
		for(int j=0;j<shapes.size();j++) {
			current.add(shapes.get(j));
		}	
		undo.push(current);
		redo.clear();
		return shapes;

	}
	
	public LinkedList<Shape> updateShape(Shape s, int i) {
		shapes.remove(i);
		shapes.add(i, s);
		LinkedList<Shape> current = new LinkedList<Shape>();
		for(int j=0;j<shapes.size();j++) {
			current.add(shapes.get(j));
		}	
		undo.push(current);
		redo.clear();
		return shapes;
	}
	
	public LinkedList<Shape> undo (){
		if(undo.isEmpty()) return null;
		shapes = undo.pop();
		redo.push(shapes);
		return shapes;
	}
	
	public LinkedList<Shape> redo (){
		if(redo.isEmpty()) return null;
		shapes = redo.pop();
		undo.push(shapes);
		return shapes;
	}
	public void clearShapes(){
		shapes=new LinkedList<>();
	}

}
