package gof.behavioral.visitor.atv.before;

public class Wheel implements AtvPart {

	@Override
	public void accept(AtvPartVisitor visitor) {
		visitor.visit(this);		
	}
}
