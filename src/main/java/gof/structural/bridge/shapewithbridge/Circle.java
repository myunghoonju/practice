package gof.structural.bridge.shapewithbridge;

public class Circle extends Shape {

	public Circle(Colour colour) {
		super(colour);
	}

	@Override
	public void applyColour() {
		colour.applyColour();
	}

}
