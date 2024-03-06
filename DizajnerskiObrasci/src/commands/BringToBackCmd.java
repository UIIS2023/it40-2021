package commands;

import geometry.Shape;
import interfaces.Command;
import mvc.models.ShapesModel;
import supports.FrameSupport;
import supports.PanelToolSupport;

public class BringToBackCmd implements Command {
	
	private PanelToolSupport panelToolSupport;
	private ShapesModel shapesModel;
	private int indexOfSelectedShape;
	private Shape tempShape;
	
	public BringToBackCmd(FrameSupport frameSupport, ShapesModel shapesModel, int indexOfSelectedShape) {
		panelToolSupport = frameSupport.getPanelToolSupport();
		this.shapesModel = shapesModel;
		this.indexOfSelectedShape = indexOfSelectedShape;
	}
	
	@Override
	public void execute() {
		tempShape = shapesModel.getShapes().remove(indexOfSelectedShape);
		shapesModel.addShapeOnIndex(0, tempShape);
		shapesModel.shapesChanged();
		panelToolSupport.selectedShapesChanged();
	}

	@Override
	public void unexecute() {
		shapesModel.getShapes().removeFirst();
		shapesModel.addShapeOnIndex(indexOfSelectedShape, tempShape);
		shapesModel.shapesChanged();
		panelToolSupport.selectedShapesChanged();
	}

	@Override
	public String toString() {
		return "Brought To Back - " + tempShape.toString();
	}
	
}