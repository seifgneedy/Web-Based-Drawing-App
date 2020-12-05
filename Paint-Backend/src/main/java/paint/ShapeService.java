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
		LinkedList<Shape> current = new LinkedList<Shape>();
		for(int i=0;i<shapes.size();i++) {
			current.add(shapes.get(i));
		}
		undo.push(current);
		redo.clear();
		shapes.add(s);
		return shapes;

	}
	
	public LinkedList<Shape> removeShape (int i) {
		LinkedList<Shape> current = new LinkedList<Shape>();
		for(int j=0;j<shapes.size();j++) {
			current.add(shapes.get(j));
		}	
		undo.push(current);
		redo.clear();
		shapes.remove(i);
		return shapes;

	}
	
	public LinkedList<Shape> updateShape(Shape s, int i) {
		LinkedList<Shape> current = new LinkedList<Shape>();
		for(int j=0;j<shapes.size();j++) {
			current.add(shapes.get(j));
		}	
		undo.push(current);
		redo.clear();
		shapes.remove(i);
		shapes.add(i, s);
		return shapes;
	}
	
	public LinkedList<Shape> undo (){
		if(undo.isEmpty()) return shapes;
		redo.push(shapes);
		shapes = undo.pop();
		return shapes;
	}
	
	public LinkedList<Shape> redo (){
		if(redo.isEmpty()) return shapes;
		undo.push(shapes);
		shapes = redo.pop();
		return shapes;
	}
	public void clearShapes(){
		shapes=new LinkedList<>();
		undo.clear();
		redo.clear();
	}

}
