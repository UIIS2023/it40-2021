package strategy;

import interfaces.SaveStrategy;

public class SaveManager {
	
	private SaveStrategy saveStrategy;
	
	public SaveManager(SaveStrategy saveStrategy) {
		this.saveStrategy = saveStrategy;
	}
	
	public void save() {
		saveStrategy.save();
	}

}