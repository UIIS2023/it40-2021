package supports;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class FrameSupport {

	private PropertyChangeSupport propertyChangeSupport;
	private PanelSelectSupport panelSelectSupport;
	private PanelDrawingSupport panelDrawingSupport;
	private PanelToolSupport panelToolSupport;
	private PanelLogSupport panelLogSupport;
	
	public FrameSupport() {
		propertyChangeSupport = new PropertyChangeSupport(this);
		panelSelectSupport = new PanelSelectSupport();
		panelDrawingSupport = new PanelDrawingSupport();
		panelToolSupport = new PanelToolSupport();
		panelLogSupport = new PanelLogSupport();
	}
	
	public void executeExit() {
		propertyChangeSupport.firePropertyChange("executeExit", null, null);
	}
	
	public void executeMinimize() {
		propertyChangeSupport.firePropertyChange("executeMinimize", null, null);
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(listener);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(listener);
	}
	
	public PanelSelectSupport getPanelSelectSupport() {
		return panelSelectSupport;
	}
	
	public PanelDrawingSupport getPanelDrawingSupport() {
		return panelDrawingSupport;
	}
	
	public PanelToolSupport getPanelToolSupport() {
		return panelToolSupport;
	}
	
	public PanelLogSupport getPanelLogSupport() {
		return panelLogSupport;
	}
	
}