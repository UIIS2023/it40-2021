package supports;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javax.swing.JTextPane;
import interfaces.Command;

public class PanelToolSupport {

	private PropertyChangeSupport propertyChangeSupport;
	
	public PanelToolSupport() {
		propertyChangeSupport = new PropertyChangeSupport(this);
	}
	
	public void sendLogTextPane(JTextPane textPane) {
		propertyChangeSupport.firePropertyChange("sendLogTextPane", null, textPane);
	}
	
	public void selectedShapesChanged() {
		propertyChangeSupport.firePropertyChange("selectedShapesChanged", null, null);
	}
	
	public void pushToUndoStack(Command command) {
		propertyChangeSupport.firePropertyChange("pushToUndoStack", null, command);
	}
	
	public void popFromUndoStack() {
		propertyChangeSupport.firePropertyChange("popFromUndoStack", null, null);
	}
	
	public void pushToRedoStack(Command command) {
		propertyChangeSupport.firePropertyChange("pushToRedoStack", null, command);
	}
	
	public void popFromRedoStack() {
		propertyChangeSupport.firePropertyChange("popFromRedoStack", null, null);
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(listener);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(listener);
	}
	
}