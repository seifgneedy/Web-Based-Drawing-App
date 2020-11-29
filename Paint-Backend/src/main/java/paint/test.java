package paint;

import paint.Model.Shape;

public class test {

	public static void main(String[] args) {
		MyShapeFactory fac = new MyShapeFactory();
		Shape s1 = fac.makeShape("{\"type\":\"Circle\",\"color\":\"red\",\"fillColor\":\"blue\",\"x\":2,\"y\":3,\"raduis\":6}");
		System.out.println(s1.getType());
		System.out.println(s1.getColor());
		System.out.println(s1.getFillColor());
		System.out.println(s1.getX());
		System.out.println(s1.getY());
		
		

		
	}

}
