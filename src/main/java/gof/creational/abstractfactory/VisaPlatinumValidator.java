package gof.creational.abstractfactory;

public class VisaPlatinumValidator implements Validator{

	@Override
	public boolean isValid(CreditCard creditCard) {
		return false;
	}
}
