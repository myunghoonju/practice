package gof.behavioral.visitor.atv.before;

public class Oil implements AtvPart {

	@Override
	public void accept(AtvPartVisitor visitor) {
		visitor.visit(this);
	}
}
