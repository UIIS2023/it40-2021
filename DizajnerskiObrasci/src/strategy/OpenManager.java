package strategy;

import interfaces.OpenStrategy;

public class OpenManager {

	private OpenStrategy openStrategy;
	
	public OpenManager(OpenStrategy openStrategy) {
		this.openStrategy = openStrategy;
	}
	
	public void open() {
		openStrategy.open();
	}
	
}