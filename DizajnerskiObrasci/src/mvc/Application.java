package mvc;

import mvc.models.TextModel;
import mvc.models.ColorModel;
import mvc.models.ShapesModel;
import mvc.views.FrameView;
import mvc.controllers.FrameController;

public class Application {

	public static void main(String[] args) {
		ColorModel colorModel = new ColorModel();
		TextModel textModel = new TextModel();
		ShapesModel shapesModel = new ShapesModel();
		FrameView frameView = new FrameView();
		new FrameController(colorModel, textModel, shapesModel, frameView);
		frameView.setVisible(true);
	}

}