package paint;

import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import paint.Model.MyShapeFactory;
import paint.Model.Shape;


@CrossOrigin(origins = { "http://localhost:8080",  "http://localhost:8020" })
@RestController
public class ShapeResource {
	@Autowired
	private ShapeService service;
	
	@GetMapping("/shapes")
	public String getShapes(){
		//return service.getShapes();
		return "abdo";
	}
	
	@PostMapping("/add")
	public void addShape(@RequestBody String shapeData) {
		MyShapeFactory fac = new MyShapeFactory();
		Shape s = fac.makeShape(shapeData);
		 
		service.addShape(s);

	}
	
	@PutMapping("/edit/{index}")
	public void editShape(@PathVariable int index, @RequestBody String shapeData) {
		MyShapeFactory fac = new MyShapeFactory();
		Shape s = fac.makeShape(shapeData);
		service.updateShape(s, index);
	}
	
	@DeleteMapping("/remove/{index}")
	public void removeShape (@PathVariable int index) {
		service.removeShape(index);
	}

}
