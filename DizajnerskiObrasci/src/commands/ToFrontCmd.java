package commands;

import java.util.Collections;
import geometry.Shape;
import interfaces.Command;
import mvc.models.ShapesModel;
import supports.FrameSupport;
import supports.PanelToolSupport;

public class ToFrontCmd implements Command {

	private PanelToolSupport panelToolSupport;
	private ShapesModel shapesModel;
	private int indexOfSelectedShape;
	private Shape tempShape;
	
	public ToFrontCmd(FrameSupport frameSupport, ShapesModel shapesModel, int indexOfSelectedShape) {
		panelToolSupport = frameSupport.getPanelToolSupport();
		this.shapesModel = shapesModel;
		this.indexOfSelectedShape = indexOfSelectedShape;
		tempShape = this.shapesModel.getShape(indexOfSelectedShape);
	}
	
	@Override
	public void execute() {
		Collections.swap(shapesModel.getShapes(), indexOfSelectedShape, indexOfSelectedShape+1);
		shapesModel.shapesChanged();
		panelToolSupport.selectedShapesChanged();
	}

	@Override
	public void unexecute() {
		Collections.swap(shapesModel.getShapes(), indexOfSelectedShape+1, indexOfSelectedShape);
		shapesModel.shapesChanged();
		panelToolSupport.selectedShapesChanged();
	}

	@Override
	public String toString() {
		return "To Front - " + tempShape.toString();
	}

}