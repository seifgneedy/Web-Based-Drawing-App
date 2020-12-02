
package paint;

import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import paint.Model.*;


@CrossOrigin(origins = { "http://localhost:8080",  "http://localhost:8081" })
@RestController
public class ShapeResource {
	@Autowired
	private ShapeService service;
	
	public static ShapeFactory fac = new MyShapeFactory();
	
	@GetMapping("/shapes")
	public LinkedList<Shape> getShapes(){
		return service.getShapes();
	}
	
	@PostMapping("/add")
	public void addShape(@RequestBody String shapeData) {
		Shape s = fac.makeShape(shapeData);
		service.addShape(s);
	}
	
	@PutMapping("/edit/{index}")
	public void editShape(@PathVariable int index, @RequestBody String shapeData) {
		Shape s = fac.makeShape(shapeData);
		service.updateShape(s, index);
	}
	
	@DeleteMapping("/remove/{index}")
	public void removeShape (@PathVariable int index) {
		service.removeShape(index);
	}

}

