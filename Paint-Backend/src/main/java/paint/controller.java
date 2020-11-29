package paint;

import java.util.LinkedList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import paint.Model.Shape;

@RestController
public class controller {
	@GetMapping("/circle")
	public LinkedList<Shape> circle () {
		MyShapeFactory fac = new MyShapeFactory();
		Shape s1 = fac.makeShape("{\"type\":\"Circle\",\"color\":\"red\",\"fillColor\":\"blue\",\"x\":2,\"y\":3,\"raduis\":6}");
		Shape s2 = fac.makeShape("{\"type\":\"Line\",\"color\":\"red\",\"fillColor\":\"blue\",\"x\":2,\"y\":3,\"x2\":6,\"y2\":7}");
		Shape s3 = fac.makeShape("{\"type\":\"Ellipse\",\"color\":\"red\",\"fillColor\":\"blue\",\"x\":2,\"y\":3,\"a\":6,\"b\":7}");
		LinkedList<Shape> shapes = new LinkedList<Shape>();
		shapes.add(s1);
		shapes.add(s2);
		shapes.add(s3);
		return shapes;
	}

}
