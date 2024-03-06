package commands;

import geometry.Rectangle;
import interfaces.Command;
import mvc.models.ShapesModel;

public class ModifyRectangleCmd implements Command {

	private ShapesModel shapesModel;
	private Rectangle selectedRectangle;
	private Rectangle modifiedRectangle;
	private Rectangle originalRectangle;
	
	public ModifyRectangleCmd(ShapesModel shapesModel, Rectangle modifiedRectangle) {
		this.shapesModel = shapesModel;
		this.selectedRectangle = (Rectangle)this.shapesModel.getSelectedShapes().getFirst();
		this.modifiedRectangle = modifiedRectangle;
		originalRectangle = (Rectangle)selectedRectangle.clone();
	}
	
	@Override
	public void execute() {
		try {
			selectedRectangle.moveTo(modifiedRectangle.getUpperLeftPoint().getX(), modifiedRectangle.getUpperLeftPoint().getY());
			selectedRectangle.setWidth(modifiedRectangle.getWidth());
			selectedRectangle.setHeight(modifiedRectangle.getHeight());
			selectedRectangle.setBorderColor(modifiedRectangle.getBorderColor());
			selectedRectangle.setInnerColor(modifiedRectangle.getInnerColor());
			shapesModel.shapesChanged();
		} catch (Exception e) {}
	}

	@Override
	public void unexecute() {
		try {
			selectedRectangle.moveTo(originalRectangle.getUpperLeftPoint().getX(), originalRectangle.getUpperLeftPoint().getY());
			selectedRectangle.setWidth(originalRectangle.getWidth());
			selectedRectangle.setHeight(originalRectangle.getHeight());
			selectedRectangle.setBorderColor(originalRectangle.getBorderColor());
			selectedRectangle.setInnerColor(originalRectangle.getInnerColor());
			shapesModel.shapesChanged();
		} catch (Exception e) {}
	}

	@Override
	public String toString() {
		return "Modified - " + selectedRectangle.toString();
	}

}