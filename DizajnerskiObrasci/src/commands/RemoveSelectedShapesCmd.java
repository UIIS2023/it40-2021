package commands;

import java.util.ArrayList;
import java.util.List;
import geometry.Shape;
import interfaces.Command;
import mvc.models.ShapesModel;
import supports.FrameSupport;
import supports.PanelSelectSupport;
import supports.PanelToolSupport;

public class RemoveSelectedShapesCmd implements Command {
	
	private PanelSelectSupport panelSelectSupport;
	private PanelToolSupport panelToolSupport;
	private ShapesModel shapesModel;
	private List<Shape> removedSelectedShapes;
	private List<Integer> indexesOfSelectedShapes;
	
	public RemoveSelectedShapesCmd(FrameSupport frameSupport, ShapesModel shapesModel) {
		panelSelectSupport = frameSupport.getPanelSelectSupport();
		panelToolSupport = frameSupport.getPanelToolSupport();
		this.shapesModel = shapesModel;
		this.removedSelectedShapes = new ArrayList<Shape>(this.shapesModel.getSelectedShapes().size());
		this.indexesOfSelectedShapes = new ArrayList<Integer>(this.shapesModel.getSelectedShapes().size());
	}
	
	@Override
	public void execute() {
		for(Shape shape : shapesModel.getShapes()) {
			if (shape.isSelected()) {
				indexesOfSelectedShapes.add(shapesModel.getShapes().indexOf(shape));
				removedSelectedShapes.add(shape);
			}
			
		}
		for(Shape shape : shapesModel.getSelectedShapes()) {
			shapesModel.removeShape(shape);
		}
		shapesModel.getSelectedShapes().clear();
		panelSelectSupport.panelVisibilityChanged(false);
		panelSelectSupport.selectedShapesChanged();
		panelToolSupport.selectedShapesChanged();
		shapesModel.shapesChanged();
	}

	@Override
	public void unexecute() {
		for(int i = 0; i < removedSelectedShapes.size(); i++) {
			if (indexesOfSelectedShapes.get(i) > shapesModel.getShapes().size()-1) {
				shapesModel.getShapes().add(removedSelectedShapes.get(i));
			} else {
				shapesModel.addShapeOnIndex(indexesOfSelectedShapes.get(i), removedSelectedShapes.get(i));
			}
			shapesModel.getSelectedShapes().add(removedSelectedShapes.get(i));
		}
		panelSelectSupport.panelVisibilityChanged(true);
		panelSelectSupport.selectedShapesChanged();
		panelToolSupport.selectedShapesChanged();
		removedSelectedShapes.clear();
		indexesOfSelectedShapes.clear();
		shapesModel.shapesChanged();
	}

	@Override
	public String toString() {
		return "Deleted Selected Shapes";
	}
	
}