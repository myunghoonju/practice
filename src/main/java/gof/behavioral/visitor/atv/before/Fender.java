package gof.behavioral.visitor.atv.before;

public class Fender implements AtvPart {

	@Override
	public void accept(AtvPartVisitor visitor) {
		visitor.visit(this);
	}
}
