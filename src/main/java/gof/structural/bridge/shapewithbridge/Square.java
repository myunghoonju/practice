package gof.structural.bridge.shapewithbridge;

public class Square extends Shape {

	public Square(Colour colour) {
		super(colour);
	}
	
	@Override
	public void applyColour() {
		colour.applyColour();
	}

}
