package commands;

import geometry.Line;
import interfaces.Command;
import mvc.models.ShapesModel;

public class ModifyLineCmd implements Command {

	private ShapesModel shapesModel;
	private Line selectedLine;
	private Line modifiedLine;
	private Line originalLine;
	
	public ModifyLineCmd(ShapesModel shapesModel, Line modifiedLine) {
		this.shapesModel = shapesModel;
		this.selectedLine = (Line)this.shapesModel.getSelectedShapes().getFirst();
		this.modifiedLine = modifiedLine;
		originalLine = (Line)selectedLine.clone();
	}
	
	@Override
	public void execute() {
		try {
			selectedLine.moveTo(modifiedLine.getStartPoint(), modifiedLine.getEndPoint());
			selectedLine.setBorderColor(modifiedLine.getBorderColor());
			shapesModel.shapesChanged();
		} catch (Exception e) {}
	}

	@Override
	public void unexecute() {
		try {
			selectedLine.moveTo(originalLine.getStartPoint(), originalLine.getEndPoint());
			selectedLine.setBorderColor(originalLine.getBorderColor());
			shapesModel.shapesChanged();
		} catch (Exception e) {}
	}

	@Override
	public String toString() {
		return "Modified - " + selectedLine.toString();
	}

}