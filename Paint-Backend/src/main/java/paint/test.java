package paint;

import paint.Model.Shape;

public class test {

	public static void main(String[] args) {
		MyShapeFactory fac = new MyShapeFactory();
		Shape s1 = fac.makeShape("Triangle");
		System.out.print(s1.getType());
		
	}

}
