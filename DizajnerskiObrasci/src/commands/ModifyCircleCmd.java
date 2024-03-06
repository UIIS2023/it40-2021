package commands;

import geometry.Circle;
import interfaces.Command;
import mvc.models.ShapesModel;

public class ModifyCircleCmd implements Command {

	private ShapesModel shapesModel;
	private Circle selectedCircle;
	private Circle modifiedCircle;
	private Circle originalCircle;
	
	public ModifyCircleCmd(ShapesModel shapesModel, Circle modifiedCircle) {
		this.shapesModel = shapesModel;
		this.selectedCircle = (Circle)this.shapesModel.getSelectedShapes().getFirst();
		this.modifiedCircle = modifiedCircle;
		originalCircle = (Circle)selectedCircle.clone();
	}
	
	@Override
	public void execute() {
		try {
			selectedCircle.moveTo(modifiedCircle.getCenter().getX(), modifiedCircle.getCenter().getY());
			selectedCircle.setRadius(modifiedCircle.getRadius());
			selectedCircle.setBorderColor(modifiedCircle.getBorderColor());
			selectedCircle.setInnerColor(modifiedCircle.getInnerColor());
			shapesModel.shapesChanged();
		} catch (Exception e) {}
	}

	@Override
	public void unexecute() {
		try {
			selectedCircle.moveTo(originalCircle.getCenter().getX(), originalCircle.getCenter().getY());
			selectedCircle.setRadius(originalCircle.getRadius());
			selectedCircle.setBorderColor(originalCircle.getBorderColor());
			selectedCircle.setInnerColor(originalCircle.getInnerColor());
			shapesModel.shapesChanged();
		} catch (Exception e) {}
	}

	@Override
	public String toString() {
		return "Modified - " + selectedCircle.toString();
	}

}