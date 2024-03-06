package commands;

import geometry.Shape;
import interfaces.Command;
import mvc.models.ShapesModel;
import supports.FrameSupport;
import supports.PanelSelectSupport;
import supports.PanelToolSupport;

public class SelectShapeCmd implements Command {

	private PanelSelectSupport panelSelectSupport;
	private PanelToolSupport panelToolSupport;
	private ShapesModel shapesModel;
	private Shape selectedShape;
	
	public SelectShapeCmd(FrameSupport frameSupport, ShapesModel shapesModel, Shape selectedShape) {
		panelSelectSupport = frameSupport.getPanelSelectSupport();
		panelToolSupport = frameSupport.getPanelToolSupport();
		this.shapesModel = shapesModel;
		this.selectedShape = selectedShape;
	}
	
	@Override
	public void execute() {
		selectedShape.setSelected(true);
		if (shapesModel.getSelectedShapes().isEmpty()) {
            panelSelectSupport.panelVisibilityChanged(true);
        }
		shapesModel.addSelectedShape(selectedShape);
		panelSelectSupport.selectedShapesChanged();
		panelToolSupport.selectedShapesChanged();
	}

	@Override
	public void unexecute() {
		selectedShape.setSelected(false);
		shapesModel.removeSelectedShape(selectedShape);
		shapesModel.shapesChanged();
		if(shapesModel.getSelectedShapes().isEmpty()) {
			panelSelectSupport.panelVisibilityChanged(false);
		}
		panelSelectSupport.selectedShapesChanged();
		panelToolSupport.selectedShapesChanged();
	}

	@Override
	public String toString() {
		return "Selected - " + selectedShape.toString();
	}

}