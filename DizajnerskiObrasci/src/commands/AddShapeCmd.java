package commands;

import geometry.Shape;
import interfaces.Command;
import mvc.models.ShapesModel;

public class AddShapeCmd implements Command {

	private ShapesModel shapesModel;
	private Shape addedShape;
	
	public AddShapeCmd(ShapesModel shapesModel, Shape addedShape) {
		this.shapesModel = shapesModel;
		this.addedShape = addedShape;
	}
	
	@Override
	public void execute() {
		shapesModel.addShape(addedShape);
	}

	@Override
	public void unexecute() {
		shapesModel.removeShape(addedShape);
		shapesModel.shapesChanged();
	}

	@Override
	public String toString() {
		return "Added - " + addedShape.toString();
	}

}