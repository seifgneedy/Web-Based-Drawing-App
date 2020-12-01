package paint;

import java.beans.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import org.json.*;
import org.springframework.stereotype.Service;

import paint.Model.Shape;

@Service
public class FileService {
	
	public boolean saveJSON(File file, LinkedList<Shape> list) throws IOException {
		JSONArray jsonArr = new JSONArray(list);
		FileWriter writer = new FileWriter(file);
        writer.write(jsonArr.toString());
        writer.flush();
        writer.close();
        return true;
	}
	
	public LinkedList<Shape> loadJSON(File file) throws IOException{
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
	
	public boolean saveXML(File file, LinkedList<Shape> list) throws IOException {
		FileOutputStream fileStream = new FileOutputStream(file);
		XMLEncoder encoder = new XMLEncoder(fileStream);
		encoder.writeObject(list);
		encoder.close();
		fileStream.close();
		return true;
	}
	
	@SuppressWarnings("unchecked")
	public LinkedList<Shape> loadXML(File file) throws IOException{
		LinkedList<Shape> list;
		FileInputStream fileStream = new FileInputStream(file);
		XMLDecoder decoder = new XMLDecoder(fileStream);
		list = (LinkedList<Shape>) decoder.readObject();
		decoder.close();
		fileStream.close();
		return list;
	}
	
}
