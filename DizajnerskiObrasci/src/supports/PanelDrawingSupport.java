package supports;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javax.swing.JButton;
import geometry.Point;

public class PanelDrawingSupport {

	private PropertyChangeSupport propertyChangeSupport;
	
	public PanelDrawingSupport() {
		propertyChangeSupport = new PropertyChangeSupport(this);
	}
	
	public void selectedButtonChanged(JButton selectedButton) {
		propertyChangeSupport.firePropertyChange("selectedButtonChanged", null, selectedButton);
	}
	
	public void propertiesOfPanelTool(String property, String[] values) {
		Object tempObject = new Object[] {property, values};
		propertyChangeSupport.firePropertyChange("propertiesOfPanelTool", null, tempObject);
	}
	
	public void startPointOfLineChanged(Point startPoint) {
		propertyChangeSupport.firePropertyChange("startPointOfLineChanged", null, startPoint);
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(listener);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(listener);
	}
	
}