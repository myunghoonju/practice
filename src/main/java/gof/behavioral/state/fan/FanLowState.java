package gof.behavioral.state.fan;

public class FanLowState extends State {

	private Fan fan;

	public FanLowState(Fan fan) {
		this.fan = fan;
	}

	public void handleRequest() {
		System.out.println("Turn the fan Med as it is next state");
		fan.setState(fan.getFanMedState());
		// In above scenario we can have new instance created as well, for that purpose
		// we will use the Singleton pattern to get the instance
	}
	
	/**
	 * To get the current state of Fan
	 */
	public String toString() {
		return "fan is Low";
	}
}
