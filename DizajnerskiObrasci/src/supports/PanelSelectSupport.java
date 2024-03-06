package supports;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class PanelSelectSupport {

	private PropertyChangeSupport propertyChangeSupport;
	
	public PanelSelectSupport() {
		propertyChangeSupport = new PropertyChangeSupport(this);
	}
	
	public void selectedShapesChanged() {
		propertyChangeSupport.firePropertyChange("selectedShapesChanged", null, null);
	}
	
	public void panelVisibilityChanged(Boolean value) {
		propertyChangeSupport.firePropertyChange("panelVisibilityChanged", null, value);
	}
	
	public void propertiesOfPanelTool(String enabledProperty) {
		propertyChangeSupport.firePropertyChange("propertiesOfPanelTool", null, enabledProperty);
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(listener);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(listener);
	}
	
}