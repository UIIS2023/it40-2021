package mvc.models;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class TextModel {

	private PropertyChangeSupport propertyChangeSupport;
	private String leftText;
	private String centerText;
	private String fixedCenterText;

	public TextModel() {
		propertyChangeSupport = new PropertyChangeSupport(this);
	}
	
	public void setLeftText(String newLeftText) {
		leftText = newLeftText;
		propertyChangeSupport.firePropertyChange("leftTextChanged", null, leftText);
	}
	
	public void setCenterText(String newCenterText) {
		centerText = newCenterText;
		propertyChangeSupport.firePropertyChange("centerTextChanged", null, centerText);
	}
	
	public String getFixedCenterText() {
		return fixedCenterText;
	}
	
	public void setFixedCenterText(String newFixedCenterText) {
		fixedCenterText = newFixedCenterText;
		propertyChangeSupport.firePropertyChange("fixedCenterTextChanged", null, fixedCenterText);
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(listener);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(listener);
	}
	
}