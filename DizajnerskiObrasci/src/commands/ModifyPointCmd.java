package commands;

import geometry.Point;
import interfaces.Command;
import mvc.models.ShapesModel;

public class ModifyPointCmd implements Command {

	private ShapesModel shapesModel;
	private Point selectedPoint;
	private Point modifiedPoint;
	private Point originalPoint;
	
	public ModifyPointCmd(ShapesModel shapesModel, Point modifiedPoint) {
		this.shapesModel = shapesModel;
		this.selectedPoint = (Point)this.shapesModel.getSelectedShapes().getFirst();
		this.modifiedPoint = modifiedPoint;
		originalPoint = (Point)selectedPoint.clone();
	}
	
	@Override
	public void execute() {
		try {
			selectedPoint.moveTo(modifiedPoint.getX(), modifiedPoint.getY());
			selectedPoint.setBorderColor(modifiedPoint.getBorderColor());
			shapesModel.shapesChanged();
		} catch (Exception e) {}
	}

	@Override
	public void unexecute() {
		try {
			selectedPoint.moveTo(originalPoint.getX(), originalPoint.getY());
			selectedPoint.setBorderColor(originalPoint.getBorderColor());
			shapesModel.shapesChanged();
		} catch (Exception e) {}
	}

	@Override
	public String toString() {
		return "Modified - " + selectedPoint.toString();
	}

}