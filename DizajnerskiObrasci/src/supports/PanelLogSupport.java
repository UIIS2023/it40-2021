package supports;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class PanelLogSupport {

	private PropertyChangeSupport propertyChangeSupport;
	
	public PanelLogSupport() {
		propertyChangeSupport = new PropertyChangeSupport(this);
	}
	
	public void addTextToLog(String log) {
		propertyChangeSupport.firePropertyChange("addTextToLog", null, log);
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(listener);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(listener);
	}
	
}