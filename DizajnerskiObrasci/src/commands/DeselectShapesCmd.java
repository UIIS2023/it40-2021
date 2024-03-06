package commands;

import java.util.ArrayList;
import java.util.List;
import geometry.Shape;
import interfaces.Command;
import mvc.models.ShapesModel;
import supports.FrameSupport;
import supports.PanelSelectSupport;
import supports.PanelToolSupport;

public class DeselectShapesCmd implements Command {
	
	private PanelSelectSupport panelSelectSupport;
	private PanelToolSupport panelToolSupport;
	private ShapesModel shapesModel;
	private List<Shape> deselectedShapes;
	
	public DeselectShapesCmd(FrameSupport frameSupport, ShapesModel shapesModel) {
		panelSelectSupport = frameSupport.getPanelSelectSupport();
		panelToolSupport = frameSupport.getPanelToolSupport();
		this.shapesModel = shapesModel;
		this.deselectedShapes = new ArrayList<Shape>(this.shapesModel.getSelectedShapes().size());
	}

	@Override
	public void execute() {
		for(Shape shape : shapesModel.getSelectedShapes()) {
			shape.setSelected(false);
			deselectedShapes.add(shape);
		}
		shapesModel.getSelectedShapes().clear();
		panelSelectSupport.panelVisibilityChanged(false);
		shapesModel.shapesChanged();
		panelSelectSupport.selectedShapesChanged();;
		panelToolSupport.selectedShapesChanged();
	}
	
	@Override
	public void unexecute() {
		for(Shape shape : deselectedShapes) {
			shape.setSelected(true);
			shapesModel.getSelectedShapes().add(shape);
		}
		deselectedShapes.clear();
		panelSelectSupport.panelVisibilityChanged(true);
		shapesModel.shapesChanged();
		panelSelectSupport.selectedShapesChanged();
		panelToolSupport.selectedShapesChanged();
	}

	@Override
	public String toString() {
		return "Deselected Shapes";
	}
	
}