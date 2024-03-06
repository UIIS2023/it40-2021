package commands;

import geometry.Donut;
import interfaces.Command;
import mvc.models.ShapesModel;

public class ModifyDonutCmd implements Command {

	private ShapesModel shapesModel;
	private Donut selectedDonut;
	private Donut modifiedDonut;
	private Donut originalDonut;
	
	public ModifyDonutCmd(ShapesModel shapesModel, Donut modifiedDonut) {
		this.shapesModel = shapesModel;
		this.selectedDonut = (Donut)this.shapesModel.getSelectedShapes().getFirst();
		this.modifiedDonut = modifiedDonut;
		originalDonut = (Donut)selectedDonut.clone();
	}
	
	@Override
	public void execute() {
		try {
			selectedDonut.moveTo(modifiedDonut.getCenter().getX(), modifiedDonut.getCenter().getY());
			selectedDonut.setRadius(modifiedDonut.getRadius());
			selectedDonut.setInnerRadius(modifiedDonut.getInnerRadius());
			selectedDonut.setBorderColor(modifiedDonut.getBorderColor());
			selectedDonut.setInnerColor(modifiedDonut.getInnerColor());
			shapesModel.shapesChanged();
		} catch (Exception e) {}
	}

	@Override
	public void unexecute() {
		try {
			selectedDonut.moveTo(originalDonut.getCenter().getX(), originalDonut.getCenter().getY());
			selectedDonut.setRadius(originalDonut.getRadius());
			selectedDonut.setInnerRadius(originalDonut.getInnerRadius());
			selectedDonut.setBorderColor(originalDonut.getBorderColor());
			selectedDonut.setInnerColor(originalDonut.getInnerColor());
			shapesModel.shapesChanged();
		} catch (Exception e) {}
	}

	@Override
	public String toString() {
		return "Modified - " + selectedDonut.toString();
	}

}