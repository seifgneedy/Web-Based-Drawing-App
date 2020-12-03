
package paint;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import paint.Model.*;


@CrossOrigin(origins = { "http://localhost:8080",  "http://localhost:8081" })
@RestController
public class ShapeResource {
	@Autowired
	private ShapeService service;
	private FileService fileService;
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
	
	@PostMapping("/upload")
	public boolean uploadFile(@RequestParam("file") MultipartFile file) throws IllegalStateException, IOException {
	    Path path = Paths.get(PaintBackendApplication.uploadFolder + file.getOriginalFilename());
	    file.transferTo(path);
	    try {
	    	if(file.getOriginalFilename().contains(".json")) {
		    	fileService.loadJSON(path.toFile());
		    }else {
		    	fileService.loadXML(path.toFile());
		    }
	    }catch(IOException e) {
	    	e.printStackTrace();
	    }
	    return true;
	}
	
	private Path saved = Paths.get("saved");
	public Resource load(String filename) {
		try {
		  Path file = saved.resolve(filename);
		  Resource resource = new UrlResource(file.toUri());
		  return resource;
		 }catch (RuntimeException | MalformedURLException e) {
		  e.printStackTrace();
		}
		return null;
	}
}

