package mvc.models;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
import geometry.Shape;

public class ShapesModel {
	
	private PropertyChangeSupport propertyChangeSupport;
	private List<Shape> shapes;
	private List<Shape> selectedShapes;
	
	public ShapesModel() {
		propertyChangeSupport = new PropertyChangeSupport(this);
		shapes = new ArrayList<Shape>();
		selectedShapes = new ArrayList<Shape>();
	}
	
	public void addShapeOnIndex(int index, Shape shapeToAdd) {
		shapes.add(index, shapeToAdd);
	}
	
	public void addShape(Shape shapeToAdd) {
		shapes.add(shapeToAdd);
		shapesChanged();
	}
	
	public void addSelectedShape(Shape selectedShapeToAdd) {
		selectedShapes.add(selectedShapeToAdd);
		shapesChanged();
	}
	
	public void removeShape(Shape shapeToRemove) {
		shapes.remove(shapeToRemove);
	}
	
	public void removeSelectedShape(Shape selectedShapeToRemove) {
		selectedShapes.remove(selectedShapeToRemove);
	}
	
	public Shape getShape(int indexOfShape) {
		return shapes.get(indexOfShape);
	}
	
	public List<Shape> getShapes(){
		return shapes;
	}
	
	public List<Shape> getSelectedShapes() {
		return selectedShapes;
	}
	
	public void setShapes(List<Shape> shapes) {
		for (Shape shape : shapes) {
			this.shapes.add(shape);
		}
		shapesChanged();
	}
	
	public void shapesChanged() {
		propertyChangeSupport.firePropertyChange("shapesChanged", null, null);
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(listener);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(listener);
	}
	
}