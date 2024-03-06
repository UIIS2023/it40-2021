package commands;

import geometry.HexagonAdapter;
import interfaces.Command;
import mvc.models.ShapesModel;

public class ModifyHexagonCmd implements Command {

	private ShapesModel shapesModel;
	private HexagonAdapter selectedHexagon;
	private HexagonAdapter modifiedHexagon;
	private HexagonAdapter originalHexagon;
	
	public ModifyHexagonCmd(ShapesModel shapesModel, HexagonAdapter modifiedHexagon) {
		this.shapesModel = shapesModel;
		this.selectedHexagon = (HexagonAdapter)this.shapesModel.getSelectedShapes().getFirst();
		this.modifiedHexagon = modifiedHexagon;
		originalHexagon = (HexagonAdapter)selectedHexagon.clone();
	}
	
	@Override
	public void execute() {
		try {
			selectedHexagon.moveTo((short)modifiedHexagon.getCenter().getX(), (short)modifiedHexagon.getCenter().getY());
			selectedHexagon.setRadius((short)modifiedHexagon.getRadius());
			selectedHexagon.setBorderColor(modifiedHexagon.getBorderColor());
			selectedHexagon.setInnerColor(modifiedHexagon.getInnerColor());
			shapesModel.shapesChanged();
		} catch (Exception e) {}
	}

	@Override
	public void unexecute() {
		try {
			selectedHexagon.moveTo((short)originalHexagon.getCenter().getX(), (short)originalHexagon.getCenter().getY());
			selectedHexagon.setRadius((short)originalHexagon.getRadius());
			selectedHexagon.setBorderColor(originalHexagon.getBorderColor());
			selectedHexagon.setInnerColor(originalHexagon.getInnerColor());
			shapesModel.shapesChanged();
		} catch (Exception e) {}
	}

	@Override
	public String toString() {
		return "Modified - " + selectedHexagon.toString();
	}

}