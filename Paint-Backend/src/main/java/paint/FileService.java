package paint;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import org.json.*;
import org.springframework.stereotype.Service;

import paint.Model.Shape;

@Service
public class FileService {
	
	public void JSONSave(File file, LinkedList<Shape> list) throws IOException {
		JSONArray jsonArr = new JSONArray(list);
		FileWriter writer = new FileWriter(file);
        writer.write(jsonArr.toString());
        writer.flush();
        writer.close();
	}
	
	public LinkedList<Shape> JSONLoad(File file) throws IOException{
		LinkedList<Shape> list = new LinkedList<Shape>();
		String contents = new String((Files.readAllBytes(Paths.get(file.getAbsolutePath()))));
		JSONArray jsonArr = new JSONArray(contents);
		int length = jsonArr.length();
		JSONObject jsonObj;
		for(int i=0; i<length; i++) {
			jsonObj = jsonArr.getJSONObject(i);
			list.add(ShapeResource.fac.makeShape(jsonObj.toString()));
		}
		return list;
	}
	
	public void XMLSave(File file, LinkedList<Shape> list) {
		
	}
	
	public LinkedList<Shape> XMLLoad(File file){
		
	}
	
}
