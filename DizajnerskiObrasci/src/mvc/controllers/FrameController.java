package mvc.controllers;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JFrame;
import mvc.models.TextModel;
import mvc.models.ColorModel;
import mvc.models.ShapesModel;
import mvc.views.FrameView;
import supports.FrameSupport;

public class FrameController implements PropertyChangeListener {

	private FrameView view;
	private PanelDrawingController panelDrawingController;
	private PanelSelectController panelSelectController;
	private PanelToolController panelToolController;
	private PanelTitleController panelTitleController;
	
	public FrameController(ColorModel colorModel, TextModel textModel, ShapesModel shapesModel, FrameView fv) {
		view = fv;
		FrameSupport frameSupport = new FrameSupport();
		frameSupport.addPropertyChangeListener(this);
		
		this.panelDrawingController = new PanelDrawingController(frameSupport, colorModel, textModel, shapesModel, view);
		view.getPanelDrawingView().setController(panelDrawingController);
		this.panelSelectController = new PanelSelectController(frameSupport, colorModel, textModel, shapesModel, view);
		view.getPanelSelectView().setController(panelSelectController);
		this.panelToolController = new PanelToolController(frameSupport, textModel, shapesModel, view);
		view.getPanelToolView().setController(panelToolController);
		this.panelTitleController = new PanelTitleController(frameSupport, textModel, colorModel, view);
		view.getPanelTitleView().setController(panelTitleController);
		new PanelLogController(frameSupport, view);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if ("executeExit".equals((String)evt.getPropertyName())) {
			System.exit(0);
		} else if ("executeMinimize".equals((String)evt.getPropertyName())) {
			view.setExtendedState(JFrame.ICONIFIED);
		}
	}
	
}